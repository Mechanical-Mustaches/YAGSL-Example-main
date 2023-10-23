package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder; 
import frc.robot.subsystems.ArmIntake;

public class Conveyor extends SubsystemBase {
    public CANSparkMax conveyMotor = new CANSparkMax(13, MotorType.kBrushless);
    private double speed = 0.6;
    public RelativeEncoder ConveyEncoder;
    ArmIntake armIntake;

    public Conveyor(){
        ConveyEncoder = conveyMotor.getEncoder();
        ConveyEncoder.setPosition(0);
        System.out.println(ConveyEncoder.getPosition());
    }

    @Override
    public void periodic(){
       System.out.println(ConveyEncoder.getPosition());
    }

    /*
     * Autonomous Commands
     */
    
    public int setEncoderPos(int pos){
        ConveyEncoder.setPosition(pos);
        return pos;
        
    }

    public void moveConCube(){
        conveyMotor.set(0.4);
        armIntake.cubeyMovey();
    }

    public boolean cubeyMove(){
        double position = ConveyEncoder.getPosition();
        if(position > 37){
            conveyMotor.set(0);
            armIntake.stopArmIntake();
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
        double position = ConveyEncoder.getPosition();
        if(position > 12){
            return true;
        }
        return false;
    }

    public boolean isGoGoGoing(){
        double position = ConveyEncoder.getPosition();
        if(position > 175){
            return true;
        }
        return false;
    }

    public void stop(){
        conveyMotor.set(0);
    }

    public boolean isFinished(){
        double position = ConveyEncoder.getPosition();
        if(position > 143){
            return true;
        }
        return false;
    }

    public boolean conveyorStop(){
        double position = ConveyEncoder.getPosition();
        double threshold = 0.01;
        return Math.abs(position) < threshold;
    }

    public boolean STOP(){
        double position = ConveyEncoder.getPosition();
        if(position > 160){
            return true;
        }
        return false;
    }

    public void reverse(){
        conveyMotor.set(-speed);
    }

}// <-- Keep brace
