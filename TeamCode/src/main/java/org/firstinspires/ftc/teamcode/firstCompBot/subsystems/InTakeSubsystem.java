package org.firstinspires.ftc.teamcode.firstCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SensorColor;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.firstCompBot.Constants;

import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.LiftConstants.meters2ticks;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.LiftConstants.min_cartridge_hight;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.LiftConstants.number_of_motors;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.LiftConstants.ticks2meters;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.LiftConstants.top_height;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.InTakeConstants.colorError;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.GameElaments.Pixals;

public class InTakeSubsystem extends SubsystemBase {
    final MotorEx motor;
    final SensorColor colorSensor;
    public InTakeSubsystem(HardwareMap hardwareMap){
        this.motor = new MotorEx(hardwareMap,"InTakeMotor");
        this.colorSensor = new SensorColor(hardwareMap,"InTakeColorSensor");
        motor.resetEncoder();
        motor.setRunMode(Motor.RunMode.VelocityControl);
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }


    public void setRunMode(Motor.RunMode mode){
        motor.setRunMode(mode);
    }

    public void setVelocity(double velocity){
        motor.setVelocity(velocity);
    }
    public void setDirection(boolean inverted){motor.setInverted(inverted);}
    public Constants.GameElaments.Pixals getPixal(){
        return Pixals.NULL;
    }
    public int red(){return colorSensor.red();}
    public int green(){return colorSensor.green();}
    public int blue(){return colorSensor.blue();}
}
