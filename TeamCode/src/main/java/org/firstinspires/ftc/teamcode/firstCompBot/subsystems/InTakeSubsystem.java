package org.firstinspires.ftc.teamcode.firstCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class InTakeSubsystem extends SubsystemBase {
    final MotorEx motor;
    //final SensorColor colorSensor;
    final RevColorSensorV3 colorSensor;
    public InTakeSubsystem(HardwareMap hardwareMap){
        this.motor = new MotorEx(hardwareMap,"intake motor");
        this.colorSensor = hardwareMap.get(RevColorSensorV3.class,"intake color sensor");
        resetEncoders();
        motor.setRunMode(Motor.RunMode.RawPower);
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }


    public void setRunMode(Motor.RunMode mode){
        motor.setRunMode(mode);
    }

    public void setVelocity(double velocity){
        motor.set(velocity);
    }
    public void setDirection(boolean inverted){motor.setInverted(inverted);}
    public int red(){return colorSensor.red();}
    public int green(){return colorSensor.green();}
    public int blue(){return colorSensor.blue();}
    public void resetEncoders(){
        motor.resetEncoder();
    }
}
