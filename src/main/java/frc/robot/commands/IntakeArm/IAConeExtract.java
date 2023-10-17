package frc.robot.commands.IntakeArm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmIntake;

public class IAConeExtract extends CommandBase{
    ArmIntake armIntake;

    public IAConeExtract(ArmIntake armIntake){
        addRequirements(armIntake);
        this.armIntake = armIntake;
    }

    public void execute(){
        armIntake.extractCone();
    }

    @Override
    public void initialize(){
        armIntake.resetPosition();
    }

    public boolean isFinished(){
        boolean finished = armIntake.isArmExtractingCone();
        if(finished){
            System.out.println(this.getClass().getName() + " is Finished");
            armIntake.stopArmIntake();
        }
        return finished;
    }


}
