package frc.robot.commands.IntakeFloor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FloorIntake;

public class IFStop extends CommandBase{
    FloorIntake floorIntake;

    public IFStop(FloorIntake floorIntake){
        addRequirements(floorIntake);
        this.floorIntake = floorIntake;
    }

    public void execute(){
        floorIntake.stopIntake();
    }

    public boolean isFinished(){
        return true;
    }
}
