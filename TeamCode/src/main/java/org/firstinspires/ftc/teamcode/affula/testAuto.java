package org.firstinspires.ftc.teamcode.affula;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous
public class testAuto extends LinearOpMode {
    MotorEx motor1;
    MotorEx motor2;
    GamepadEx controller;
    GamepadEx controller2;

    @Override
    public void runOpMode() throws InterruptedException {
        motor1 = new MotorEx(hardwareMap, "motor1");
        motor2 = new MotorEx(hardwareMap, "motor2");
        waitForStart();
        motor1.setRunMode(Motor.RunMode.RawPower);
        motor2.setRunMode(Motor.RunMode.RawPower);
        controller = new GamepadEx(gamepad1);
        controller2 = new GamepadEx(gamepad2);
        motor1.set(0.3);
        motor2.set(1);
        telemetry.addData("leftY", controller.getLeftY());
        telemetry.update();
        while (true) {
            motor1.set(0.3);
        }
    }

}
