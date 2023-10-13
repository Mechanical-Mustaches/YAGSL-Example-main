package frc.robot.commands.ArmPositions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class ACubeMid extends CommandBase{
    Arm arm;

    public ACubeMid(Arm arm){
        addRequirements(arm);
        this.arm = arm;
    }

    public void execute(){
        arm.armCubeMid();
    }

    public boolean isFinished(){
        return arm.isMidCubeSetpoint();
    }
    
}
