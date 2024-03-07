package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.JointSubsystem;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.JointConstants.angle_threshold;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.JointConstants.groundAngle;

public class ControlClawsAngleCommand extends CommandBase {
    JointSubsystem subsystem;
    private Supplier<Double> armAngle;
    private double defaultAngle;

    public ControlClawsAngleCommand(JointSubsystem subsystem, Supplier<Double> angle){
        this.subsystem=subsystem;
        this.armAngle=angle;
        defaultAngle = groundAngle;
        addRequirements(subsystem);
    }
    public ControlClawsAngleCommand(JointSubsystem subsystem, Supplier<Double> armAngle,double defaultAngle){
        this.subsystem=subsystem;
        this.armAngle=armAngle;
        this.defaultAngle = defaultAngle;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        if(armAngle.get()<angle_threshold)subsystem.turnToAngle(defaultAngle);
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