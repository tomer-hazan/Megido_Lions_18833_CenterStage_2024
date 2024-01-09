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
    Servo lHook;
    Servo rHook;
    MotorEx inTakeMotor;
    MecanumDrive mecanum;
    GamepadEx gp1;
    GamepadEx gp2;
    @Override
    public void init() {
        frontLeft = new MotorEx(hardwareMap,"fl");
        rearLeft = new MotorEx(hardwareMap,"rl");
        frontRight = new MotorEx(hardwareMap,"fr");
        rearRight=new MotorEx(hardwareMap,"rr");
        pullUpMotor = new MotorEx(hardwareMap,"pullUp");
        inTakeMotor = new MotorEx(hardwareMap,"inTake");
        lHook = hardwareMap.get(Servo.class, "L servo");
        rHook = hardwareMap.get(Servo.class, "R servo");
        pullUpMotor.setInverted(true);
        // input motors exactly as shown below
        mecanum = new MecanumDrive(frontLeft, frontRight, rearLeft, rearRight);
        gp1 = new GamepadEx(gamepad1);
        gp2 = new GamepadEx(gamepad2);
    }

    @Override
    public void loop() {
        mecanum.driveRobotCentric(gp1.getLeftX(),gp1.getLeftY(),gp1.getRightY());
        if (gamepad1.a){
            lHook.setPosition(1);
            rHook.setPosition(0);
        }
        else if (gamepad1.b){
            lHook.setPosition(0);
            rHook.setPosition(1);
        }
        if(gp2.getButton(GamepadKeys.Button.B)){
            inTakeMotor.setInverted(false);
            inTakeMotor.set(1);
        } else if (gp2.getButton(GamepadKeys.Button.A)) {
            inTakeMotor.setInverted(true);
            inTakeMotor.set(1);
        }else if (gp2.getButton(GamepadKeys.Button.Y)) {
            inTakeMotor.set(0);
        }
        pullUpMotor.set(gp2.getLeftY());
        telemetry.addData("height" ,pullUpMotor.getCurrentPosition());
        telemetry.update();
    }
}
