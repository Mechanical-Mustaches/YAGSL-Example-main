package frc.robot.commands.Conveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmIntake;
import frc.robot.subsystems.Conveyor;

public class ConveyArmCombo extends CommandBase {
    ArmIntake armIntake;
    Conveyor conveyor;

    public ConveyArmCombo(ArmIntake armIntake, Conveyor conveyor){
        addRequirements(armIntake, conveyor);
        this.armIntake = armIntake;
        this.conveyor = conveyor;
    }

    @Override
    public void initialize(){
        conveyor.setEncoderPos(0);
        armIntake.setArmEncoderPos(0);
    }


    public void execute(){
        conveyor.cubeyMove();
        armIntake.cubeyIntake(); 
    }

    
    
}
