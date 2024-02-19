package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
public class DriveAutoTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(new Pose2d(11.61, -69.36, Math.toRadians(90.00)));
        TrajectorySequence myTrajectory = drive.trajectorySequenceBuilder(new Pose2d(11.61, -69.36, Math.toRadians(90.00)))
                .lineTo(new Vector2d(11.19, -50))                       //zihoy 
                .waitSeconds(0.5)
                .lineTo(new Vector2d(11.19, -65))

                .lineToSplineHeading(new Pose2d(30, -65,Math.toRadians(180)))                           //pixel
                .splineToConstantHeading(new Vector2d(40, -45), Math.toRadians(0),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL/2, DriveConstants.MAX_ANG_VEL/2,DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL/2))
                .waitSeconds(0.1)

                .lineTo(new Vector2d(47, -45),                              //hanaia
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL/2, DriveConstants.MAX_ANG_VEL/2,DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL/2))
                .lineTo(new Vector2d(38,-45))
                .lineTo(new Vector2d(38,-20))
                .lineTo(new Vector2d(57,-20))
                .waitSeconds(2)

                .lineTo(new Vector2d(30,-20))                                       //place
                .lineTo(new Vector2d(15,-20))
                .waitSeconds(0.2)
                .turn(Math.toRadians(-90))
//                .splineToConstantHeading(new Vector2d(11.61, -65), 0)
                .lineTo(new Vector2d(12, -74),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL/2,DriveConstants.MAX_ANG_VEL/2,DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL/2))


                .build();


        drive.followTrajectorySequenceAsync(myTrajectory);
        waitForStart();

        if(isStopRequested()) return;


        while (!isStopRequested()){
            drive.update();
            telemetry.addData("pos",drive.getPoseEstimate());
            telemetry.update();
        }
    }
}
