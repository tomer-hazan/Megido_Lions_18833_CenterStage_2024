package org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.LEDSubsystem;

public class SwitchColorsCommand extends CommandBase {
    public int color;
    LEDSubsystem ledSubsystem;
    public SwitchColorsCommand(LEDSubsystem ledSubsystem){
        this.ledSubsystem = ledSubsystem;
        addRequirements(ledSubsystem);
        color=0;
    }
    @Override
    public void initialize() {
        super.initialize();
        ledSubsystem.green();
    }

    @Override
    public void execute() {
        super.execute();
        ledSubsystem.makeColor(color);
        color++;
    }

    public void setColor(int color){
        this.color=color;
    }
    public int getColor(){
        return color%4;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
