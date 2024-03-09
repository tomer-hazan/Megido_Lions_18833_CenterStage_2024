package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.opModes.RobotOpMode;

public class ChangeSpeedCommand extends CommandBase {
    double speed;
    public ChangeSpeedCommand(double speed){
        this.speed=speed;
    }
    @Override
    public void initialize() {
        if(RobotOpMode.speed==speed)RobotOpMode.speed=1;
        else RobotOpMode.speed=speed;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
