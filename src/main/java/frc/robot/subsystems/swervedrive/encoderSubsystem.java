package frc.robot.subsystems.swervedrive;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class encoderSubsystem extends SubsystemBase
{
    private CANSparkMax sparkmax = new CANSparkMax(0, MotorType.kBrushless);
    private AbsoluteEncoder encoder = sparkmax.getAbsoluteEncoder(Type.kDutyCycle);

    @Override
    public void periodic()
    {
        SmartDashboard.putNumber("encoder",encoder.getPosition());
    }
}