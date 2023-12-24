package org.firstinspires.ftc.teamcode.firstCompBot.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.firstCompBot.Constants;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.HookSubsystem;

import java.util.function.Supplier;

public class PullUpCommand extends CommandBase {
    private HookSubsystem subsystem;
    private Supplier<Double> power;
    public PullUpCommand(HookSubsystem subsystem,Supplier<Double> power) {
        this.subsystem = subsystem;
        this.power=power;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        subsystem.setHookPosition(Constants.HookConstants.top_limit);
        subsystem.setSuspensionPower(power.get());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
