package frc.robot.commands.swervedrive.auto;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPoint;
import com.pathplanner.lib.auto.PIDConstants;
import com.pathplanner.lib.auto.SwerveAutoBuilder;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.Auton;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;


public final class aprilPP
{
  // More complex path with holonomic rotation. Non-zero starting velocity of 2 m/s. Max velocity of 4 m/s and max accel of 3 m/s^2
public PathPlannerTrajectory master = PathPlanner.generatePath(
  new PathConstraints(4, 3), 
  new PathPoint(
    new Translation2d(1.0, 1.0), 
    Rotation2d.fromDegrees(0), 
    Rotation2d.fromDegrees(0), 
    2
    ),
  new PathPoint(
    new Translation2d(3.0, 3.0), 
    Rotation2d.fromDegrees(45), 
    Rotation2d.fromDegrees(-90)
));

public CommandBase goToLimelight(SwerveSubsystem swerve) {
  SwerveAutoBuilder pathBuilder = new SwerveAutoBuilder(
    swerve::getPose,
    swerve::resetOdometry,
    new PIDConstants(Auton.yAutoPID.p, Auton.yAutoPID.i, Auton.yAutoPID.d),
    new PIDConstants(Auton.angleAutoPID.p, Auton.angleAutoPID.i, Auton.angleAutoPID.d),
    swerve::setChassisSpeeds,
    null, 
    false);
  return Commands.sequence(pathBuilder.fullAuto(master));
}
}