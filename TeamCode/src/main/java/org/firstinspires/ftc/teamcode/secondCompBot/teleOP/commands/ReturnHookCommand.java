package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.HookSubsystem;

public class ReturnHookCommand extends CommandBase {
    private HookSubsystem subsystem;
    public ReturnHookCommand(HookSubsystem subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        subsystem.setHookPosition(Constants.HookConstants.bottom_limit);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
