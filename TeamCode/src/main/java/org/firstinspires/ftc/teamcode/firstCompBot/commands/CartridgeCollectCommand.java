package org.firstinspires.ftc.teamcode.firstCompBot.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.firstCompBot.Constants.InTakeConstants;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CartridgeSubsystam;

public class CartridgeCollectCommand extends CommandBase {
    CartridgeSubsystam subsystem;

    public CartridgeCollectCommand(CartridgeSubsystam subsystem){
        this.subsystem=subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.setDirection(InTakeConstants.inDirection);
        subsystem.insert();
    }
    public void end(boolean interrupted) {
        subsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
