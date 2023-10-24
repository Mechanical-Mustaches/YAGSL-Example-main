package frc.robot.commands.IntakeArm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmIntake;

public class IAStop extends CommandBase {
    ArmIntake armIntake;

    public IAStop(ArmIntake armIntake){
        addRequirements(armIntake);
        this.armIntake = armIntake;
    }

    public void execute(){
        armIntake.stopArmIntake();
    }

    public boolean isFinsihed(){
        return true;
    }
}
