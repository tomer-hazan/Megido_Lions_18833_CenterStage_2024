package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ClawSubsystem;

public class ControlLeftClawCommand extends CommandBase {
    ClawSubsystem subsystem;
    private boolean toOpen;
    private double startTime;

    public ControlLeftClawCommand(ClawSubsystem subsystem, boolean toOpen){
        this.subsystem=subsystem;
        this.toOpen=toOpen;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        if(toOpen)subsystem.openOrCloseLeft(Constants.ClawConstants.Positions.OPEN);
        else subsystem.openOrCloseLeft(Constants.ClawConstants.Positions.CLOSE);
        startTime=subsystem.getTime();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
