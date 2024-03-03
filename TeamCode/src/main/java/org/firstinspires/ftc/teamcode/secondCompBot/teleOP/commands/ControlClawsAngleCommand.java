package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ClawSubsystem;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.angle_threshold;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.groundAngle;

public class ControlClawsAngleCommand extends CommandBase {
    ClawSubsystem subsystem;
    private Supplier<Double> armAngle;
    private double defaultAngle;

    public ControlClawsAngleCommand(ClawSubsystem subsystem, Supplier<Double> angle){
        this.subsystem=subsystem;
        this.armAngle=angle;
        defaultAngle = groundAngle;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
//        if(armAngle.get()<angle_threshold)subsystem.turnToAngle(defaultAngle);\
        if(armAngle.get()<angle_threshold)subsystem.turnToAngle(groundAngle);
        else subsystem.turnToAngle(150);
    }
    public void setDefaultAngle(double angle){
        defaultAngle = angle;
    }
    public double getDefaultAngle(){
        return defaultAngle;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}