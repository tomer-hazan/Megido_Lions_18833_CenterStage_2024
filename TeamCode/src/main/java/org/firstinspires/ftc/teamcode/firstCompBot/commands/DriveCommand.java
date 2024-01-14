package org.firstinspires.ftc.teamcode.firstCompBot.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.DriveTrainSubsystem;

import java.util.function.Supplier;

public class DriveCommand extends CommandBase {
    private DriveTrainSubsystem driveTrainSubsystem;
    Supplier<Double> leftX;
    Supplier<Double> leftY;
    Supplier<Double> rightY;
    public DriveCommand(DriveTrainSubsystem subsystem, Supplier<Double> leftX, Supplier<Double> leftY, Supplier<Double> rightY) {
        this.driveTrainSubsystem = subsystem;
        this.leftX=leftX;
        this.leftY=leftY;
        this.rightY=rightY;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        MecanumDrive  m_drive = (MecanumDrive) driveTrainSubsystem.getDrive();
        m_drive.driveRobotCentric(leftX.get(),leftY.get(),rightY.get());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
