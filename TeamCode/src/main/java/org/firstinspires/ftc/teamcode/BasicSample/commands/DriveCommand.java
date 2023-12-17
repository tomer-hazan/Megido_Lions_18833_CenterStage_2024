package org.firstinspires.ftc.teamcode.BasicSample.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.BasicSample.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.BasicSample.subsystems.LiftSubsystem;

import java.util.function.Supplier;

public class DriveCommand extends CommandBase {
private DriveTrainSubsystem driveTrainSubsystem;
    Supplier<Double> leftX;
    Supplier<Double> leftY;
    Supplier<Double> rightY;
    public DriveCommand(DriveTrainSubsystem subsystem, Supplier<Double> leftX,Supplier<Double> leftY,Supplier<Double> rightY) {
        this.driveTrainSubsystem = subsystem;
        this.leftX=leftX;
        this.leftY=leftY;
        this.rightY=rightY;
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
