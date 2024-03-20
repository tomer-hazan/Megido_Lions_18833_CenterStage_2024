package org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.AprilTagSubsystem;

public class WaitForClearanceCommand extends CommandBase {
    AprilTagSubsystem aprilTagSubsystem;
    public WaitForClearanceCommand(AprilTagSubsystem subsystem){
        aprilTagSubsystem=subsystem;
    }

    @Override
    public void initialize() {
        aprilTagSubsystem.resumeDetecting();
    }

    @Override
    public boolean isFinished() {
        return aprilTagSubsystem.getNumberOfAprilTags()>=3;
    }

    @Override
    public void end(boolean interrupted) {
        aprilTagSubsystem.stopDetecting();
    }
}
