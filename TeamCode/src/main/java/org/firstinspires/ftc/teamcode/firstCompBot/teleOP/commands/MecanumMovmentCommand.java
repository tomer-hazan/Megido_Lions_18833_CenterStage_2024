package org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.DriveTrainSubsystem;

import java.util.function.Supplier;

public class MecanumMovmentCommand extends CommandBase {
    private DriveTrainSubsystem driveTrainSubsystem;
    Supplier<Double> powewr;
    MecanumDrive m_drive;
    public MecanumMovmentCommand(DriveTrainSubsystem subsystem, Supplier<Double> power) {
        this.driveTrainSubsystem = subsystem;
        this.powewr=power;
        m_drive = (MecanumDrive) driveTrainSubsystem.getDrive();
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        m_drive.driveRobotCentric(powewr.get(),0,0);
    }


    @Override
    public boolean isFinished() {
        return true;
    }
}
