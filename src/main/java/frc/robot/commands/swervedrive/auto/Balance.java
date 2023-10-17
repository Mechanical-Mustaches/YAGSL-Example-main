package frc.robot.commands.swervedrive.auto;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class Balance extends CommandBase {
    private SwerveSubsystem swerve;
    private int m_AutoBalanceState = 0;
    private boolean isFinished = false;

    /*
     * THIS IS JUST GOING FROM THE DRIVER SIDE!! 
     */

    public Balance(SwerveSubsystem m_drive){
        addRequirements(m_drive);
        this.swerve = m_drive;
    }

    @Override
    public void execute(){
        if(m_AutoBalanceState == 0){
            System.out.println("Running Wheels");
            if(swerve.getPitch().getDegrees() < -8 && swerve.getPose().getX() > 1.5){
                m_AutoBalanceState = 1;
                swerve.drive(new Translation2d(.1, 0), 0, true, true);
            }
            else{
                swerve.drive(new Translation2d(.3, 0), 0, true, true);
            }
        }

        else if(m_AutoBalanceState == 1){
            System.out.println("Can you see me?");
            if(swerve.getPitch().getDegrees() < -4.5){
                swerve.drive(new Translation2d(.1, 0), 0, true, true);
            }
            else{
                swerve.drive(new Translation2d(0 , 0.02), 0, true, false);
            }
        }

        if(swerve.getPose().getX() > 5){
            System.out.println("White line");
            swerve.drive(new Translation2d(0, 0), 0 , true, false);
            isFinished = true;
        }

    }


    @Override
    public void initialize(){
        super.initialize();
        swerve.getSwerveDriveConfiguration(); // Idk if this is the right line of code to use
        System.out.println("I'm on!");
    }

    public boolean isFinished(){
        return isFinished();
    }

    public void end(boolean interrupted){
        isFinished = true;
    }

} // Keep brace
