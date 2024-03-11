package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.DriveTrainSubsystem;

import java.util.function.Supplier;

public class DriveCommand extends CommandBase {
    private DriveTrainSubsystem driveTrainSubsystem;
    Supplier<Double> leftX;
    Supplier<Double> leftY;
    Supplier<Double> rightX;
    boolean fieldCentric;
    MecanumDrive m_drive;
    Supplier<Double> heading;
    public DriveCommand(DriveTrainSubsystem subsystem, Supplier<Double> leftX, Supplier<Double> leftY, Supplier<Double> rightX) {
        this.driveTrainSubsystem = subsystem;
        this.leftX=leftX;
        this.leftY=leftY;
        this.rightX=rightX;
        this.fieldCentric = false;
        m_drive = (MecanumDrive) driveTrainSubsystem.getDrive();
        addRequirements(subsystem);
    }
    public DriveCommand(DriveTrainSubsystem subsystem, Supplier<Double> leftX, Supplier<Double> leftY, Supplier<Double> rightX,Supplier<Double> heading) {
        this.driveTrainSubsystem = subsystem;
        this.leftX=leftX;
        this.leftY=leftY;
        this.rightX=rightX;
        this.fieldCentric = true;
        this.heading= heading;
        m_drive = (MecanumDrive) driveTrainSubsystem.getDrive();
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if(fieldCentric)m_drive.driveFieldCentric(leftX.get(),leftY.get(),0.75*rightX.get(),heading.get());
        else m_drive.driveRobotCentric(leftX.get(),leftY.get(),0.75*rightX.get(),true);
        Log.d("drive", "robot centric");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
