// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.swervedrive.auto.Autos;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDrive;
import frc.robot.commands.swervedrive.drivebase.AbsoluteFieldDrive;
import frc.robot.commands.swervedrive.drivebase.TeleopDrive;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;

//PathPlanner Imports
import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;

//Command Imports
import frc.robot.commands.ArmPositions.*;
import frc.robot.commands.IntakeArm.*;
import frc.robot.commands.IntakeFloor.*;
import frc.robot.commands.Conveyor.*;
import frc.robot.commands.Compressor.*;

//Subsystem Imports
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ArmIntake;
import frc.robot.subsystems.Compressor;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.FloorIntake;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{

  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                         "swerve/neo"));
  // CommandJoystick rotationController = new CommandJoystick(1);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  // CommandJoystick driverController = new CommandJoystick(1); // Need to delete 

  // CommandJoystick driverController   = new CommandJoystick(3);//(OperatorConstants.DRIVER_CONTROLLER_PORT);
  XboxController driverXbox = new XboxController(0);
  XboxController gunnerXbox = new XboxController(1);

  private final SendableChooser<String> m_autoChooser = new SendableChooser<String>();

  Arm arm = new Arm();
  ArmIntake armIntake = new ArmIntake();
  Conveyor conveyor = new Conveyor();
  FloorIntake floorIntake = new FloorIntake();
  Compressor compressor = new Compressor();

  //SmartDashboard.putNumber("Gyro Pitch", swerveSubsystem.getPitch().getDegrees());


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    // Configure the trigger bindings
    configureBindings();
    initializeAutoChooser();
    periodic();
 
    TeleopDrive simClosedFieldRel = new TeleopDrive(
      drivebase,
       () -> MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
       () -> MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
       () -> driverXbox.getRawAxis(4), () -> true, false, false); //2

    TeleopDrive closedFieldRel = new TeleopDrive(
        drivebase,
        () -> -MathUtil.applyDeadband(driverXbox.getRightY(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> -MathUtil.applyDeadband(driverXbox.getRightX(), OperatorConstants.LEFT_X_DEADBAND),
        () -> -driverXbox.getRawAxis(0), () -> true, false, false);
        // () -> -MathUtil.applyDeadband(driverController.getY(), OperatorConstants.LEFT_Y_DEADBAND),
        // () -> -MathUtil.applyDeadband(driverController.getX(), OperatorConstants.LEFT_X_DEADBAND),
        // () -> -driverController.getRawAxis(4), () -> true, false, true);

    drivebase.setDefaultCommand(!RobotBase.isSimulation() ? closedFieldRel : simClosedFieldRel);

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary predicate, or via the
   * named factories in {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */

   
  public void periodic(){

  }



  private void configureBindings()
  {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    /*
     * DRIVER BUTTON CONTROLS
     */

    new JoystickButton(driverXbox, 4).onTrue((new InstantCommand(drivebase::zeroGyro)));
    // new JoystickButton(driverXbox, 3).onTrue(new InstantCommand(drivebase::addFakeVisionReading));
    new JoystickButton(driverXbox, 5).whileTrue(new RepeatCommand(new InstantCommand(drivebase::lock, drivebase)));
 
    JoystickButton d_leftBumper = new JoystickButton(driverXbox, 6);

    d_leftBumper.onTrue(new IFCollect(floorIntake));
    d_leftBumper.onTrue(new CompOut(compressor));
    d_leftBumper.onFalse(new IFStop(floorIntake));
    d_leftBumper.onFalse(new CompIn(compressor));

     
     /*
      * GUNNER BUTTON CONTROLS
      */
    
      JoystickButton g_OneButt = new JoystickButton(gunnerXbox, 1);
      JoystickButton g_TwoButt = new JoystickButton(gunnerXbox, 2);
      JoystickButton g_ThreeButt = new JoystickButton(gunnerXbox, 3);
      JoystickButton g_FourButt = new JoystickButton(gunnerXbox, 4);
      JoystickButton g_FiveButt = new JoystickButton(gunnerXbox, 5);
      JoystickButton g_SixButt = new JoystickButton(gunnerXbox, 6);
      JoystickButton g_SevenButt = new JoystickButton(gunnerXbox, 7);
      JoystickButton g_EightButt = new JoystickButton(gunnerXbox, 8);
      JoystickButton g_NineButt = new JoystickButton(gunnerXbox, 9);
      JoystickButton g_TenButt = new JoystickButton(gunnerXbox, 10);
      JoystickButton g_ElevenButt = new JoystickButton(gunnerXbox, 11);
      JoystickButton g_TwelveButt = new JoystickButton(gunnerXbox, 12);

      g_TwelveButt.onTrue(new ACollapse(arm));
      g_TwelveButt.onFalse(new ACollapse(arm));

      /*
      * CONE OUTPUTS
      */

      g_SevenButt.onTrue(new AConeHigh(arm));
      g_SevenButt.onFalse(new ACollapse(arm));

      g_EightButt.onTrue(new AConeMid(arm));
      g_EightButt.onFalse(new ACollapse(arm));

      g_NineButt.onTrue(new AConeMid(arm));
      g_NineButt.onFalse(new ACollapse(arm));

      g_TenButt.onTrue(new AConeHuman(arm));
      g_TenButt.onTrue(new IAConeIntake(armIntake));
      g_TenButt.onFalse(new IAStop(armIntake));
      g_TenButt.onFalse(new ACollapse(arm));

      g_ElevenButt.onTrue(new IAConeExtract(armIntake));
      g_ElevenButt.onFalse(new IAStop(armIntake));

  
      /*
       * CUBE-Y DOOBY DOO OUTPUTS
       */
        
      g_OneButt.onTrue(new ACubeHigh(arm));
      g_OneButt.onFalse(new ACollapse(arm));

      g_TwoButt.onTrue(new ACubeMid(arm));
      g_TwoButt.onFalse(new ACollapse(arm));

      g_ThreeButt.onTrue(new ACubeMid(arm));
      g_ThreeButt.onFalse(new ACollapse(arm));

/*    g_FourButt.onTrue(new ACubeHuman(arm));
      g_FourButt.onTrue(new IACubeIntake(armIntake));
      g_FourButt.onFalse(new IAStop(armIntake));
      g_FourButt.onFalse(new ACollapse(arm));
  */
      
      g_FiveButt.onTrue(new IACubeExtract(armIntake));
      g_FiveButt.onFalse(new IAStop(armIntake));

      g_SixButt.onTrue(new ACubeConvey(arm));
      g_SixButt.onTrue(new IACubeIntake(armIntake));
      g_SixButt.onTrue(new CGo(conveyor));
      g_SixButt.onFalse(new CStopp(conveyor));
      g_SixButt.onFalse(new IAStop(armIntake));
      g_SixButt.onFalse(new ACollapse(arm));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */


  // public Command getAutonomousCommand()
  // {
  //   // An example command will be run in autonomous
  //   return Autos.exampleAuto(drivebase);
  // }

  
  private String initializeAutoChooser() {
    m_autoChooser.setDefaultOption("Test Path", "path1");
    m_autoChooser.addOption("No Bump 2.5 Peice", "path2");
    m_autoChooser.addOption("No Bump 2 Peice", "path3");
    m_autoChooser.addOption("Balance Grab", "path4");
    m_autoChooser.addOption("Balance Leave", "path7");
    m_autoChooser.addOption("Balance", "path8");
    m_autoChooser.addOption("Bump 2 Peice", "path6");
    
    SmartDashboard.putData("Auto Selector", m_autoChooser);
    return m_autoChooser.getSelected();
  }

  public Command getAutoPaths(){
    return Autos.autoBuilderBase(drivebase, initializeAutoChooser(), arm, armIntake, floorIntake, conveyor, compressor);
  }
 

  public void setDriveMode()
  {
    //drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake)
  {
    drivebase.setMotorBrake(brake);
  }
}