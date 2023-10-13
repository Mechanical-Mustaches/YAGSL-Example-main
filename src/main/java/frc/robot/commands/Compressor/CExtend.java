package frc.robot.commands.Compressor;

import frc.robot.subsystems.Compressor;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class CExtend extends CommandBase{
    Compressor compressor;

    public CExtend(Compressor compressor){
        addRequirements(compressor);
        this.compressor = compressor;
    }

    public void execute(){
        compressor.fowardCompressor();
    }

    public boolean isFinished(){
        return compressor.isCompressorForward();
    }
    
}
