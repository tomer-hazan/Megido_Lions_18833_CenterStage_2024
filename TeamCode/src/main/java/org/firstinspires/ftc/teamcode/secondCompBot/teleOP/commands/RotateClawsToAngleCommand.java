package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ClawSubsystem;

public class RotateClawsToAngleCommand extends CommandBase {
    ClawSubsystem subsystem;
    private double angle;

    public RotateClawsToAngleCommand(ClawSubsystem subsystem, double angle){
        this.subsystem=subsystem;
        this.angle=angle;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        subsystem.turnToAngle(angle);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
