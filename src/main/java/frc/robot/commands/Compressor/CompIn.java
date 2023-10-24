package frc.robot.commands.Compressor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Compressor;

public class CompIn extends CommandBase {
    Compressor compressor;

    public CompIn(Compressor compressor){
        addRequirements(compressor);
        this.compressor = compressor;
    }

    public void execute(){
        compressor.CompressorIn();
    }

    public boolean isFinished(){
        return true;
    }
    
}
