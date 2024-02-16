package org.firstinspires.ftc.teamcode.affula;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants.bottom_limit;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants.top_limit;

@TeleOp
public class testServo extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        ServoEx  rotationServo = new SimpleServo(hardwareMap,"rotation cartridge servo",bottom_limit,top_limit);
        waitForStart();
        while (!isStopRequested()){
            rotationServo.setPosition(Math.abs(gamepad1.left_stick_y));
            telemetry.addData("percent",Math.abs(gamepad1.left_stick_y));
            telemetry.update();
        }

    }
//    ServoEx  rotationServo = new SimpleServo(hardwareMap,"rotation cartridge servo",bottom_limit,top_limit);
//    waitForStart();
//        while (true){
//        if(gamepad1.a)rotationServo.setPosition(1);
//        else if(gamepad1.b)rotationServo.setPosition(0);
//    }

}
