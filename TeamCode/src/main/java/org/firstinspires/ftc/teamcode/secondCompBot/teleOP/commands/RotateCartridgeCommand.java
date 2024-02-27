package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.clawSubsystem;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.CartridgeConstants.CartridgePositions.COLLECTION;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.CartridgeConstants.CartridgePositions.DROP;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.CartridgeConstants.autoRotateCartridgeHeight;

public class RotateCartridgeCommand extends CommandBase {
    clawSubsystem subsystem;
    Supplier<Double> liftHeight;

    public RotateCartridgeCommand(clawSubsystem subsystem, Supplier<Double> liftHeight){
        this.subsystem=subsystem;
        this.liftHeight=liftHeight;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        if(autoRotateCartridgeHeight>liftHeight.get())subsystem.rotateToPosition(COLLECTION);
        else subsystem.rotateToPosition(DROP);

    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
