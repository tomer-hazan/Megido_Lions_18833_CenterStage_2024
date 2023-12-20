package org.firstinspires.ftc.teamcode.firstCompBot.commands;


import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.firstCompBot.Constants.InTakeConstants;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.InTakeSubsystem;

public class EjectionCommand extends CommandBase {
    InTakeSubsystem subsystem;
    public EjectionCommand(InTakeSubsystem subsystem){
        this.subsystem=subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.setDirection(InTakeConstants.outDirection);
        subsystem.setVelocity(InTakeConstants.outVelocity);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

