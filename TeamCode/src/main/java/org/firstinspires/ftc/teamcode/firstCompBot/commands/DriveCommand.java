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
    Supplier<Boolean> fieldCentric;
    MecanumDrive  m_drive;
    public DriveCommand(DriveTrainSubsystem subsystem, Supplier<Double> leftX, Supplier<Double> leftY, Supplier<Double> rightY, Supplier<Boolean> fieldCentric) {
        this.driveTrainSubsystem = subsystem;
        this.leftX=leftX;
        this.leftY=leftY;
        this.rightY=rightY;
        this.fieldCentric = fieldCentric;
        m_drive = (MecanumDrive) driveTrainSubsystem.getDrive();
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        driveTrainSubsystem.resetEncoders();
    }

    @Override
    public void execute() {
        if(fieldCentric.get());//m_drive.driveFieldCentric(leftX.get(),leftY.get(),rightY.get(),);
        else m_drive.driveRobotCentric(leftX.get(),leftY.get(),rightY.get());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
