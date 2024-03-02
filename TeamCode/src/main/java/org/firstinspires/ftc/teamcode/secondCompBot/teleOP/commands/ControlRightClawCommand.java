package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ClawSubsystem;

public class ControlRightClawCommand extends CommandBase {
    ClawSubsystem subsystem;
    private boolean toOpen;

    public ControlRightClawCommand(ClawSubsystem subsystem, boolean toOpen){
        this.subsystem=subsystem;
        this.toOpen=toOpen;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        if(toOpen)subsystem.openOrCloseRight(Constants.ClawConstants.Positions.OPEN);
        else subsystem.openOrCloseRight(Constants.ClawConstants.Positions.CLOSE);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
