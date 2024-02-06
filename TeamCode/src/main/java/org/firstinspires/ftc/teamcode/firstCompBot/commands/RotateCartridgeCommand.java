package org.firstinspires.ftc.teamcode.firstCompBot.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CartridgeSubsystam;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants.CartridgePositions.COLLECTION;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants.CartridgePositions.DROP;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants.autoRotateCartridgeHeight;

public class RotateCartridgeCommand extends CommandBase {
    CartridgeSubsystam subsystem;
    Supplier<Double> liftHeight;

    public RotateCartridgeCommand(CartridgeSubsystam subsystem, Supplier<Double> liftHeight){
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
