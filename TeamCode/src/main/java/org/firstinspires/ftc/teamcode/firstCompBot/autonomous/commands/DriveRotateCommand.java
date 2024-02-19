package org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.function.Supplier;

public class DriveRotateCommand extends CommandBase {
    SampleMecanumDrive m_drive;
    Supplier<Double> angel;
    TrajectorySequence trajectory;
    public DriveRotateCommand(DriveTrainSubsystem subsystem, Supplier<Double> angle) {
        m_drive = subsystem.getSampleDrive();
        this.angel= angle;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        double ang = angel.get();
        trajectory =m_drive.trajectorySequenceBuilder(new Pose2d())
                .turn(ang)
                .build();

        m_drive.followTrajectorySequenceAsync(trajectory);
    }

    public TrajectorySequence getTrajectory(){return trajectory;}

    @Override
    public boolean isFinished() {
        return !m_drive.isBusy();
    }
}

