package frc.robot.commands.Conveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;

public class CReverse extends CommandBase {
    Conveyor conveyor;

    public CReverse(Conveyor conveyor){
        addRequirements(conveyor);
        this.conveyor = conveyor;
    }

    public void execute(){
        conveyor.reverse();
    }
    
}
