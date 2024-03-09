package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.SlideSubsystem;

import java.util.function.Supplier;

public class MoveLiftTimedCommand extends CommandBase {
    private SlideSubsystem lift;
    private Supplier<Double> time;
    private double startTime;
    private double seconds;

    public MoveLiftTimedCommand(SlideSubsystem subsystem, double seconds,Supplier<Double> time) {
        this.lift = subsystem;
        this.seconds=seconds;
        this.time=time;
        startTime=time.get();
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        lift.setRunMode(Motor.RunMode.RawPower);
        lift.setPower(1);
    }

    @Override
    public boolean isFinished() {
        return time.get()-startTime>seconds;
    }
}
