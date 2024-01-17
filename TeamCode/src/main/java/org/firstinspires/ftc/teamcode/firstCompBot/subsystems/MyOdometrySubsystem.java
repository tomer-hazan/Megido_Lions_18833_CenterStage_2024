package org.firstinspires.ftc.teamcode.firstCompBot.subsystems;

import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.firstCompBot.Constants.DriveTrainConstants;

public class MyOdometrySubsystem extends OdometrySubsystem{
    /**
     * Make sure you are using the supplier version of the constructor
     *
     */
    public MyOdometrySubsystem(HardwareMap hardwareMap) {
        //                                 vertical left odometry                                         vertical right odometry                                                horizontal odometry
        super(new HolonomicOdometry(() -> (new MotorEx(hardwareMap,"left front")).getCurrentPosition(),() -> (new MotorEx(hardwareMap,"right rear")).getCurrentPosition(),() -> (new MotorEx(hardwareMap,"left rear")).getCurrentPosition(),DriveTrainConstants.TrackWidth,DriveTrainConstants.CenterWheelOffset));
    }
}
