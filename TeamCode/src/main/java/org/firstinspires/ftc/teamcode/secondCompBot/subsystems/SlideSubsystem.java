package org.firstinspires.ftc.teamcode.secondCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SlideSubsystem extends SubsystemBase{
    public final MotorEx   motor;
    RevTouchSensor limit;
    public SlideSubsystem(HardwareMap hardwareMap){
        this.motor = new MotorEx(hardwareMap,"slide");
        motor.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.encoder.setDirection(Motor.Direction.FORWARD);
        motor.resetEncoder();
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motor.set(0);
        motor.setInverted(false);
        limit = hardwareMap.get(RevTouchSensor.class,"slide limit switch");
    }

    @Override
    public void periodic() {
        if(limit.isPressed())resetEncoders();
    }

    public void setRunMode(Motor.RunMode mode){
        motor.setRunMode(mode);
    }

    public int getHeight(){
        return motor.getCurrentPosition() ;
    }

    public void setPower(double power){
        motor.set(power);
    }
    public void setTarget(int target){
        motor.setTargetPosition(target);
    }
    public void setDistance(int distance){
        motor.setTargetPosition(distance);
    }
    public void setVelocity(double target){
        motor.setVelocity(target);
    }
    public void stopMotors(){
        motor.stopMotor();
    }
    public boolean isBottom(){return motor.getCurrentPosition()<=0;}
    public void resetEncoders(){
        motor.resetEncoder();
    }
    public boolean atTargetPos(){return motor.atTargetPosition();}

}
