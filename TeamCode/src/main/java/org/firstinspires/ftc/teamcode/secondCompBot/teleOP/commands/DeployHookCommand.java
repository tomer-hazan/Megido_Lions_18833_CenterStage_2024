package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.HookSubsystem;

public class DeployHookCommand extends CommandBase {
    private HookSubsystem subsystem;
    public DeployHookCommand(HookSubsystem subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        subsystem.raiseHook();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
