package org.firstinspires.ftc.teamcode.firstCompBot.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.firstCompBot.Constants.InTakeConstants;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.InTakeSubsystem;

import java.util.function.Supplier;

public class InTakeCommand extends CommandBase {
    InTakeSubsystem subsystem;
    Supplier<Double> power;
    public InTakeCommand(InTakeSubsystem subsystem, Supplier<Double> power){
        this.subsystem=subsystem;
        this.power = power;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        subsystem.setDirection(InTakeConstants.inDirection);
        subsystem.setPower(InTakeConstants.inMaxPower*power.get());
    }
    public void end(boolean interrupted) {
        subsystem.setPower(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
