package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.JointSubsystem;

public class ChangeClawsDefaultPos extends CommandBase {
    JointSubsystem subsystem;
    double newPos;
    public ChangeClawsDefaultPos(JointSubsystem jointSubsystem,double pos){
        subsystem = jointSubsystem;
        newPos = pos;
    }

    @Override
    public void initialize() {
        subsystem.setDefaultPose(newPos);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
