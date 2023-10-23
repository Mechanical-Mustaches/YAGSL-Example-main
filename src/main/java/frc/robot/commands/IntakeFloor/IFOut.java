package frc.robot.commands.IntakeFloor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FloorIntake;

public class IFOut extends CommandBase {
    FloorIntake floorIntake;

    public IFOut(FloorIntake floorIntake){
        addRequirements(floorIntake);
        this.floorIntake = floorIntake;
    }

    public void exectue(){
        floorIntake.reverseIntake();
    }

    public boolean isFinished(){
        return floorIntake.isIntakeReversed();
    }
    
}
