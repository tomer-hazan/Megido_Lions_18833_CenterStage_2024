package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.DriveTrainSubsystem;

import java.util.function.Supplier;
@Config
public class MecanumMovmentCommand extends CommandBase {
    private DriveTrainSubsystem driveTrainSubsystem;
    Supplier<Double> rearPower;
    MecanumDrive m_drive;
    Supplier<Double> frontPower;
    public static double multiply;

    public MecanumMovmentCommand(DriveTrainSubsystem subsystem, Supplier<Double> frontPower,Supplier<Double> rearPower) {
        this.driveTrainSubsystem = subsystem;
        this.frontPower=frontPower;
        m_drive = (MecanumDrive) driveTrainSubsystem.getDrive();
        this.rearPower = rearPower;
        addRequirements(subsystem);
        multiply=1;
    }

    @Override
    public void initialize() {
        driveTrainSubsystem.leftFront.set(frontPower.get());
        driveTrainSubsystem.rightRear.set(rearPower.get());
        driveTrainSubsystem.leftRear.set(-rearPower.get());
        driveTrainSubsystem.rightFront.set(-frontPower.get());
    }


    @Override
    public boolean isFinished() {
        return true;
    }
}
