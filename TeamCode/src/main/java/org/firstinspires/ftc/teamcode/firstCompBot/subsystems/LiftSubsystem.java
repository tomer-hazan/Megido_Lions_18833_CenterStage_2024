package org.firstinspires.ftc.teamcode.firstCompBot.subsystems;

import android.util.Log;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.HookConstants.levelError;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.HookConstants.levels;

public class LiftSubsystem extends SubsystemBase{
    public final MotorEx   motor1;
    public final MotorEx motor2;
    public final MotorEx motor3;
    TouchSensor bottomLimitSwitch;
    int level;
   // double encoderOffset;
    public LiftSubsystem(HardwareMap hardwareMap){
        this.motor1 = new MotorEx(hardwareMap,"lift motor 1");
        this.motor2 = new MotorEx(hardwareMap,"lift motor 2");
        this.motor3 = new MotorEx(hardwareMap,"lift motor 3");
        motor2.encoder = motor1.encoder;
        motor3.encoder = motor1.encoder;
        bottomLimitSwitch =hardwareMap.get(TouchSensor.class,"limit switch");
        motor1.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor3.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor1.encoder.setDirection(Motor.Direction.REVERSE);
        motor2.encoder.setDirection(Motor.Direction.REVERSE);
        motor3.encoder.setDirection(Motor.Direction.REVERSE);
        //bottomLimitSwitch = hardwareMap.get(RevTouchSensor.class,"LiftLimit");
        motor1.resetEncoder();
        motor2.resetEncoder();
        motor3.resetEncoder();
        motor1.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motor3.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motor1.set(0);
        motor2.set(0);
        motor3.set(0);
        motor1.setInverted(false);
        motor2.setInverted(false);
        motor3.setInverted(true);
        //encoderOffset = 0;//?
        level = 0;
        motor1.encoder.setDirection(Motor.Direction.REVERSE);

    }

    @Override
    public void periodic() {
        if (bottomLimitSwitch.isPressed()) {
            resetEncoders();
        }
        double height = getHeight();
        if(inLevel(level,height));
        else if(inLevel(level-1,height))level = level-1;
        else if(inLevel(level+1,height))level = level+1;
        else level=findLevel(height);

    }

    public void setRunMode(Motor.RunMode mode){
        motor1.setRunMode(mode);
        motor2.setRunMode(mode);
        motor3.setRunMode(mode);
    }

    public double getHeight(){
        return motor1.getCurrentPosition() ;
    }
    public double getHeight1(){
        return motor1.getCurrentPosition();
    }
    public double getHeight2(){
        return motor2.getCurrentPosition();
    }
    //private double avrageMotors(){return (motor1.getCurrentPosition()+motor2.getCurrentPosition())/number_of_motors; }

    public boolean isTop(){
        //return getHeight()>=top_height;
        return false;

    }

    public boolean isBottom(){
        Log.d("boolean limit switch", String.valueOf(bottomLimitSwitch.isPressed()));
        return bottomLimitSwitch.isPressed();
    }

    public void setPower(double power){
        motor1.set(power);
        motor2.set(power);
        motor3.set(power);
    }
    public void setTarget(int target){
        motor1.setTargetPosition(target);
        motor2.setTargetPosition(target);
        motor3.setTargetPosition(target);
    }
    public void setVelocity(double target){
        motor1.setVelocity(target);
        motor2.setVelocity(target);
        motor3.setVelocity(target);
    }
    public void stopMotors(){
        motor1.stopMotor();
        motor2.stopMotor();
        motor3.stopMotor();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public void resetEncoders(){
        motor1.resetEncoder();
        motor2.resetEncoder();
        motor3.resetEncoder();
    }

    //public double getEncoderOffset(){return encoderOffset;}
    private boolean inLevel(int level,double height){
        //    test if level is valid             test the condition
        return (level<levels.length-1&&level>0)&&(levels[level]-levelError<height && height<levels[level]+levelError);
    }
    private int findLevel(double height){
        for(int i=0;i<levels.length;i++){
            if(inLevel(level,height))return level;
        }
        return 0;
    }

}
