package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase{
    private CANSparkMax armMotor = new CANSparkMax(10, MotorType.kBrushless);
    private double armOutputPower = 0.025;
    private double armPower;
    SparkMaxPIDController PID;
    private double kDt = 0.02;
    RelativeEncoder sir_eyespy_coder;
    TrapezoidProfile.Constraints m_Constraints = new TrapezoidProfile.Constraints(300, 150);
    ProfiledPIDController controller = new ProfiledPIDController(0.025, 0.005, 0, m_Constraints, kDt);

    public Arm(){
        armMotor.restoreFactoryDefaults();
        sir_eyespy_coder = armMotor.getEncoder();
        PID = armMotor.getPIDController();
    }

    @Override
    public void periodic(){
        sir_eyespy_coder = armMotor.getEncoder();
    }

    public void setArmMotor(double percent){
        armMotor.set(percent);
    }

    public void armCollapse_Test(){
        armPower = armOutputPower;
        setArmMotor(armPower);
    }

    public void armExtend_Test(){
        armPower = -armOutputPower;
        setArmMotor(armPower);
    }
 
    public void armHold(){
        armPower = 0;
        setArmMotor(armPower);
    }

    public void armCollapse(){
        armMotor.set(controller.calculate(sir_eyespy_coder.getPosition(), 3));
    }

    public boolean isCollapseing(){
        if(sir_eyespy_coder.getPosition() < 6){
            return true;
        }
        return false;
    }

    /*
     * CONE POSITIONS
     */

    public void armHighCone(){
        armMotor.set(controller.calculate(sir_eyespy_coder.getPosition(), 32));
    }

    public boolean isConeHighSetPoint(){
        if(sir_eyespy_coder.getPosition() > 31){ 
            return true;
        }
       return false;
    }

    public void armMidCone(){
        armMotor.set(controller.calculate(sir_eyespy_coder.getPosition(), 15));
    }

    public boolean isConeMidSetpoint(){
        if(sir_eyespy_coder.getPosition() > 15){ 
            return true;
        }
        return false;
    }

    public void armFloorCone(){
        armMotor.set(controller.calculate(sir_eyespy_coder.getPosition(), 5));
    }

    public boolean isConeFloorSetpoint(){
        if(sir_eyespy_coder.getPosition() > 10){
            return true;
        }
        return false; 
    }

    public void armHumanCone(){
        armMotor.set(controller.calculate(sir_eyespy_coder.getPosition(), 32));
    }

    /*
     * CUBE-Y DOOBY DO POSITIONS
     */

    public void armCubeHigh(){
       armMotor.set(controller.calculate(sir_eyespy_coder.getPosition(), 32));
    }

    public boolean isHighCubeSetpoint(){
        if(sir_eyespy_coder.getPosition() > 32){
            return true;
        }
        return false;   
    }

    public void armCubeMid(){
        armMotor.set(controller.calculate(sir_eyespy_coder.getPosition(), 15));
    }

    public boolean isMidCubeSetpoint(){
        if(sir_eyespy_coder.getPosition() > 23){
            return true;
        }
        return false;
    }

    public void armCubeFloor(){
        armMotor.set(controller.calculate(sir_eyespy_coder.getPosition(), 11));
    }

    public boolean isCubeFloorSetpoint(){
        if(sir_eyespy_coder.getPosition() > 11){
            return true; 
        }
        return false;
    }

    public void armHumanCube(){
        armMotor.set(controller.calculate(sir_eyespy_coder.getPosition(), 31)); 
    }

    public void armConveyCube(){
        armMotor.set(controller.calculate(sir_eyespy_coder.getPosition(), -1));
    }




} //<-- Keep Brace
