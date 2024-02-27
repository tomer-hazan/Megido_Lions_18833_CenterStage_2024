package org.firstinspires.ftc.teamcode.secondCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SlideSubsystem extends SubsystemBase{
    public final MotorEx   motor;
    public SlideSubsystem(HardwareMap hardwareMap){
        this.motor = new MotorEx(hardwareMap,"slide motor");
        motor.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.encoder.setDirection(Motor.Direction.REVERSE);
        motor.resetEncoder();
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motor.set(0);
        motor.setInverted(false);
    }

    @Override
    public void periodic() {}

    public void setRunMode(Motor.RunMode mode){
        motor.setRunMode(mode);
    }

    public double getHeight(){
        return motor.getCurrentPosition() ;
    }

    public void setPower(double power){
        motor.set(power);
    }
    public void setTarget(int target){
        motor.setTargetPosition(target);
    }
    public void setVelocity(double target){
        motor.setVelocity(target);
    }
    public void stopMotors(){
        motor.stopMotor();
    }
    public void resetEncoders(){
        motor.resetEncoder();
    }

}
