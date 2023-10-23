package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.RelativeEncoder; 

public class FloorIntake extends SubsystemBase{
    private CANSparkMax floorMotor = new CANSparkMax(12, MotorType.kBrushless);
    private DoubleSolenoid leftDS = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 0);
    private DoubleSolenoid rightDS = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 6, 7);

    private double superSpeed = 0.4;
    RelativeEncoder sir_eyespy_coder;

    public FloorIntake(){
        sir_eyespy_coder = floorMotor.getEncoder();
        sir_eyespy_coder.setPosition(0);
        leftDS.set(Value.kReverse);
        rightDS.set(Value.kReverse);
    }

    public void collectCube(){
        floorMotor.set(-superSpeed);
        leftDS.set(Value.kForward);
        rightDS.set(Value.kForward);
    }

    public boolean isCollectingCube(){
        double position = sir_eyespy_coder.getPosition();
        if(position < -30){
            return true;
        }
        return false;
    }

    public void reverseIntake(){
        leftDS.set(Value.kReverse);
        rightDS.set(Value.kReverse);
    }

    public boolean isIntakeReversed(){
        if(leftDS.getRevChannel() == 0){
            return true;
        }
        return false;
    }

    public void stopIntake(){
        floorMotor.set(0);
    }

    public boolean isIntakeOff(){
        double position = sir_eyespy_coder.getPosition();
        if(position > -300.0){
            return true;
        }
        return false;
    }


    public void fartCube(){
        floorMotor.set(superSpeed);
    }

    public boolean isFartingCube(){
        double position = sir_eyespy_coder.getPosition();
        if(position > 80.0){
            return true;
        }
        return false; 
    }

   
}
