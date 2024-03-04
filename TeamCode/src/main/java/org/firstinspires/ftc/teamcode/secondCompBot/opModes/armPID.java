package org.firstinspires.ftc.teamcode.secondCompBot.opModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Config
public class armPID extends OpMode {
    PIDController controller;
    public static double p=0,i=0,d=0;
    public static double f=0;
    public static int target=0;
    private final double ticks_in_degree=5446/238.0;
    MotorEx arm;
    @Override
    public void init() {
        controller = new PIDController(p,i,d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        arm = new MotorEx(hardwareMap,"arm");
    }

    @Override
    public void loop() {
        controller.setPID(p,i,d);
        int armPos = arm.getCurrentPosition();
        double pid = controller.calculate(armPos,target);
        double ff = Math.cos(target/ticks_in_degree)*f;
        double power = pid+ff;

        arm.set(power);
        telemetry.addData("pos",armPos);
        telemetry.addData("target",target);
        telemetry.update();
    }
}
