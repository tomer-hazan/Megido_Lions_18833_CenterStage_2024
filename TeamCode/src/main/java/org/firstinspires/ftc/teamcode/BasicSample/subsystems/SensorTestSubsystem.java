package org.firstinspires.ftc.teamcode.BasicSample.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SensorColor;
import com.arcrobotics.ftclib.hardware.SensorRevTOFDistance;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class SensorTestSubsystem extends SubsystemBase {
    SensorRevTOFDistance distanceSensor;
    SensorColor colorSensor;
    Telemetry telemetry;
    public SensorTestSubsystem(HardwareMap hardwareMap, Telemetry telemetry){
        distanceSensor = new SensorRevTOFDistance(hardwareMap,"distance");
        colorSensor = new SensorColor(hardwareMap,"color");
        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        if(distanceSensor!=null)telemetry.addData("distance",distanceSensor.getDistance(DistanceUnit.MM));
        if(colorSensor!=null){
            telemetry.addData("red",colorSensor.red());
            telemetry.addData("green",colorSensor.green());
            telemetry.addData("blue",colorSensor.blue());
            telemetry.addData("alpha",colorSensor.alpha());
        }
        telemetry.update();

    }

}
