package org.firstinspires.ftc.teamcode.firstCompBot.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CartridgeSubsystam;

import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants.CartridgePositions.DROP;

public class RotateToDropCommand extends CommandBase {
    CartridgeSubsystam subsystem;

    public RotateToDropCommand(CartridgeSubsystam subsystem){
        this.subsystem=subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.rotateToPosition(DROP);
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
