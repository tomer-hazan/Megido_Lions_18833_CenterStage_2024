package org.firstinspires.ftc.teamcode.firstCompBot.subsystems;

import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
import com.arcrobotics.ftclib.kinematics.Odometry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.firstCompBot.Constants.DriveTrainConstants;

public class MyOdometrySubsystem extends OdometrySubsystem{
    /**
     * Make sure you are using the supplier version of the constructor
     *
     */
    public MyOdometrySubsystem(HardwareMap hardwareMap) {
        super(new HolonomicOdometry(() -> (new MotorEx(hardwareMap,"leftVerticalOdometry")).getCurrentPosition(),() -> (new MotorEx(hardwareMap,"rightVerticalOdometry")).getCurrentPosition(),() -> (new MotorEx(hardwareMap,"horizontalOdometry")).getCurrentPosition(),DriveTrainConstants.TrackWidth,DriveTrainConstants.CenterWheelOffset));
    }
}
