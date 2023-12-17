package org.firstinspires.ftc.teamcode.BasicSample.subsystems;

import com.arcrobotics.ftclib.command.MecanumControllerCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.drivebase.RobotDrive;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.function.Supplier;

public class DriveTrainSubsystem extends SubsystemBase {
    MecanumDrive drive;
    MotorEx leftFront;
    MotorEx rightFront;
    MotorEx leftRear;
    MotorEx rightRear;
    public DriveTrainSubsystem(HardwareMap hardwareMap){
        leftFront = new MotorEx(hardwareMap,"leftFront");
        rightFront = new MotorEx(hardwareMap,"rightFront");
        leftRear = new MotorEx(hardwareMap,"leftRear");
        rightRear = new MotorEx(hardwareMap,"rightRear");
        drive = new MecanumDrive(leftFront,rightFront,leftRear,rightRear);
    }

    public RobotDrive getDrive(){
        return drive;
    }
}
