package frc.robot.commands.ArmPositions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class ACollapse extends CommandBase {
    Arm arm;

    public ACollapse(Arm arm){
        addRequirements(arm);
        this.arm = arm;
    }

    public void execute(){
        arm.armCollapse();
    }

    public boolean isFinished(){
        return arm.isCollapseing();
    }
    
}
