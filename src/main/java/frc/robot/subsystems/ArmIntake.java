package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.RelativeEncoder; 

public class ArmIntake extends SubsystemBase {
    private CANSparkMax intakeMotor = new CANSparkMax(11, MotorType.kBrushless);
    RelativeEncoder AIencoder;
    private int intakeCurrentLimit = 25;
    private double intakeOutputPower = 1.0;
    private double intakeCubePower = 0.4;

    private int intakeAmps;
    private double intakePower;
    private int counter = 0;

    
    public ArmIntake(){
        AIencoder = intakeMotor.getEncoder();
        AIencoder.setPosition(0);
    }

    /*
     * TELEOP COMMANDS
     */

    public void setIntakeMotor(double percent, int amps){
        intakeMotor.set(percent);
    }

    public void resetPosition(){
        AIencoder.setPosition(0.0);
    }

    public void stopArmIntake(){
        intakePower = 0;
        intakeAmps = 0;  
        setIntakeMotor(intakePower, intakeAmps);
    }

    public boolean isArmIntakeStopped(){
        return true;
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
        double position = AIencoder.getPosition();
        if(position < -40){
            return true;
        }
        return false;
    }

    public void extractCone(){
        intakePower = intakeOutputPower;
        intakeAmps = -intakeCurrentLimit;
        setIntakeMotor(intakePower, intakeAmps);
        //System.out.println("isExtractingCone position: " + AIencoder.getPosition());

    }

    public boolean isArmExtractingCone(){
        double position = AIencoder.getPosition();
       // System.out.println("isExtractingCone position: " + position);
        if(position > 35){
            return true;
        }
        return false;

    }


    /*
     * CUBE-Y DOOBY DOO INTAKE POSITIONS
     */

    public void intakeCube(){
        intakePower = intakeCubePower; //Both - 
        intakeAmps = intakeCurrentLimit;
        setIntakeMotor(intakePower, intakeAmps);
    }

    public boolean isArmIntakingCube(){
        double position = AIencoder.getPosition();
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
        double position = AIencoder.getPosition();
        // System.out.println("isExtractingCone position: " + position);
         if(position < -35){
             return true;
         }
         return false;
    }

  


} //<-- Keep Brace
