package org.firstinspires.ftc.teamcode.secondCompBot.opModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp
public class armPID extends OpMode {
    PIDController controller;
    public static double p=0.025,i=0.25,d=0.0055;
    public static double f=0.15;
    public static int armTarget =1000;
    private final double ticks_in_degree=2790/119;
    Servo flip1;
    Servo flip2;
    MotorEx arm;
//    MotorEx slide;
    @Override
    public void init() {
        controller = new PIDController(p,i,d);
        controller.setTolerance(50);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        arm = new MotorEx(hardwareMap,"arm");
        arm.encoder.setDirection(Motor.Direction.REVERSE);
        arm.setInverted(true);
//        slide = new MotorEx(hardwareMap,"slide");
//        flip1 = hardwareMap.get(Servo.class,"flip servo 1");
//        flip2 = hardwareMap.get(Servo.class,"flip servo 2");
//        flip1.setPosition(Constants.ClawConstants.groundPos);
//        flip2.setPosition(Constants.ClawConstants.groundPos);
//        slide.setRunMode(Motor.RunMode.PositionControl);
        telemetry.addData("pos",arm.getCurrentPosition());
        telemetry.addData("target", armTarget);
//        telemetry.addData("slidePos",slide.getCurrentPosition());
        telemetry.update();
    }

    @Override
    public void loop() {
        controller.setPID(p,i,d);
        int armPos = arm.getCurrentPosition();
        double pid = controller.calculate(armPos, armTarget);
        double ff = Math.cos(armTarget /ticks_in_degree)*f;
        double power = pid+ff;
        if(gamepad1.a)arm.resetEncoder();

        arm.set(power);
//        slide.setTargetPosition(slideTarget);
//        slide.set(0.75);
        telemetry.addData("pos",armPos);
        telemetry.addData("target", armTarget);
        telemetry.addData("angle", armTarget /ticks_in_degree);
//        telemetry.addData("slidePos",slide.getCurrentPosition());
        telemetry.addData("isFinnished",controller.atSetPoint());
        telemetry.update();
    }
}
