package org.firstinspires.ftc.teamcode.BasicSample.subsystems;

import android.util.Log;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.BasicSample.lib.Util;

import static org.firstinspires.ftc.teamcode.BasicSample.Constants.LiftConstants.lower_plate_height;
import static org.firstinspires.ftc.teamcode.BasicSample.Constants.LiftConstants.meters2ticks;
import static org.firstinspires.ftc.teamcode.BasicSample.Constants.LiftConstants.number_of_motors;
import static org.firstinspires.ftc.teamcode.BasicSample.Constants.LiftConstants.ticks2meters;
import static org.firstinspires.ftc.teamcode.BasicSample.Constants.LiftConstants.top_height;

public class LiftSubsystem extends SubsystemBase{
    final MotorGroup motors;
    final MotorEx   motor1;
    final MotorEx motor2;
    final MotorEx motor3;
    RevTouchSensor bottomLimitSwitch;
    double encoderOffset;
    public LiftSubsystem(HardwareMap hardwareMap){
        this.motor1 = new MotorEx(hardwareMap,"LiftMotor1");
        this.motor2 = new MotorEx(hardwareMap,"LiftMotor2");
        this.motor3 = new MotorEx(hardwareMap,"LiftMotor3");
        motors = new MotorGroup(motor1,motor2,motor3);
        bottomLimitSwitch = hardwareMap.get(RevTouchSensor.class,"LiftLimit");
        motors.resetEncoder();
        motors.setRunMode(Motor.RunMode.RawPower);

        motors.set(0);
        motors.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motor1.setInverted(true);
        motor2.setInverted(false);
        motor3.setInverted(true);
        encoderOffset = meters2ticks(lower_plate_height);
    }

    @Override
    public void periodic() {
        if (bottomLimitSwitch.isPressed()) {
            encoderOffset = meters2ticks(lower_plate_height) - (motor1.getCurrentPosition()+motor2.getCurrentPosition()+motor3.getCurrentPosition())/number_of_motors;
        }

    }

    public void setRunMode(Motor.RunMode mode){
        motors.setRunMode(mode);
    }

    public double getHeight(){
        return ticks2meters((motor1.getCurrentPosition()+motor2.getCurrentPosition()+motor3.getCurrentPosition())/number_of_motors + encoderOffset);
    }

    public boolean isTop(){
        return getHeight()>=top_height;
    }

    public boolean isBottom(){
        return bottomLimitSwitch.isPressed();
    }

    public void setPower(double power){
        setRunMode(Motor.RunMode.RawPower);
        motors.set(power);
    }
    public double getencoderOffSet(){return encoderOffset;}

}
