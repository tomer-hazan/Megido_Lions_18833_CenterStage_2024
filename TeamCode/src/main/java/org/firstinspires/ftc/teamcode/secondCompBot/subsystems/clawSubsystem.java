package org.firstinspires.ftc.teamcode.secondCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SensorColor;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.Positions;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.rotation_limit;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.rotation_start;

public class clawSubsystem extends SubsystemBase {
    public final SensorColor colorSensorLeft;
    public final SensorColor colorSensorRight;
    public final ServoEx rotationServo1;
    public final ServoEx rotationServo2;
    public final Servo leftClaw;//continuous
    public final Servo rightClaw;
    public clawSubsystem(HardwareMap hardwareMap){
        this.rightClaw = hardwareMap.get(Servo.class,"right claw");
        this.leftClaw = hardwareMap.get(Servo.class,"left claw");
        rotationServo1 = new SimpleServo(hardwareMap,"claw rotation servo 1",rotation_start,rotation_limit);
        rotationServo2 = new SimpleServo(hardwareMap,"claw rotation servo 2",rotation_start,rotation_limit);
        colorSensorLeft = new SensorColor(hardwareMap,"color sensor left");
        colorSensorRight = new SensorColor(hardwareMap,"color sensor right");
        colorSensorLeft.getARGB();
    }

    public void openOrCloseLeft(Positions position){
        switch (position){
            case CLOSE:
                leftClaw.setPosition(0);
                break;
            case OPEN:
                leftClaw.setPosition(1);
                break;
        }
    }
    public void openOrCloseRight(Positions position){
        switch (position){
            case CLOSE:
                rightClaw.setPosition(0);
                break;
            case OPEN:
                rightClaw.setPosition(1);
                break;
        }
    }
    public void turnToAngle(double angle){
        rotationServo1.turnToAngle(angle);
        rotationServo2.turnToAngle(angle);
    }
    public void setPos(double pos){
        rotationServo1.setPosition(pos);
        rotationServo2.setPosition(pos);
    }
    public  int[] getLeftARGB(){return colorSensorLeft.getARGB();}
    public  int[] getRightARGB(){return colorSensorRight.getARGB();}



}
