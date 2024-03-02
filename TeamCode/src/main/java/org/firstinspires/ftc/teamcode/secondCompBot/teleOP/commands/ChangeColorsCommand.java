package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.LEDSubsystem;

public class ChangeColorsCommand extends CommandBase {
    LEDSubsystem subsystem;
    int color;
    public ChangeColorsCommand(LEDSubsystem ledSubsystem, int color){
        this.subsystem=ledSubsystem;
        this.color = color;
        addRequirements(ledSubsystem);
    }

    @Override
    public void execute() {
        subsystem.makeColor(color);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
