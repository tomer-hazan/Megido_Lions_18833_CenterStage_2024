package org.firstinspires.ftc.teamcode.firstCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.drivebase.RobotDrive;
import com.arcrobotics.ftclib.hardware.SensorColor;
import com.arcrobotics.ftclib.hardware.SensorRevTOFDistance;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class DriveTrainSubsystem extends SubsystemBase {
    SampleMecanumDrive autoDrive;
    MecanumDrive drive;
    public MotorEx leftFront;
    public MotorEx rightFront;
    public MotorEx leftRear;
    public MotorEx rightRear;
    SensorRevTOFDistance leftSensor;
    SensorRevTOFDistance rightSensor;
    SensorColor frontSensor;
    public DriveTrainSubsystem(HardwareMap hardwareMap){

        leftFront = new MotorEx(hardwareMap,"left front");
        rightFront = new MotorEx(hardwareMap,"right front");
        leftRear = new MotorEx(hardwareMap,"left rear");
        rightRear = new MotorEx(hardwareMap,"right rear");
        drive = new MecanumDrive(false,leftFront,rightFront,leftRear,rightRear);
        //frontSensor = new SensorColor(hardwareMap,"front color sensor");
        leftSensor = new SensorRevTOFDistance(hardwareMap,"left distance");
        rightSensor = new SensorRevTOFDistance(hardwareMap,"right distance");
        leftFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        autoDrive = new SampleMecanumDrive(hardwareMap);
        resetEncoders();
    }

    public RobotDrive getDrive(){
        return drive;
    }
    public SampleMecanumDrive getSampleDrive(){
        return autoDrive;
    }
    public double getLeftDistance(){return leftSensor.getDistance(DistanceUnit.METER);}
    public double getRightDistance(){return rightSensor.getDistance(DistanceUnit.METER);}
    public double getBBDistance(){//distance to black wall
        double a =0.155;
        double z =0.205;
        return Math.sin(Math.atan(a/(getRightDistance()-getLeftDistance())))*((getLeftDistance()+getRightDistance())/2+z);
    }
    public void resetEncoders(){
        leftRear.resetEncoder();
        rightRear.resetEncoder();
        leftFront.resetEncoder();
        rightFront.resetEncoder();
    }
    public boolean leftFrontInverted(){
        return leftFront.getInverted();
    }
    public boolean leftRearInverted(){
        return leftRear.getInverted();
    }
    public boolean rightFrontInverted(){
        return rightFront.getInverted();
    }
    public boolean rightRearInverted(){
        return rightRear.getInverted();
    }
//    public int[] geARGBt(){
//        return frontSensor.getARGB();
//    }

}
