package frc.robot.commands.IntakeFloor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FloorIntake;

public class IFCollect extends CommandBase {
    FloorIntake floorIntake;

    public IFCollect(FloorIntake floorIntake){
        addRequirements(floorIntake);
        this.floorIntake = floorIntake;
    }

    public void execute(){
        floorIntake.collectCube();
    }

    public boolean isFinished(){
        return true;
    }
    
}
