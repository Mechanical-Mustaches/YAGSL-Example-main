package frc.robot.commands.ArmPositions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class AConeMid extends CommandBase {
    Arm arm;

    public AConeMid(Arm arm){
        addRequirements(arm);
        this.arm = arm;
    }

    public void execute(){
        arm.isConeMidSetpoint();
    }

    public boolean isFinsihed(){
        return arm.isConeMidSetpoint();
    }
    
    
}
