package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ClawSubsystem;

public class OpenOrCloseRightClawCommand extends CommandBase {
    ClawSubsystem subsystem;

    public OpenOrCloseRightClawCommand(ClawSubsystem subsystem){
        this.subsystem=subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        switch (subsystem.getRightClawPos()){
            case OPEN:
                subsystem.openOrCloseRight(Constants.ClawConstants.Positions.CLOSE);
                break;
            case CLOSE:
                subsystem.openOrCloseRight(Constants.ClawConstants.Positions.OPEN);
                break;
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
