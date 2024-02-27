package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.clawSubsystem;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.CartridgeConstants.CartridgePositions.DROP;

public class RotateToDropCommand extends CommandBase {
    clawSubsystem subsystem;

    public RotateToDropCommand(clawSubsystem subsystem){
        this.subsystem=subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        super.initialize();
        subsystem.rotateToPosition(DROP);
    }

    @Override
    public void execute() {
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
