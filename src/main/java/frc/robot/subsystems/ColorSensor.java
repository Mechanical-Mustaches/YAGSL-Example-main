package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

public class ColorSensor extends SubsystemBase {
    private final I2C.Port sensor = I2C.Port.kOnboard;
    String colorString;


    ColorSensorV3 C_sense = new ColorSensorV3(sensor);

    private final ColorMatch m_colorMatcher = new ColorMatch();

    private final Color kPurpleTarget = new Color(0.5, 0, 0.5);
    private final Color kYellowTarget = new Color(0.361, 0.524, 0.113);

    public ColorSensor(){
       
    }

    
    public void initialize(){
        m_colorMatcher.addColorMatch(kPurpleTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);
    }

    public String getDetectedColors(){
        Color detectedColor = C_sense.getColor();
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if(match.color == kPurpleTarget){
            colorString = "Purple";
        }
        else if(match.color == kYellowTarget){
            colorString = "Yellow";
        }
        else{
            colorString = "Unkown";
        }

        return colorString;

    }

    




}
