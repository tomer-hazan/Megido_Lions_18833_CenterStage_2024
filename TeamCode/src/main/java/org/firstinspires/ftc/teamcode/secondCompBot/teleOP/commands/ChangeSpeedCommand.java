package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.opModes.RobotOpMode;

public class ChangeSpeedCommand extends CommandBase {
    double speed;
    double turnSpeed;
    public ChangeSpeedCommand(double speed,double turnSpeed){
        this.speed=speed;
        this.turnSpeed=turnSpeed;
    }
    @Override
    public void initialize() {
        RobotOpMode.speed=speed;
        RobotOpMode.turnSpeed=turnSpeed;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
