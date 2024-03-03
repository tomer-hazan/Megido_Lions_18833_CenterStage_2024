package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ClawSubsystem;

import java.util.function.Supplier;

public class RotateClawsToAngleCommand extends CommandBase {
    ClawSubsystem subsystem;
    private  Supplier<Double> angle;

    public RotateClawsToAngleCommand(ClawSubsystem subsystem, Supplier<Double> angle){
        this.subsystem=subsystem;
        this.angle=angle;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        subsystem.turnToAngle(angle.get());
    }

    @Override
    public void execute() {
        subsystem.turnToAngle(angle.get());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
