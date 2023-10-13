package frc.robot.commands.ArmPositions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class AConeFloor extends CommandBase {
    Arm arm;

    public AConeFloor(Arm arm){
        addRequirements(arm);
        this.arm = arm;
    }

    public void execute(){
        arm.armFloorCone();
    }
    
    public boolean isFinished(){
        return arm.isConeFloorSetpoint();
    }
}
