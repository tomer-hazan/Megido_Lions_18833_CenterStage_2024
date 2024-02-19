package org.firstinspires.ftc.teamcode.firstCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AirplaneSubsystem extends SubsystemBase {
    CRServo servo;
    public AirplaneSubsystem(HardwareMap hardwareMap){
        servo = new CRServo(hardwareMap,"airplane servo");
    }
    public void lunch(){
        servo.set(1);
    }
    public void stop(){
        servo.stop();
    }
}
