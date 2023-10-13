package frc.robot.commands.Compressor;

import frc.robot.subsystems.Compressor;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class CRetract extends CommandBase{
    Compressor compressor;

    public CRetract(Compressor compressor){
        addRequirements(compressor);
        this.compressor = compressor;
    }

    public void execute(){
        compressor.reverseCompressor();
    }

    public boolean isFinished(){
        return compressor.isCompressorReversed();
    }



}
