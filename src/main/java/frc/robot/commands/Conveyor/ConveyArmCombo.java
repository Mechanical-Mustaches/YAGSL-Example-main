package frc.robot.commands.Conveyor;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;

public class ConveyArmCombo extends CommandBase {
      Conveyor conveyor;

    public ConveyArmCombo(Conveyor conveyor){
        addRequirements( conveyor);
        this.conveyor = conveyor;
    }

    @Override
    public void initialize(){
        conveyor.setEncoderPos(0);
    }


    public void execute(){
        conveyor.moveConCube();
    }

    public boolean isFinished(){
        return conveyor.cubeyMove();
    }

    

    
    
}
