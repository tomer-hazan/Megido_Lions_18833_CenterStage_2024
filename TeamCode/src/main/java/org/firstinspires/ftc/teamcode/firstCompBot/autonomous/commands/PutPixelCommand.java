package org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.firstCompBot.Constants;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.InTakeSubsystem;

import static java.lang.System.currentTimeMillis;

public class PutPixelCommand extends CommandBase {
    private InTakeSubsystem inTake;
    int target = 8600;
    Telemetry telemetry;
    long startTime;
    Constants.GameConstants.GameType gameType;

    public PutPixelCommand(InTakeSubsystem subsystem, Telemetry telemetry, Constants.GameConstants.GameType gameType) {
        this.inTake = subsystem;
        this.telemetry =telemetry;
        this.gameType = gameType;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        startTime=currentTimeMillis();
        inTake.setDirection(Constants.InTakeConstants.outDirection);
        inTake.setPower(powers());
    }


    @Override
    public void end(boolean interrupted) {
        inTake.setPower(0);
    }

    @Override
    public boolean isFinished() {
        return currentTimeMillis()-startTime>2000;
    }
    public double powers(){
        switch (gameType){
            case LEFT:
                return 0.3;
            case CENTER:
                return 0.4;
            case RIGHT:
                return 0.3;
        }
        return 0;
    }
}
