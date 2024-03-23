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
    public final ServoEx rotationServo;
    private double defaultPose;
    public JointSubsystem(HardwareMap hardwareMap){
        rotationServo = new SimpleServo(hardwareMap,"flip servo",rotation_start,rotation_limit);
        defaultPose = groundPos;
    }
    public void turnToAngle(double angle){
        rotationServo.turnToAngle(angle);
    }
    public void setPosWithLimits(double pos){
        if(pos<rotation_min)pos=rotation_min;
        if(pos>rotation_max)pos=rotation_max;
        rotationServo.setPosition(pos);
    }
    public void setPos(double pos){
        rotationServo.setPosition(pos);
    }
    public double getPos(){
        return rotationServo.getPosition();
    }
    public double getDefaultPose(){
        return defaultPose;
    }
    public void setDefaultPose(double defaultPose){
        this.defaultPose=defaultPose;
    }
    public double getAngle(){
        return rotationServo.getAngle();
    }
}
