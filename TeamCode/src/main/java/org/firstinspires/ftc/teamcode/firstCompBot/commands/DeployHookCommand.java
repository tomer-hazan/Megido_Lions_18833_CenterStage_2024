package org.firstinspires.ftc.teamcode.firstCompBot.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.firstCompBot.Constants;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.HookSubsystem;

public class DeployHookCommand extends CommandBase {
    private HookSubsystem subsystem;
    public DeployHookCommand(HookSubsystem subsystem) {
        this.subsystem = subsystem;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        subsystem.setHookPosition(Constants.HookConstants.top_limit);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
