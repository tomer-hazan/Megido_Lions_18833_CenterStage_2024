package org.firstinspires.ftc.teamcode.secondCompBot.opModes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.JointSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ChangeClawsDefaultPos;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlClawsPosCommand;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
public class DriveAutoTest2 extends LinearOpMode {
    ClawSubsystem clawSubsystem;
    JointSubsystem jointSubsystem;
    ControlClawsPosCommand controlClawsPosCommand;
    ChangeClawsDefaultPos jointToGround;
    @Override
    public void runOpMode() {
        clawSubsystem=new ClawSubsystem(hardwareMap,()->getRuntime());
        jointSubsystem = new JointSubsystem(hardwareMap);
        clawSubsystem.openOrCloseLeft(Constants.ClawConstants.Positions.CLOSE);
        clawSubsystem.openOrCloseRight(Constants.ClawConstants.Positions.CLOSE);
        controlClawsPosCommand = new ControlClawsPosCommand(jointSubsystem,()->0.0);
        jointToGround = new ChangeClawsDefaultPos(jointSubsystem, Constants.JointConstants.groundPos);
        jointSubsystem.setDefaultCommand(controlClawsPosCommand);


        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(new Pose2d(12, -60, Math.toRadians(-90.00)));
        TrajectorySequence myTrajectory = drive.trajectorySequenceBuilder(new Pose2d(12, -60, Math.toRadians(-90.00)))
                .lineToSplineHeading(new Pose2d(12,-32,Math.toRadians(90)))
                .lineToSplineHeading(new Pose2d(45, -32, Math.toRadians(180)))
//                .splineTo(new Vector2d(12, -60), Math.toRadians(-90.00))
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
