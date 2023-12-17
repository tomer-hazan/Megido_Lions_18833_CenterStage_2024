package org.firstinspires.ftc.teamcode.firstCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.drivebase.RobotDrive;
import com.arcrobotics.ftclib.hardware.SensorRevTOFDistance;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DriveTrainSubsystem extends SubsystemBase {
    MecanumDrive drive;
    MotorEx leftFront;
    MotorEx rightFront;
    MotorEx leftRear;
    MotorEx rightRear;
    SensorRevTOFDistance leftSensor;
    SensorRevTOFDistance rightSensor;
    public DriveTrainSubsystem(HardwareMap hardwareMap){
        leftFront = new MotorEx(hardwareMap,"leftFront");
        rightFront = new MotorEx(hardwareMap,"rightFront");
        leftRear = new MotorEx(hardwareMap,"leftRear");
        rightRear = new MotorEx(hardwareMap,"rightRear");
        drive = new MecanumDrive(leftFront,rightFront,leftRear,rightRear);

        leftSensor = new SensorRevTOFDistance(hardwareMap,"leftDistance");
        rightSensor = new SensorRevTOFDistance(hardwareMap,"rightDistance");
    }

    public RobotDrive getDrive(){
        return drive;
    }
    public double getLeftDistance(){return leftSensor.getDistance(DistanceUnit.METER);}
    public double getRightDistance(){return rightSensor.getDistance(DistanceUnit.METER);}

}
