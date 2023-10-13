package frc.robot.commands.ArmPositions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class ACubeHuman extends CommandBase {
    Arm arm;

    public ACubeHuman(Arm arm){
        this.arm = arm;
    }

    public void execute(){
        arm.armHumanCube();
    }
}
