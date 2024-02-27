package org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.SlideSubsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

public class GetToWallCommand extends ParallelCommandGroup {
    public GetToWallCommand(DriveTrainSubsystem drive, SlideSubsystem lift, Telemetry telemetry){

        addCommands(
                new DriveTrajectorySequenceCommand(drive,moveToBoard(drive.getSampleDrive())),
                new MoveLiftToHeightCommand(lift,telemetry,8600)
        );
        addRequirements(drive,lift);
    }

    private TrajectorySequence moveToBoard(SampleMecanumDrive drive){
        return drive.trajectorySequenceBuilder(new Pose2d(11.61, -69.36, Math.toRadians(90.00)))
                .lineTo(new Vector2d(11.19, -50))                       //zihoy
                .waitSeconds(0.5)
                .lineTo(new Vector2d(11.19, -65))

                .lineToSplineHeading(new Pose2d(30, -65,Math.toRadians(180)))                           //pixel
                .splineToConstantHeading(new Vector2d(40, -45), Math.toRadians(0),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL/2, DriveConstants.MAX_ANG_VEL/2,DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL/2))
                .waitSeconds(0.1)
                .build();
    }
}
