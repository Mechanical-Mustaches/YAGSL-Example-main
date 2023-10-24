package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Compressor extends SubsystemBase {
    private DoubleSolenoid leftDS = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 0);
    private DoubleSolenoid rightDS = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 6, 7);

    public Compressor(){
        leftDS.set(Value.kReverse);
        rightDS.set(Value.kReverse);;
    }

    public void CompressorOut(){
        leftDS.set(Value.kForward);
        rightDS.set(Value.kForward);
    }

    public boolean isCompresorOut(){
        if(leftDS.getRevChannel() == 1){
            return true;
        }
        return false;
    }

    public void CompressorIn(){
        leftDS.set(Value.kReverse);
        rightDS.set(Value.kReverse);
    }

    public boolean isCompressorIn(){
        if(leftDS.getRevChannel() == 0){
            return true;
        }
        return false;
    }



}
