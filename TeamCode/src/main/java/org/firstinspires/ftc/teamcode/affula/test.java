package org.firstinspires.ftc.teamcode.affula;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class test extends OpMode {
    MotorEx left;
    MotorEx right;
    GamepadEx controller;
    GamepadEx driver;
    double powerLimit = 1;
    boolean freaze = false;
    boolean last=false;

    @Override
    public void init() {
        left = new MotorEx(hardwareMap,"leftMotor");
        right = new MotorEx(hardwareMap,"rightMotor");
        left.setRunMode(Motor.RunMode.RawPower);
        right.setRunMode(Motor.RunMode.RawPower);
        left.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        right.setInverted(true);
        driver = new GamepadEx(gamepad1);
        controller = new GamepadEx(gamepad2);
    }

    @Override
    public void loop() {
        if(controller.getButton(GamepadKeys.Button.B) && !last){
            freaze = !freaze;
        }
        if(controller.isDown(GamepadKeys.Button.A))
        if(controller.getButton(GamepadKeys.Button.A)) powerLimit = 0.3;
        if(controller.getButton(GamepadKeys.Button.X)) powerLimit = 0.7;
        if(controller.getButton(GamepadKeys.Button.Y)) powerLimit = 1;
        if(!freaze){
            left.set(driver.getLeftY()*powerLimit);
            right.set(driver.getRightY()*powerLimit);
            telemetry.addData("frozen?","not frozen");
        }else{
            left.set(controller.getLeftY()*powerLimit);
            right.set(controller.getRightY()*powerLimit);
            telemetry.addData("frozen?","frozen");
        }
        last = controller.getButton(GamepadKeys.Button.B);

        telemetry.addData("limit", powerLimit);
        telemetry.addData("left",left.get());
        telemetry.addData("right",-right.get());
        telemetry.update();
    }
}
