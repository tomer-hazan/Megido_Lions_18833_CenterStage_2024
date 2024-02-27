package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.SlideSubsystem;

import static java.lang.System.currentTimeMillis;

public class MoveLiftToIntakeCommand extends CommandBase {
    private SlideSubsystem lift;
    int target = 500;
    Telemetry telemetry;
    int step = 0;
    int timeTarget;
    double time;
    double power;

    public MoveLiftToIntakeCommand(SlideSubsystem subsystem, Telemetry telemetry, int timeTarget, double power) {
        this.lift = subsystem;
        this.timeTarget = timeTarget;
        this.telemetry =telemetry;
        this.power = power;
        addRequirements(subsystem);

    }

    @Override
    public void initialize() {
        lift.setRunMode(Motor.RunMode.RawPower);
        lift.setPower(power);
        time = currentTimeMillis();


    }


        @Override
    public boolean isFinished() {
        return currentTimeMillis()-time>timeTarget;
    }
}
