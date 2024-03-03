package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ClawSubsystem;

public class ControlClawsPosOnceCommand extends CommandBase {
    ClawSubsystem subsystem;
    double pos;

    public ControlClawsPosOnceCommand(ClawSubsystem subsystem, double pos){
        this.subsystem=subsystem;
        this.pos=pos;
    }

    @Override
    public void initialize() {
        subsystem.turnToAngle(pos);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}