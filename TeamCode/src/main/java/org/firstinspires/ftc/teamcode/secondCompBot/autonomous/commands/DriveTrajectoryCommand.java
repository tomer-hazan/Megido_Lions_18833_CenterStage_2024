package org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.DriveTrainSubsystem;

public class DriveTrajectoryCommand extends CommandBase {
    SampleMecanumDrive m_drive;
    Trajectory trajectory;
    public DriveTrajectoryCommand(DriveTrainSubsystem subsystem, Trajectory trajectorySequence) {
        m_drive = subsystem.getSampleDrive();
        trajectory = trajectorySequence;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        m_drive.followTrajectoryAsync(trajectory);
    }

    public Trajectory getTrajectory(){return trajectory;}

    @Override
    public boolean isFinished() {
        return !m_drive.isBusy();
    }
}

