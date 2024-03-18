package org.firstinspires.ftc.teamcode.secondCompBot.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Config
public class HookSubsystem extends SubsystemBase {
    Servo rightServo;
    MotorEx suspension;
    Servo leftServo;
    public static double rightPos=0.56;
    public HookSubsystem(HardwareMap hardwareMap){
        rightServo = hardwareMap.get(Servo.class,"right hook");
        leftServo = hardwareMap.get(Servo.class,"left hook");
        suspension = new MotorEx(hardwareMap,"suspension");
        suspension.setInverted(true);
        suspension.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        leftServo.setDirection(Servo.Direction.REVERSE);//toDo check
    }

    public double getHookPosition() {
        return rightServo.getPosition();
    }
    public void raiseHook(){
        rightServo.setPosition(rightPos);
        leftServo.setPosition(0.3);
    }
    public void lowerHook(){
        rightServo.setPosition(1);
        leftServo.setPosition(1);
    }
    public void pullUp(){suspension.set(1);}
    public void pullDown(){suspension.set(-1);}
    public void pull(double power){suspension.set(power);}
    public void stopPulling(){suspension.set(0);}
}
