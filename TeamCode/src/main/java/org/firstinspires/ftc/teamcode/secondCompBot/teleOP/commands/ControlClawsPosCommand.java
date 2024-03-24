package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.JointSubsystem;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.JointConstants.angle_threshold;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.JointConstants.deg90Pos;

public class ControlClawsPosCommand extends CommandBase {
    JointSubsystem subsystem;
    private Supplier<Double> armDeg;

    public ControlClawsPosCommand(JointSubsystem subsystem, Supplier<Double> armDeg){
        this.subsystem=subsystem;
        this.armDeg =armDeg;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        if(armDeg.get()<angle_threshold)subsystem.setPos(subsystem.getDefaultPose());
        else subsystem.setPos(deg90Pos);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}