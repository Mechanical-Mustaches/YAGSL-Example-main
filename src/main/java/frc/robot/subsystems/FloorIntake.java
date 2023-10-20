package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.RelativeEncoder; 

public class FloorIntake extends SubsystemBase{
    private CANSparkMax floorMotor = new CANSparkMax(12, MotorType.kBrushless);
    private double superSpeed = 0.4;
    RelativeEncoder sir_eyespy_coder;

    public FloorIntake(){
        sir_eyespy_coder = floorMotor.getEncoder();
        sir_eyespy_coder.setPosition(0);
    }

    public void collectCube(){
        floorMotor.set(-superSpeed);
    }

    public boolean isCollectingCube(){
        double position = sir_eyespy_coder.getPosition();
        if(position < -30){
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
}
