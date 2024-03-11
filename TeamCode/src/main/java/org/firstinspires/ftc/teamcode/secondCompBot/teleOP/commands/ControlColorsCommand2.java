package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.LEDSubsystem;

import java.util.function.Supplier;

public class ControlColorsCommand2 extends CommandBase {
    LEDSubsystem ledSubsystem;
    Supplier<Boolean> leftPixel;
    Supplier<Boolean> rightPixel;
    public ControlColorsCommand2(LEDSubsystem ledSubsystem, Supplier<Boolean> leftPixel, Supplier<Boolean> rightPixel){
        this.ledSubsystem=ledSubsystem;
        this.leftPixel=leftPixel;
        this.rightPixel=rightPixel;
        addRequirements(ledSubsystem);
    }

    @Override
    public void execute() {
        if(leftPixel.get())ledSubsystem.bothLeft();
        else ledSubsystem.noneLeft();
        if(rightPixel.get())ledSubsystem.bothRight();
        else ledSubsystem.noneRight();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
