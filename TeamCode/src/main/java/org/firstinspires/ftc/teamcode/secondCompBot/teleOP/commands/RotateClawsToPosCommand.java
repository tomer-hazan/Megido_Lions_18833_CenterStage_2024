package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.JointSubsystem;

import java.util.function.Supplier;

public class RotateClawsToPosCommand extends CommandBase {
    JointSubsystem subsystem;
    private  Supplier<Double> pos;

    public RotateClawsToPosCommand(JointSubsystem subsystem, Supplier<Double> pos){
        this.subsystem=subsystem;
        this.pos=pos;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        subsystem.setPos(pos.get());
    }

    @Override
    public void execute() {
        subsystem.setPos(pos.get());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
