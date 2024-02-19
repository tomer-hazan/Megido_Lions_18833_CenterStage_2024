package org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CartridgeSubsystam;

import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants.CartridgePositions.COLLECTION;

public class ForceCloseCartridgeCommand extends CommandBase {
    CartridgeSubsystam subsystem;

    public ForceCloseCartridgeCommand(CartridgeSubsystam subsystem){
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
