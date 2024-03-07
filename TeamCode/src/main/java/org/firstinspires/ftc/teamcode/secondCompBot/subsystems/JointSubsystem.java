package org.firstinspires.ftc.teamcode.secondCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.JointConstants.groundPos;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.JointConstants.rotation_limit;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.JointConstants.rotation_max;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.JointConstants.rotation_min;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.JointConstants.rotation_start;

public class JointSubsystem extends SubsystemBase {
    public final ServoEx rotationServo1;
    public final ServoEx rotationServo2;
    private double defaultPose;
    public JointSubsystem(HardwareMap hardwareMap){
        rotationServo1 = new SimpleServo(hardwareMap,"flip servo 1",rotation_start,rotation_limit);
        rotationServo2 = new SimpleServo(hardwareMap,"flip servo 2",rotation_start,rotation_limit);
        rotationServo2.setInverted(false);
        defaultPose = groundPos;
    }
    public void turnToAngle(double angle){
        rotationServo1.turnToAngle(angle);
        rotationServo2.turnToAngle(angle);
    }
    public void setPosWithLimits(double pos){
        if(pos<rotation_min)pos=rotation_min;
        if(pos>rotation_max)pos=rotation_max;
        rotationServo1.setPosition(pos);
        rotationServo2.setPosition(pos);
    }
    public void setPos(double pos){
        rotationServo1.setPosition(pos);
        rotationServo2.setPosition(pos);
    }
    public double getPos(){
        return rotationServo1.getPosition();
    }
    public double getDefaultPose(){
        return defaultPose;
    }
    public void setDefaultPose(double defaultPose){
        this.defaultPose=defaultPose;
    }
    public double getAngle(){
        return rotationServo1.getAngle();
    }
}
