package org.firstinspires.ftc.teamcode.firstCompBot.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.firstCompBot.Constants.InTakeConstants;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.InTakeSubsystem;

public class InTakeCommand extends CommandBase {
    InTakeSubsystem subsystem;
    public InTakeCommand(InTakeSubsystem subsystem){
        this.subsystem=subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.setDirection(InTakeConstants.inDirection);
        subsystem.setVelocity(InTakeConstants.inVelocity);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}