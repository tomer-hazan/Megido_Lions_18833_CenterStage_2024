package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ClawSubsystem;

public class OpenOrCloseLeftClawCommand extends CommandBase {
    ClawSubsystem subsystem;

    public OpenOrCloseLeftClawCommand(ClawSubsystem subsystem){
        this.subsystem=subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        switch (subsystem.getLeftClawPos()){
            case OPEN:
                subsystem.openOrCloseLeft(Constants.ClawConstants.Positions.CLOSE);
                break;
            case CLOSE:
                subsystem.openOrCloseLeft(Constants.ClawConstants.Positions.OPEN);
                break;
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
