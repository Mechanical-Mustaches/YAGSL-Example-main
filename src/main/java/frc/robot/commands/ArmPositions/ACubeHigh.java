package frc.robot.commands.ArmPositions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class ACubeHigh extends CommandBase {
    Arm arm;

    public ACubeHigh(Arm arm){
        addRequirements(arm);
        this.arm = arm;
    }

    public void execute(){
        arm.armCubeHigh();
    }

    public boolean isFinished(){
        return arm.isHighCubeSetpoint();
    }
}
