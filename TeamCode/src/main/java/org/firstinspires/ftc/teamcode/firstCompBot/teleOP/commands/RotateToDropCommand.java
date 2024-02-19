package org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands;

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
