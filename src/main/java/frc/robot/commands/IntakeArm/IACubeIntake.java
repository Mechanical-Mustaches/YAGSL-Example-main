package frc.robot.commands.IntakeArm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmIntake;

public class IACubeIntake extends CommandBase{
    ArmIntake armIntake;

    public IACubeIntake(ArmIntake armIntake){
        addRequirements(armIntake);
        this.armIntake = armIntake;
    }

    public void execute(){
        armIntake.intakeCube();
    }

    public boolean isFinished(){
        return armIntake.isArmIntakingCube();
    }
}
