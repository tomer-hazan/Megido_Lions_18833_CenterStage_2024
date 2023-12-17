package org.firstinspires.ftc.teamcode.BasicSample.opModes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.BasicSample.subsystems.SensorTestSubsystem;
@TeleOp
public class SensorTestOpMode extends CommandOpMode {
    SensorTestSubsystem sensorTestSubsystem;
    @Override
    public void initialize() {
        sensorTestSubsystem = new SensorTestSubsystem(hardwareMap,telemetry);
    }
}
