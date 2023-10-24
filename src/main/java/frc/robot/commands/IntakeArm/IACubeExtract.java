package frc.robot.commands.IntakeArm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmIntake;

public class IACubeExtract extends CommandBase{
    ArmIntake armIntake;

    public IACubeExtract(ArmIntake armIntake){
        addRequirements(armIntake);
        this.armIntake = armIntake;
    }

    public void execute(){
        armIntake.extractCube();
    }

    @Override
    public void initialize(){
        armIntake.resetPosition();
    }

    public boolean isFinished(){
        boolean finished = armIntake.isArmExtractingCube();
        if(finished){
            System.out.println(this.getClass().getName() + " is Finished");
            armIntake.stopArmIntake();
        }
        return finished;
    }


}
