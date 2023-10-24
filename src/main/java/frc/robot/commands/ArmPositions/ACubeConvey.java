package frc.robot.commands.ArmPositions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class ACubeConvey extends CommandBase {
    Arm arm;
    
    public ACubeConvey(Arm arm){
        addRequirements(arm);
        this.arm = arm;
    }

    public void execute(){
        arm.armConveyCube();
    }
    public boolean isFinsihed(){
        return true;
    }

}
