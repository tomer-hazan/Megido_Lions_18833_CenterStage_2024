package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.clawSubsystem;

public class ControlRightClawCommand extends CommandBase {
    clawSubsystem subsystem;
    private boolean toOpen;

    public ControlRightClawCommand(clawSubsystem subsystem, boolean toOpen){
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
