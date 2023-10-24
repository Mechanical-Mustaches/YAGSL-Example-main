package frc.robot.commands.IntakeArm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmIntake;

public class IAConeIntake extends CommandBase {
    ArmIntake armIntake;

    public IAConeIntake(ArmIntake armIntake){
        addRequirements(armIntake);
        this.armIntake = armIntake;
    }

    public void execute(){
        armIntake.intakeCone();
    }

    public boolean isFinished(){
        return true;
        //return armIntake.isArmIntakingCone();
    }
    
}
