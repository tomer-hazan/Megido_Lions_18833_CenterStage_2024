package org.firstinspires.ftc.teamcode.firstCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LEDSubsystem extends SubsystemBase {
    DigitalChannel redLED;
    DigitalChannel greenLED;
    public LEDSubsystem(HardwareMap hardwareMap){
        redLED = hardwareMap.get(DigitalChannel.class,"red");
        greenLED = hardwareMap.get(DigitalChannel.class,"green");
        redLED.setMode(DigitalChannel.Mode.OUTPUT);
        greenLED.setMode(DigitalChannel.Mode.OUTPUT);
    }
    public void green(){
        redLED.setState(false);
        greenLED.setState(true);
    }
    public void red(){
        redLED.setState(true);
        greenLED.setState(false);
    }
    public void none(){
        redLED.setState(true);
        greenLED.setState(true);
    }
    public void both(){
        redLED.setState(false);
        greenLED.setState(false);
    }
    public void makeColor(int color){
        switch (color%4){
            case 0:
                green();
                break;
            case 1:
                red();
                break;
            case 2:
                both();
                break;
            case 3:
                none();
                break;
        }
    }
}
