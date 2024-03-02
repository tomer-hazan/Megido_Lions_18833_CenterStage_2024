package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;


import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.HookSubsystem;

import java.util.function.Supplier;

public class PullRobotCommand extends CommandBase {
    private HookSubsystem subsystem;
    Supplier<Boolean> dpadUp;
    Supplier<Boolean> dpadDown;
    Supplier<Double> defaultPower;
    public PullRobotCommand(HookSubsystem subsystem, Supplier<Double> defaultPower, Supplier<Boolean> dpadUp,Supplier<Boolean> dpadDown) {
        this.subsystem = subsystem;
        this.defaultPower=defaultPower;
        this.dpadDown=dpadDown;
        this.dpadUp=dpadUp;
        addRequirements(subsystem);
    }
    @Override
    public void execute() {
        if(dpadUp.get())subsystem.pull(1);
        else if(dpadDown.get())subsystem.pull(-1);
        else subsystem.pull(defaultPower.get());

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
