package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import java.util.function.Supplier;

public class waitCommand extends CommandBase {
    Supplier<Double> seconds;
    double start;
    double waitTime;
    public  waitCommand(Supplier<Double> time,double waitTime){

        this.waitTime = waitTime;
        seconds = time;
    }

    @Override
    public void initialize() {
        start = seconds.get();
    }

    @Override
    public boolean isFinished() {
        return seconds.get()-start>=waitTime;
    }
}
