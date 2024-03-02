package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;


import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.HookSubsystem;

import java.util.function.Supplier;

public class PullRobotCommand extends CommandBase {
    private HookSubsystem subsystem;
    Supplier<Double> power;
    public PullRobotCommand(HookSubsystem subsystem, Supplier<Double> power) {
        this.subsystem = subsystem;
        this.power=power;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.pull(power.get());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
