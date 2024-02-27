package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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
