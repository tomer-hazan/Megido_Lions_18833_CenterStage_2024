package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.LEDSubsystem;

import java.util.function.Supplier;

public class ControlColorsCommand extends CommandBase {
    LEDSubsystem ledSubsystem;
    Supplier<Constants.GameElements.Pixals> leftPixel;
    Supplier<Constants.GameElements.Pixals> rightPixel;
    public ControlColorsCommand(LEDSubsystem ledSubsystem, Supplier<Constants.GameElements.Pixals> leftPixel,Supplier<Constants.GameElements.Pixals> rightPixel){
        this.ledSubsystem=ledSubsystem;
        this.leftPixel=leftPixel;
        this.rightPixel=rightPixel;
    }

    @Override
    public void execute() {
        ledSubsystem.makeColorLeft(leftPixel.get());
        ledSubsystem.makeColorRight(rightPixel.get());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
