package frc.robot.commands.ArmPositions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class AConeHigh extends CommandBase{
    Arm arm;

    public AConeHigh(Arm arm){
        addRequirements(arm);
        this.arm = arm;
    }

    public void execute(){
        arm.armHighCone();
    }

    public boolean isFinished(){
        return arm.isConeHighSetPoint();
    }
    
    
}
