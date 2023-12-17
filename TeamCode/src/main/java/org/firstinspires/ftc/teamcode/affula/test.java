package org.firstinspires.ftc.teamcode.affula;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class test extends OpMode {
    MotorEx motor1;
    MotorEx motor2;
    CRServo servo;
    GamepadEx controller;
    GamepadEx controller2;
    @Override
    public void init() {
        motor1 = new MotorEx(hardwareMap,"motor1");
        motor2 = new MotorEx(hardwareMap,"motor2");
        servo = new CRServo(hardwareMap,"servo");
        motor1.setRunMode(Motor.RunMode.RawPower);
        motor2.setRunMode(Motor.RunMode.RawPower);
        controller = new GamepadEx(gamepad1);
        controller2 = new GamepadEx(gamepad2);
    }

    @Override
    public void loop() {
        motor1.set(controller.getLeftY());
        motor2.set(controller.getRightY());
        servo.set(controller2.getLeftY());
        telemetry.addData("leftY",controller.getLeftY());
        telemetry.update();
    }
}
