package frc.robot.commands.Compressor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Compressor;

public class CompOut extends CommandBase {
    Compressor compressor;

    public CompOut(Compressor compressor){
        addRequirements(compressor);
        this.compressor = compressor;
    }

    public void execute(){
        compressor.CompressorOut();
    }

    public boolean isFinished(){
        return true;
    }

}
