package org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.firstCompBot.Constants;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CartridgeSubsystam;

public class CartridgeDropCommand extends CommandBase {
    CartridgeSubsystam subsystem;

    public CartridgeDropCommand(CartridgeSubsystam subsystem){
        this.subsystem=subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.setDirection(Constants.CartridgeConstants.outDirection);
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
