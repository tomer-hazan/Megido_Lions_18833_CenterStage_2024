package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.JointSubsystem;

import java.util.function.Supplier;

public class RotateClawsToAngleCommand extends CommandBase {
    JointSubsystem subsystem;
    private  Supplier<Double> angle;

    public RotateClawsToAngleCommand(JointSubsystem subsystem, Supplier<Double> angle){
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
