package frc.robot.commands.Compressor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Compressor;


public class CSTOP extends CommandBase{
    Compressor compressor;

    public CSTOP(Compressor compressor){
        addRequirements(compressor);
        this.compressor = compressor;
    }

    public void execute(){
        compressor.stop();
    }
    
}
