package frc.robot.commands.IntakeFloor;

import edu.wpi.first.networktables.FloatArrayEntry;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FloorIntake;

public class IFFart extends CommandBase {
    FloorIntake floorIntake;

    public IFFart(FloorIntake floorIntake){
        addRequirements(floorIntake);
        this.floorIntake = floorIntake;
    }

    public void execute(){
        floorIntake.fartCube();
    }

    public boolean isFinished(){
        return floorIntake.isFartingCube();
    }
    
}
