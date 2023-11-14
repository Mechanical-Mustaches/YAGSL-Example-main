// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.swervedrive.drivebase;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPoint;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.swervedrive.auto.FollowTrajectory;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import frc.robot.commands.swervedrive.auto.*;

/**
 * An example command that uses an example subsystem.
 */
public class onTheFly extends CommandBase {
  private SwerveSubsystem swerve;
  private PathPlannerTrajectory path = null;
  private Rotation2d rotation;
  private Rotation2d holonomic;
  private Translation2d offset;
  private int id;
  private static AprilTagFieldLayout aprilTagField = null;

  public onTheFly(SwerveSubsystem swerve, Rotation2d rotation, Rotation2d holonomic, Translation2d offset, int id) {
    this.swerve = swerve;
    this.rotation = rotation;
    this.holonomic = holonomic;
    this.offset = offset;
    this.id = id;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    path = swerve.createPath(id, rotation, holonomic, offset);
    System.out.println("pathcreated");
    new SequentialCommandGroup(new FollowTrajectory(swerve, path, false));
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
