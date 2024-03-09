package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

/**
 * This is a simple teleop routine for testing localization. Drive the robot around like a normal
 * teleop routine and make sure the robot's estimated pose matches the robot's actual pose (slight
 * errors are not out of the ordinary, especially with sudden drive motions). The goal of this
 * exercise is to ascertain whether the localizer has been configured properly (note: the pure
 * encoder localizer heading may be significantly off if the track width has not been tuned).
 */
@TeleOp(group = "drive")
public class LocalizationTest extends LinearOpMode {
    FtcDashboard dashboard;
    static int leftEncoderStar;
    static int rightEncoderStar;
    static double frontEncoderStar;
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        TelemetryPacket packet = new TelemetryPacket();
        dashboard = FtcDashboard.getInstance();
        dashboard.setTelemetryTransmissionInterval(25);

        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DcMotor leftEncoder = hardwareMap.get(DcMotor.class, "left rear");
        DcMotor rightEncoder = hardwareMap.get(DcMotor.class, "right rear");
        DcMotor frontEncoder =hardwareMap.get(DcMotor.class, "right front");
        waitForStart();
        leftEncoderStar= leftEncoder.getCurrentPosition();
        rightEncoderStar = rightEncoder.getCurrentPosition();
        frontEncoderStar = frontEncoder.getCurrentPosition();

        while (!isStopRequested()) {
            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x,
                            -gamepad1.right_stick_x
                    )
            );

            drive.update();

            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.addData("leftEncoder",leftEncoder.getCurrentPosition());
            telemetry.addData("rightEncoder",rightEncoder.getCurrentPosition());
            telemetry.addData("frontEncoder",frontEncoder.getCurrentPosition());
            packet.put("leftEncoder",leftEncoder.getCurrentPosition()-leftEncoderStar);
            packet.put("rightEncoder",rightEncoder.getCurrentPosition()-rightEncoderStar);
            packet.put("frontEncoder",frontEncoder.getCurrentPosition()-frontEncoderStar);
            dashboard.sendTelemetryPacket(packet);
            telemetry.update();
        }
    }
}
