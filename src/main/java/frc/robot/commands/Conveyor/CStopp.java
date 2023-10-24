package frc.robot.commands.Conveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;

public class CStopp extends CommandBase {
    Conveyor conveyor;

    public CStopp(Conveyor conveyor){
        addRequirements(conveyor);
        this.conveyor = conveyor;
    }

    public void execute(){
        conveyor.stop();
    }

    public boolean isFinished(){
        return true;
        //return conveyor.STOP();
    }
    
}
