package org.firstinspires.ftc.teamcode.firstCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.firstCompBot.Constants.HookConstants;


public class HookSubsystem extends SubsystemBase {
    ServoEx rightServo;
    //MotorEx suspension;
    public HookSubsystem(HardwareMap hardwareMap){
        rightServo = new SimpleServo(hardwareMap,"right hook", HookConstants.bottom_limit,HookConstants.top_limit);
        //suspension = new MotorEx(hardwareMap,"suspension");
    }

    public double getHookPosition() {
        return rightServo.getPosition();
    }
    public void setHookPosition(double position){
        rightServo.setPosition(position);
    }
//    public double getHeight(){
//        return HookConstants.ticksToMeters(suspension.getCurrentPosition());
//    }
//    public void setSuspensionPower(double power){
//        suspension.set(power);
//    }
//    public void setSuspensionDirection(boolean inverted){
//        suspension.setInverted(inverted);
//    }
}
