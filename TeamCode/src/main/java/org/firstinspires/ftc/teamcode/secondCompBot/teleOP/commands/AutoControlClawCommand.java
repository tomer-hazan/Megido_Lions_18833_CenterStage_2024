package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.clawSubsystem;

public class AutoControlClawCommand extends CommandBase {
    clawSubsystem subsystem;

    public AutoControlClawCommand(clawSubsystem subsystem){
        this.subsystem=subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        if(subsystem.isDetectedPixelLeft())subsystem.openOrCloseLeft(Constants.ClawConstants.Positions.CLOSE);
        else subsystem.openOrCloseLeft(Constants.ClawConstants.Positions.OPEN);
        if(subsystem.isDetectedPixelRight())subsystem.openOrCloseRight(Constants.ClawConstants.Positions.CLOSE);
        else subsystem.openOrCloseRight(Constants.ClawConstants.Positions.OPEN);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
