package org.firstinspires.ftc.teamcode.sagi;

import com.arcrobotics.ftclib.hardware.SensorRevTOFDistance;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@TeleOp
public class disSensorTest extends OpMode {
    SensorRevTOFDistance leftSensor;
    SensorRevTOFDistance rightSensor;
    @Override
    public void init() {
        leftSensor = new SensorRevTOFDistance(hardwareMap,"left distance");
        rightSensor = new SensorRevTOFDistance(hardwareMap,"right distance");
    }

    @Override
    public void loop() {
        telemetry.addData("left",leftSensor.getDistance(DistanceUnit.METER));
        telemetry.addData("right",rightSensor.getDistance(DistanceUnit.METER));
        telemetry.addData("BB Distance",getBBDistance());

    }
    private double getRightDistance(){
        return rightSensor.getDistance(DistanceUnit.METER);
    }
    private double getLeftDistance(){
        return leftSensor.getDistance(DistanceUnit.METER);
    }
    public double getBBDistance(){//distance to black wall
        double a =0.155;
        double z =0.205;
        return Math.sin(Math.atan(a/(getRightDistance()-getLeftDistance())))*((getLeftDistance()+getRightDistance())/2+z);
    }
}
