package org.firstinspires.ftc.teamcode.secondCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.secondCompBot.Constants.ArmConstants;

public class ArmSubsystem extends SubsystemBase{
    public final MotorEx   motor;
    public static double f=0.5;
    RevTouchSensor limit;
    ArmConstants.State state;
    double target;
    double velo;
    public ArmSubsystem(HardwareMap hardwareMap){
        this.motor = new MotorEx(hardwareMap,"arm");
        limit =hardwareMap.get(RevTouchSensor.class,"arm limit switch");
        motor.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.encoder.setDirection(Motor.Direction.REVERSE);
        motor.resetEncoder();
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motor.set(0);
        state = ArmConstants.State.STAND_STILL;
        velo = motor.getVelocity();
        motor.setInverted(false);
    }

    @Override
    public void periodic() {
        if(limit.isPressed())motor.resetEncoder();
        velo=Math.abs(motor.getVelocity());
        if(velo<300){
            if(state== ArmConstants.State.MOVING){
                state= ArmConstants.State.STAND_STILL;
                target=motor.getCurrentPosition();
            }
        }else state= ArmConstants.State.MOVING;
    }

    public void setRunMode(Motor.RunMode mode){
        motor.setRunMode(mode);
    }

    public void setPower(double power){
//        if(state== ArmConstants.State.STAND_STILL) motor.set(power-Math.cos(target /ArmConstants.ticks_in_degree)*f);
//        else motor.set(power);

//        motor.set(power-Math.cos(target /ArmConstants.ticks_in_degree)*f);
        motor.set(power);
    }
    public void setTarget(int target){
        motor.setTargetPosition(target);
    }
    public void setVelocity(double target){
        motor.setVelocity(target);
    }
    public double getVelocity(){return motor.getVelocity();}
    public void stopMotors(){
        motor.stopMotor();
    }

    public void resetEncoders(){
        motor.resetEncoder();
    }
    public double getAngle(){
        return motor.getCurrentPosition() /ArmConstants.ticks_in_degree;
    }
}
