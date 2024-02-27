package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.clawSubsystem;

public class RotateClawsToAngleCommand extends CommandBase {
    clawSubsystem subsystem;
    private double angle;

    public RotateClawsToAngleCommand(clawSubsystem subsystem, double angle){
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
