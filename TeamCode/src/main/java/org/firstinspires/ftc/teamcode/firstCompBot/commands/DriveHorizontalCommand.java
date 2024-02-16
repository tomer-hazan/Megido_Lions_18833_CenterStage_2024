package org.firstinspires.ftc.teamcode.firstCompBot.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.DriveTrainSubsystem;

import java.util.function.Supplier;

public class DriveHorizontalCommand extends CommandBase {
    private DriveTrainSubsystem driveTrainSubsystem;
    Supplier<Double> leftX;
    MecanumDrive  m_drive;
    Supplier<Double> heading;
    public DriveHorizontalCommand(DriveTrainSubsystem subsystem, Supplier<Double> leftX, Supplier<Double> heading) {
        this.driveTrainSubsystem = subsystem;
        this.leftX=leftX;
        this.heading=heading;
        m_drive = (MecanumDrive) driveTrainSubsystem.getDrive();
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
    }

    public void drive() {
        m_drive.driveFieldCentric(0,leftX.get(),0,heading.get());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
