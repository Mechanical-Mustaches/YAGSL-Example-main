package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

public class ColorSensor extends SubsystemBase {
    //port 
    private final I2C.Port sensor = I2C.Port.kOnboard;
    //sensor liberrry
    ColorSensorV3 C_sense = new ColorSensorV3(sensor);

 
    //private I2C differentSensor = new I2C(sensor, 0x39);

    public Color detectColor = C_sense.getColor();
    
    ColorSensorV3 detectedColor; // = C_sense.getRawColor();

    private final ColorMatch m_colorMatcher = new ColorMatch();
    public ColorMatchResult match = m_colorMatcher.matchClosestColor(detectColor);
    public String colorString = "Unknown";


    private final Color kPurpleTarget = new Color(0.5, 0, 0.5);
    private final Color kYellowTarget = new Color(0.361, 0.524, 0.113);

    public ColorSensor(){
      // differentSensor.write(0x00, 0b00000011);
       //detectedColor.getRawColor();
    }

    
    public void initialize(){
        m_colorMatcher.addColorMatch(kPurpleTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);
    }



    public void getDetectedColors(){
      //  match.matchClosestColor(detectedColor);

        if(match.color == kPurpleTarget){
            colorString = "Purple";
        }
        else if(match.color == kYellowTarget){
            colorString = "Yellow";
        }
        // else{
        //     colorString = "Unkown";
        // }

      //  return colorString;

    }

    




}
