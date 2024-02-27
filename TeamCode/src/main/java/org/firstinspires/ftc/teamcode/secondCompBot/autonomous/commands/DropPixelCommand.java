package org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.clawSubsystem;

import static java.lang.System.currentTimeMillis;

public class DropPixelCommand extends CommandBase {
    private clawSubsystem cartridge;
    int target = 8600;
    Telemetry telemetry;
    long startTime;

    public DropPixelCommand(clawSubsystem clawSubsystem, Telemetry telemetry) {
        this.cartridge = clawSubsystem;
        this.telemetry =telemetry;
        addRequirements(clawSubsystem);
    }

    @Override
    public void initialize() {
        startTime=currentTimeMillis();
        cartridge.rotateToPosition(Constants.CartridgeConstants.CartridgePositions.DROP);
        cartridge.drop();
    }


//    @Override
//    public void end(boolean interrupted) {
//        cartridge.stop();
//        cartridge.rotateToPosition(Constants.CartridgeConstants.CartridgePositions.COLLECTION);
//    }
//
    @Override
    public boolean isFinished() {
        return currentTimeMillis()-startTime>3000;
    }
}
