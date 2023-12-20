package org.firstinspires.ftc.teamcode.firstCompBot.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.AirplaneSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.HookSubsystem;

public class DeployHookCommand extends CommandBase {
    private HookSubsystem subsystem;

    public DeployHookCommand(HookSubsystem subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        if(!airplaneSubsystem.hasLunched()){
            airplaneSubsystem.lunch();
            airplaneSubsystem.setPosition(1);
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
