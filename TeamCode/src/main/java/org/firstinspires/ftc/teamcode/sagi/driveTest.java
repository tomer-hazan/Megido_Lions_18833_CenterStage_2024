package org.firstinspires.ftc.teamcode.sagi;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class driveTest extends OpMode {
    MotorEx frontLeft;
    MotorEx rearLeft;
    MotorEx frontRight;
    MotorEx rearRight;
    GamepadEx driver;
    double power;
    @Override
    public void init() {
        frontLeft = new MotorEx(hardwareMap,"fl");
        rearLeft = new MotorEx(hardwareMap,"rl");
        frontRight = new MotorEx(hardwareMap,"fr");
        rearRight= new MotorEx(hardwareMap,"rr");
        driver  = new GamepadEx(gamepad1);
        power = 0;
        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rearLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setInverted(false);
        rearRight.setInverted(false);
        frontLeft.setInverted(true);
        rearLeft.setInverted(true);
    }

    @Override
    public void loop() {
        power-=driver.getRightY()/1000;
        if(driver.getButton(GamepadKeys.Button.DPAD_DOWN))power=0.5;
        if(driver.getButton(GamepadKeys.Button.DPAD_RIGHT))power=0.25;
        if(driver.getButton(GamepadKeys.Button.DPAD_LEFT))power=0.75;
        if(driver.getButton(GamepadKeys.Button.DPAD_UP))power=0;
        telemetry.addData("power",power);
        frontLeft.set(power);
        rearLeft.set(power);
        frontRight.set(power);
        rearRight.set(power);
    }
}
