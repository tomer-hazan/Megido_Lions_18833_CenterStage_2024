package org.firstinspires.ftc.teamcode.firstCompBot.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CartridgeSubsystam;

public class CartridgeDropOneCommand extends CommandBase {
    CartridgeSubsystam subsystem;

    public CartridgeDropOneCommand(CartridgeSubsystam subsystem){
        this.subsystem=subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.setDirection(CartridgeConstants.outDirection);
        subsystem.drop();
    }

    public void end(boolean interrupted) {
        subsystem.stop();
    }
    @Override
    public boolean isFinished() {
        return false;
    }
}
