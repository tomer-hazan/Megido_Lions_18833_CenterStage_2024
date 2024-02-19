package org.firstinspires.ftc.teamcode.firstCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LEDSubsystem extends SubsystemBase {
    DigitalChannel redLED;
    DigitalChannel greenLED;
    DigitalChannel redLED2;
    DigitalChannel greenLED2;
    public LEDSubsystem(HardwareMap hardwareMap){
        redLED = hardwareMap.get(DigitalChannel.class,"red");
        greenLED = hardwareMap.get(DigitalChannel.class,"green");
        redLED.setMode(DigitalChannel.Mode.OUTPUT);
        greenLED.setMode(DigitalChannel.Mode.OUTPUT);
        redLED2 = hardwareMap.get(DigitalChannel.class,"red2");
        greenLED2 = hardwareMap.get(DigitalChannel.class,"green2");
        redLED2.setMode(DigitalChannel.Mode.OUTPUT);
        greenLED2.setMode(DigitalChannel.Mode.OUTPUT);
    }
    public void green(){
        redLED.setState(false);
        greenLED.setState(true);
        redLED2.setState(false);
        greenLED2.setState(true);
    }
    public void red(){
        redLED.setState(true);
        greenLED.setState(false);
        redLED2.setState(true);
        greenLED2.setState(false);
    }
    public void none(){
        redLED.setState(true);
        greenLED.setState(true);
        redLED2.setState(true);
        greenLED2.setState(true);
    }
    public void both(){
        redLED.setState(false);
        greenLED.setState(false);
        redLED2.setState(false);
        greenLED2.setState(false);
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
