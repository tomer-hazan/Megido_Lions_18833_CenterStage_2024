package org.firstinspires.ftc.teamcode.secondCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class HookSubsystem extends SubsystemBase {
    Servo rightServo;
    MotorEx suspension;
    Servo leftServo;
    public HookSubsystem(HardwareMap hardwareMap){
        rightServo = hardwareMap.get(Servo.class,"right hook");
        leftServo = hardwareMap.get(Servo.class,"left hook");
        suspension = new MotorEx(hardwareMap,"suspension");
    }

    public double getHookPosition() {
        return rightServo.getPosition();
    }
    public void raiseHook(){
        rightServo.setPosition(1);
        leftServo.setPosition(1);
    }
    public void lowerHook(){
        rightServo.setPosition(0);
        leftServo.setPosition(0);
    }
    public void pullUp(){suspension.set(1);}
    public void pullDown(){suspension.set(-1);}
    public void pull(double power){suspension.set(power);}
    public void stopPulling(){suspension.set(0);}
}
