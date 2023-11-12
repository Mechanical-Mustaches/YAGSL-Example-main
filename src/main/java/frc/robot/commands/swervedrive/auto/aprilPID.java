package frc.robot.commands.swervedrive.auto;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class aprilPID extends CommandBase
{

  private final SwerveSubsystem swerveSubsystem;
  private final PIDController   controllerX;
  private final PIDController   controllerY;
  private final PIDController   controllerR;

  public aprilPID(SwerveSubsystem swerveSubsystem)
  {
    this.swerveSubsystem = swerveSubsystem;
    controllerX = new PIDController(3, 0.0, 0.0);
    controllerY = new PIDController(3, 0.0, 0.0);
    controllerR = new PIDController(0.1, 0.0, 0); //0.1

    controllerX.setTolerance(0.05);
    controllerY.setTolerance(0.05);
    controllerR.setTolerance(0.1);

    controllerX.setSetpoint(-1);
    controllerY.setSetpoint(0.0);
    controllerR.setSetpoint(0.0);
    
    addRequirements(this.swerveSubsystem);
  }

  @Override
  public void execute()
  {
    /*
     * If the limelight can see the target, run the drive function
     */
    if(LimelightHelpers.getTV("limelight")) {

      //PID Loop changes limelight values into new varibles
      double translationX = MathUtil.clamp(controllerX.calculate(
        LimelightHelpers.getCameraPose_TargetSpace("limelight")[2], 
        -1), -4,4);
      double translationY = MathUtil.clamp(controllerY.calculate(
        LimelightHelpers.getCameraPose_TargetSpace("limelight")[0], 
        0.0), -4,4);
      double rotation = MathUtil.clamp(controllerR.calculate(
        LimelightHelpers.getCameraPose_TargetSpace("limelight")[4], 
        0.0), -2,2);

      //Drives robot from new varibles
      swerveSubsystem.drive(
        new Translation2d(translationX, -translationY),
        -rotation, 
        false, 
        false);
    } else {
      swerveSubsystem.drive(
        new Translation2d(0.0, 0.0),
        0.0, 
        false, 
        false);
    }
  }

  @Override
  public boolean isFinished()
  {
    return false;
    //return (controllerX.atSetpoint() && controllerY.atSetpoint() && controllerR.atSetpoint() && LimelightHelpers.getTV("limelight"));
  }
}