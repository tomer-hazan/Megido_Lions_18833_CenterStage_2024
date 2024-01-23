package org.firstinspires.ftc.teamcode.sagi;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class drive extends OpMode {
    MotorEx frontLeft;
    MotorEx rearLeft;
    MotorEx frontRight;
    MotorEx rearRight;
    MotorEx pullUpMotor;
//    Servo lHook;
    Servo rHook;
    MotorEx inTakeMotor;
    MecanumDrive mecanum;
    GamepadEx driver;
    GamepadEx controller;
    @Override
    public void init() {
        frontLeft = new MotorEx(hardwareMap,"fl");
        rearLeft = new MotorEx(hardwareMap,"rl");
        frontRight = new MotorEx(hardwareMap,"fr");
        rearRight=new MotorEx(hardwareMap,"rr");
        pullUpMotor = new MotorEx(hardwareMap,"pullUp");
        inTakeMotor = new MotorEx(hardwareMap,"inTake");
//        lHook = hardwareMap.get(Servo.class, "L servo");
        rHook = hardwareMap.get(Servo.class, "R servo");
        pullUpMotor.setInverted(true);
        // input motors exactly as shown below
        mecanum = new MecanumDrive(frontLeft, frontRight, rearLeft, rearRight);
        driver = new GamepadEx(gamepad1);
        controller = new GamepadEx(gamepad2);
    }

    @Override
    public void loop() {
        mecanum.driveRobotCentric(driver.getLeftX(), driver.getLeftY(), driver.getRightX());
        if (controller.getButton(GamepadKeys.Button.DPAD_UP)){
//            lHook.setPosition(1);
            rHook.setPosition(0);
        }
        else if (controller.getButton(GamepadKeys.Button.DPAD_DOWN)){
//            lHook.setPosition(0);
            rHook.setPosition(1);
        }
        if(controller.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)>0.1){
            inTakeMotor.setInverted(false);
            inTakeMotor.set(1);
        } else if (controller.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)>0.1) {
            inTakeMotor.setInverted(true);
            inTakeMotor.set(1);
        }else inTakeMotor.set(0);
        pullUpMotor.set(controller.getLeftY());
        telemetry.addData("height" ,pullUpMotor.getCurrentPosition());
        telemetry.update();
    }
}
