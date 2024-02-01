package org.firstinspires.ftc.teamcode.sagi;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class jhgsfviueiudz extends OpMode {

    DcMotor l;
    DcMotor r;


    @Override
    public void init() {
      l=hardwareMap.dcMotor.get("lift motor 1");
      r=hardwareMap.dcMotor.get("lift motor 2");

      l.setDirection(DcMotorSimple.Direction.FORWARD);
      r.setDirection(DcMotorSimple.Direction.FORWARD);
      r.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      l.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        l.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {

        l.setPower(-gamepad2.left_stick_y);
        r.setPower(-gamepad2.left_stick_y);
        telemetry.addData("left pos",l.getCurrentPosition());
        telemetry.addData("right pos",r.getCurrentPosition());

    }
}
