package org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.firstCompBot.Constants;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CartridgeSubsystam;

import static java.lang.System.currentTimeMillis;

public class DropPixelCommand extends CommandBase {
    private CartridgeSubsystam cartridge;
    int target = 8600;
    Telemetry telemetry;
    long startTime;

    public DropPixelCommand(CartridgeSubsystam cartridgeSubsystam, Telemetry telemetry) {
        this.cartridge = cartridgeSubsystam;
        this.telemetry =telemetry;
        addRequirements(cartridgeSubsystam);
    }

    @Override
    public void initialize() {
        startTime=currentTimeMillis();
        cartridge.rotateToPosition(Constants.CartridgeConstants.CartridgePositions.DROP);
        cartridge.drop();
    }


    @Override
    public void end(boolean interrupted) {
        cartridge.stop();
        cartridge.rotateToPosition(Constants.CartridgeConstants.CartridgePositions.COLLECTION);
    }

    @Override
    public boolean isFinished() {
        return currentTimeMillis()-startTime>3000;
    }
}
