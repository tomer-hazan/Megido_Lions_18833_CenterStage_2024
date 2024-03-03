package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ClawSubsystem;

public class ControlClawsAngleOnceCommand extends CommandBase {
    ClawSubsystem subsystem;
    double angle;

    public ControlClawsAngleOnceCommand(ClawSubsystem subsystem, double angle){
        this.subsystem=subsystem;
        this.angle=angle;
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