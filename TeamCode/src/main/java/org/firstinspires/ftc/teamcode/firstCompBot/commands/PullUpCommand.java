package org.firstinspires.ftc.teamcode.firstCompBot.commands;


import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.firstCompBot.Constants.HookConstants;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.HookSubsystem;

import java.util.function.Supplier;

public class PullUpCommand extends CommandBase {
    private HookSubsystem subsystem;
    public PullUpCommand(HookSubsystem subsystem) {
        this.subsystem = subsystem;
    }

    @Override
    public void initialize() {
        subsystem.setSuspensionDirection(HookConstants.up_direction_inverted);
    }

    @Override
    public void execute() {
        subsystem.setHookPosition(HookConstants.top_limit);
        subsystem.setSuspensionPower(HookConstants.power);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
