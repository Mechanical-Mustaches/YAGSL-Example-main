package frc.robot.commands.Conveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;

public class CGo extends CommandBase{
    Conveyor conveyor;

    public CGo(Conveyor conveyor){
        addRequirements(conveyor);
        this.conveyor = conveyor;
    }

    public void execute(){
        conveyor.goConveyor();
    }

    public boolean isFinished(){
        return conveyor.isConveyorGoing();
    }
    
}
