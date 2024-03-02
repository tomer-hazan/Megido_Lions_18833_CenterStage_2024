package org.firstinspires.ftc.teamcode.secondCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.secondCompBot.Constants;

public class LEDSubsystem extends SubsystemBase {
    DigitalChannel redLeft;
    DigitalChannel greenLeft;
    DigitalChannel redRight;
    DigitalChannel greenRight;
    public LEDSubsystem(HardwareMap hardwareMap){
        redLeft = hardwareMap.get(DigitalChannel.class,"red left");
        greenLeft = hardwareMap.get(DigitalChannel.class,"green left");
        redLeft.setMode(DigitalChannel.Mode.OUTPUT);
        greenLeft.setMode(DigitalChannel.Mode.OUTPUT);
        redRight = hardwareMap.get(DigitalChannel.class,"red right");
        greenRight = hardwareMap.get(DigitalChannel.class,"green right");
        redRight.setMode(DigitalChannel.Mode.OUTPUT);
        greenRight.setMode(DigitalChannel.Mode.OUTPUT);
    }
    public void green(){
        redLeft.setState(false);
        greenLeft.setState(true);
        redRight.setState(false);
        greenRight.setState(true);
    }
    public void red(){
        redLeft.setState(true);
        greenLeft.setState(false);
        redRight.setState(true);
        greenRight.setState(false);
    }
    public void none(){
        redLeft.setState(true);
        greenLeft.setState(true);
        redRight.setState(true);
        greenRight.setState(true);
    }
    public void both(){
        redLeft.setState(false);
        greenLeft.setState(false);
        redRight.setState(false);
        greenRight.setState(false);
    }
    public void greenLeft(){
        redLeft.setState(false);
        greenLeft.setState(true);
    }
    public void redLeft(){
        redLeft.setState(true);
        greenLeft.setState(false);
    }
    public void noneLeft(){
        redLeft.setState(true);
        greenLeft.setState(true);
    }
    public void bothLeft(){
        redLeft.setState(false);
        greenLeft.setState(false);
    }
    public void greenRight(){
        redRight.setState(false);
        greenRight.setState(true);
    }
    public void redRight(){
        redRight.setState(true);
        greenRight.setState(false);
    }
    public void noneRight(){
        redRight.setState(true);
        greenRight.setState(true);
    }
    public void bothRight(){
        redRight.setState(false);
        greenRight.setState(false);
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
    public void makeColorLeft(Constants.GameElements.Pixals color){
        switch (color){
            case GREEN:
                greenLeft();
                break;
            case PURPLE:
                redLeft();
                break;
            case YELLOW:
                bothLeft();
                break;
            case NULL:
                noneLeft();
                break;
        }
    }

    public void makeColorRight(Constants.GameElements.Pixals color){
        switch (color){
            case GREEN:
                greenRight();
                break;
            case PURPLE:
                redRight();
                break;
            case YELLOW:
                bothRight();
                break;
            case NULL:
                noneRight();
                break;
        }
    }
}
