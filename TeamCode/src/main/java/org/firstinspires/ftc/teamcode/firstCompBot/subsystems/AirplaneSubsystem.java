package org.firstinspires.ftc.teamcode.firstCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.firstCompBot.Constants.AirplaneConstants;

public class AirplaneSubsystem extends SubsystemBase {
    ServoEx servo;
    Boolean hasLunched;
    public AirplaneSubsystem(HardwareMap hardwareMap){
        servo = new SimpleServo(hardwareMap,"AirPlane", AirplaneConstants.bottom_limit, AirplaneConstants.top_limit);
        servo.setPosition(0);
        hasLunched = false;
    }
    public Boolean hasLunched(){
        return hasLunched;
    }
    public void lunch(){
        hasLunched = true;
    }
    public void setPosition(double position){
        servo.setPosition(position);
    }
}
