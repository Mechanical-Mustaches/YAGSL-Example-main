package frc.robot.commands.swervedrive.drivebase;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import swervelib.SwerveController;

public class aprilRotation extends CommandBase {

  private SwerveSubsystem drivebase;
  private PIDController controllerR;
  private DoubleSupplier vX;
  private DoubleSupplier vY;
  private DoubleSupplier omega;
  private BooleanSupplier driveMode;
  private SwerveController controller;

  public aprilRotation(SwerveSubsystem drivebase, DoubleSupplier vX, DoubleSupplier vY, DoubleSupplier omega,
      BooleanSupplier driveMode) {
    this.vX = vX;
    this.vY = vY;
    this.omega = omega;
    this.driveMode = driveMode;
    this.controller = drivebase.getSwerveController();
    this.drivebase = drivebase;

    addRequirements(this.drivebase);

    controllerR = new PIDController(0.025, 0.0, 0);
  }

  @Override
  public void initialize() {
      LimelightHelpers.setPipelineIndex("limelight", 0);
      SmartDashboard.putString("Drivebase mode", "april");
      SmartDashboard.putBoolean("seen", false);
  }

  @Override
  public void execute() {

    double xVelocity = Math.pow(vX.getAsDouble(), 3);
    double yVelocity = Math.pow(vY.getAsDouble(), 3);
    double angVelocity = Math.pow(omega.getAsDouble(), 3);
    SmartDashboard.putNumber("vX", xVelocity);
    SmartDashboard.putNumber("vY", yVelocity);
    SmartDashboard.putNumber("omega", angVelocity);

     if(LimelightHelpers.getTV("limelight")) {
      SmartDashboard.putBoolean("seen", true);
      double rotation = controllerR.calculate(LimelightHelpers.getTX("limelight"), 0.0);

      drivebase.drive(new Translation2d(xVelocity * drivebase.maximumSpeed, yVelocity * drivebase.maximumSpeed),
          3*rotation,
          driveMode.getAsBoolean());
    } else {
      SmartDashboard.putBoolean("seen", false);
      drivebase.drive(new Translation2d(xVelocity * drivebase.maximumSpeed, yVelocity * drivebase.maximumSpeed),
          angVelocity * controller.config.maxAngularVelocity,
          driveMode.getAsBoolean());
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}