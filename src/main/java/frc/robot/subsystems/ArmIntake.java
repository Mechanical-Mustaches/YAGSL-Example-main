package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.RelativeEncoder; 

public class ArmIntake extends SubsystemBase {
    private CANSparkMax intakeMotor = new CANSparkMax(11, MotorType.kBrushless);
    RelativeEncoder sir_eyespy_coder;
    private int intakeCurrentLimit = 25;
    private double intakeOutputPower = 1.0;
    private double intakeCubePower = 0.4;

    private int intakeAmps;
    private double intakePower;
    private int counter = 0;

    
    public ArmIntake(){
        sir_eyespy_coder = intakeMotor.getEncoder();
        sir_eyespy_coder.setPosition(0);
    }

    public void setIntakeMotor(double percent, int amps){
        intakeMotor.set(percent);
    }

    public void resetPosition(){
        sir_eyespy_coder.setPosition(0.0);
    }

    public void stopArmIntake(){
        intakePower = 0;
        intakeAmps = 0;  
        setIntakeMotor(intakePower, intakeAmps);
    }

    public boolean isArmIntakeStopped(){
        double position = sir_eyespy_coder.getPosition();
        if (counter++ % 10 == 0) {
            System.out.println("Intake arm position: " + position);
        }
        double threshold = 0.01;
        return Math.abs(position) < threshold;
    }

    /*
     * CONE INTAKE POSITIONS
     */

    public void intakeCone(){
        intakePower = -intakeOutputPower;
        intakeAmps = intakeCurrentLimit;
        setIntakeMotor(intakePower, intakeAmps);
    }

    public boolean isArmIntakingCone(){
        double position = sir_eyespy_coder.getPosition();
        if(position < -40){
            return true;
        }
        return false;
    }

    public void extractCone(){
        intakePower = intakeOutputPower;
        intakeAmps = -intakeCurrentLimit;
        setIntakeMotor(intakePower, intakeAmps);
    }

    public boolean isArmExtractingCone(){
        double position = sir_eyespy_coder.getPosition();
        if(position > 42){
            return true;
        }
        return false;

    }


    /*
     * CUBE-Y DOOBY DOO INTAKE POSITIONS
     */

    public void intakeCube(){
        intakePower = -intakeCubePower;
        intakeAmps = -intakeCurrentLimit;
        setIntakeMotor(intakePower, intakeAmps);
    }

    public boolean isArmIntakingCube(){
        double position = sir_eyespy_coder.getPosition();
        if(position > 22){
            return true;
        }
        return false;
    }

    public void extractCube(){
        intakePower = -intakeOutputPower;
        intakeAmps = -intakeCurrentLimit;
        setIntakeMotor(intakePower, intakeAmps);
    }

    public boolean isArmExtractingCube(){
        double position = sir_eyespy_coder.getPosition();
        if(position > 60){
            return true;
        }
        return false;
    }

  


} //<-- Keep Brace