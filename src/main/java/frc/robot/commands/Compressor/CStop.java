package frc.robot.commands.Compressor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Compressor;


public class CStop extends CommandBase{
    Compressor compressor;

    public CStop(Compressor compressor){
        addRequirements(compressor);
        this.compressor = compressor;
    }

    public void execute(){
        compressor.stop();
    }
    
}
