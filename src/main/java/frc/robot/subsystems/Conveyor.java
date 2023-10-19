package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder; 

public class Conveyor extends SubsystemBase {
    private CANSparkMax conveyMotor = new CANSparkMax(13, MotorType.kBrushless);
    private double speed = 0.6;
    RelativeEncoder sir_eyespy_coder;

    public Conveyor(){
        sir_eyespy_coder = conveyMotor.getEncoder();
        sir_eyespy_coder.setPosition(0);
    }

    /*
     * Autonomous Commands
     */
    public int setEncoderPos(int pos){
        sir_eyespy_coder.setPosition(pos);
        return pos;
        
    }

    public void moveConCube(){
        conveyMotor.set(0.4);
    }

    public boolean cubeyMove(){
        double position = sir_eyespy_coder.getPosition();
        if(position > 10){
            return true;
        }
        return false;
    }


    /*
     * Teleop Commands
     */

    public void goConveyor(){
        conveyMotor.set(speed);
    }

    public boolean isConveyorGoing(){
        double position = sir_eyespy_coder.getPosition();
        if(position > 12){
            return true;
        }
        return false;
    }

    public boolean isGoGoGoing(){
        double position = sir_eyespy_coder.getPosition();
        if(position > 175){
            return true;
        }
        return false;
    }

    public void stop(){
        conveyMotor.set(0);
    }

    public boolean isFinished(){
        double position = sir_eyespy_coder.getPosition();
        if(position > 143){
            return true;
        }
        return false;
    }

    public boolean conveyorStop(){
        double position = sir_eyespy_coder.getPosition();
        double threshold = 0.01;
        return Math.abs(position) < threshold;
    }

    public boolean STOP(){
        double position = sir_eyespy_coder.getPosition();
        if(position > 160){
            return true;
        }
        return false;
    }

    public void reverse(){
        conveyMotor.set(-speed);
    }

}// <-- Keep brace
