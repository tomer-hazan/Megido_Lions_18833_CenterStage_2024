package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;


import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.Constants.InTakeConstants;

import java.util.function.Supplier;

public class EjectionCommand extends CommandBase {
    InTakeSubsystem subsystem;
    Supplier<Double> power;
    public EjectionCommand(InTakeSubsystem subsystem, Supplier<Double> power){
        this.subsystem=subsystem;
        this.power = power;
        addRequirements(subsystem);
    }
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        subsystem.setDirection(InTakeConstants.outDirection);
        subsystem.setPower(InTakeConstants.outMaxPower*power.get());
    }
    public void end(boolean interrupted) {
        subsystem.setPower(0);
    }


    @Override
    public boolean isFinished() {
        return false;
    }
}

