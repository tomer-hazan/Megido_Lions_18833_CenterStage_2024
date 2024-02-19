package org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.firstCompBot.opModes.RobotOpMode;

public class ChangeSpeedCommand extends CommandBase {
    @Override
    public void initialize() {
        if(RobotOpMode.speed==0)RobotOpMode.speed=1;
        else RobotOpMode.speed=0;
        //RobotOpMode.slow();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
