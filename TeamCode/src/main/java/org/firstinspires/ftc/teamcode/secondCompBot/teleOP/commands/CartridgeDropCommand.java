package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.clawSubsystem;

public class CartridgeDropCommand extends CommandBase {
    clawSubsystem subsystem;

    public CartridgeDropCommand(clawSubsystem subsystem){
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
