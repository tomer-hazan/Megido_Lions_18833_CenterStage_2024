package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.LEDSubsystem;

public class ChangeColorsCommand extends CommandBase {
    LEDSubsystem ledSubsystem;
    int color;
    public ChangeColorsCommand(LEDSubsystem ledSubsystem, int color){
        this.ledSubsystem=ledSubsystem;
        this.color = color;
    }

    @Override
    public void execute() {
        ledSubsystem.makeColor(color);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
