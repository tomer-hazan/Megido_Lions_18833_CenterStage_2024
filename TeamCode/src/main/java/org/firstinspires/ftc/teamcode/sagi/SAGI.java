package org.firstinspires.ftc.teamcode.sagi;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class SAGI extends OpMode {
    Servo l;
    Servo r;
    @Override
    public void init() {
        l = hardwareMap.get(Servo.class, "L servo");
        r = hardwareMap.get(Servo.class, "R servo");

    }

    @Override
    public void loop() {
        if (gamepad1.a){
            l.setPosition(1);
            r.setPosition(0);
        }
        else if (gamepad1.b){
            l.setPosition(0);
            r.setPosition(1);
        }
    }
}
