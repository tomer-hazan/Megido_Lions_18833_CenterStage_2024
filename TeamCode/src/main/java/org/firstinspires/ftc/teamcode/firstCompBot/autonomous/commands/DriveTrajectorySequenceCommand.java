package org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

public class DriveTrajectorySequenceCommand extends CommandBase {
    SampleMecanumDrive m_drive;
    TrajectorySequence trajectory;
    public DriveTrajectorySequenceCommand(DriveTrainSubsystem subsystem, TrajectorySequence trajectorySequence) {
        m_drive = subsystem.getSampleDrive();
        trajectory = trajectorySequence;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        m_drive.followTrajectorySequenceAsync(trajectory);
    }

    public TrajectorySequence getTrajectory(){return trajectory;}

    @Override
    public boolean isFinished() {
        return !m_drive.isBusy();
    }
}

