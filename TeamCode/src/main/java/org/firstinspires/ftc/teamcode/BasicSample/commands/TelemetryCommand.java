package org.firstinspires.ftc.teamcode.BasicSample.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.BasicSample.subsystems.LiftSubsystem;

import java.util.function.Supplier;

public class TelemetryCommand extends CommandBase {
    private Telemetry telemetry;

    public TelemetryCommand(Telemetry telemetry) {
        this.telemetry= telemetry;
    }

    @Override
    public void initialize() {
        setTelemetry();
    }

    @Override
    public void execute() {
        setTelemetry();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    private void setTelemetry(){
        telemetry.addData("","");
    }
}
