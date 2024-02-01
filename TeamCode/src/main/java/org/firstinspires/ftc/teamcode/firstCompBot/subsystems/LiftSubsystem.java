package org.firstinspires.ftc.teamcode.firstCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.LiftConstants.meters2ticks;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.LiftConstants.min_cartridge_hight;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.LiftConstants.number_of_motors;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.LiftConstants.ticks2meters;

public class LiftSubsystem extends SubsystemBase{
    final MotorGroup motors;
    public final MotorEx   motor1;
    public final MotorEx motor2;
    RevTouchSensor bottomLimitSwitch;
    double encoderOffset;
    public LiftSubsystem(HardwareMap hardwareMap){
        this.motor1 = new MotorEx(hardwareMap,"lift motor 1");
        this.motor2 = new MotorEx(hardwareMap,"lift motor 2");
        motors = new MotorGroup(motor1,motor2);
        motor1.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //bottomLimitSwitch = hardwareMap.get(RevTouchSensor.class,"LiftLimit");
        motors.resetEncoder();
        motors.setRunMode(Motor.RunMode.RawPower);
        motors.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motors.set(0);
        motor1.setInverted(false);//?
        motor2.setInverted(false);//?
        encoderOffset = meters2ticks(min_cartridge_hight);//?
    }

    @Override
    public void periodic() {
//        if (bottomLimitSwitch.isPressed()) {
//            encoderOffset = meters2ticks(min_cartridge_hight) - avrageMotors();
//        }

    }

    public void setRunMode(Motor.RunMode mode){
        motors.setRunMode(mode);
    }

    public double getHeight(){
        return ticks2meters(avrageMotors() + encoderOffset);
    }
    private double avrageMotors(){return (motor1.getCurrentPosition()+motor2.getCurrentPosition())/number_of_motors; }

    public boolean isTop(){
        //return getHeight()>=top_height;
        return false;

    }

    public boolean isBottom(){
        //return bottomLimitSwitch.isPressed();
        return false;
    }

    public void setPower(double power){
        setRunMode(Motor.RunMode.RawPower);
        motors.set(power);
    }
    public double getEncoderOffset(){return encoderOffset;}

}
