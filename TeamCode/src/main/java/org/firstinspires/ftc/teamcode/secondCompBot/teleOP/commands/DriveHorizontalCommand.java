package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.DriveTrainSubsystem;

import java.util.function.Supplier;

public class DriveHorizontalCommand extends CommandBase {
    private DriveTrainSubsystem driveTrainSubsystem;
    Supplier<Double> strafe;
    MecanumDrive  m_drive;
    Supplier<Double> heading;
    public DriveHorizontalCommand(DriveTrainSubsystem subsystem, Supplier<Double> leftX, Supplier<Double> heading) {
        this.driveTrainSubsystem = subsystem;
        this.strafe=leftX;
        this.heading=heading;
        m_drive = (MecanumDrive) driveTrainSubsystem.getDrive();
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        m_drive.driveFieldCentric(strafe.get(),0,0,heading.get());
        Log.d("drive", "field centric");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
