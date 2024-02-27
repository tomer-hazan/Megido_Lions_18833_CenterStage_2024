package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.clawSubsystem;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.CartridgeConstants.CartridgePositions.COLLECTION;

public class ForceCloseCartridgeCommand extends CommandBase {
    clawSubsystem subsystem;

    public ForceCloseCartridgeCommand(clawSubsystem subsystem){
        this.subsystem=subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        super.initialize();
        subsystem.rotateToPosition(COLLECTION);
    }


    @Override
    public boolean isFinished() {
        return false;
    }
}
