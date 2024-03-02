package org.firstinspires.ftc.teamcode.secondCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.secondCompBot.Constants.ArmConstants;

public class ArmSubsystem extends SubsystemBase{
    public final MotorEx   motor;
    public ArmSubsystem(HardwareMap hardwareMap){
        this.motor = new MotorEx(hardwareMap,"arm");
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
    public double getAngle(){
        return motor.getCurrentPosition()/ArmConstants.max_tick_count *180;
    }

}
