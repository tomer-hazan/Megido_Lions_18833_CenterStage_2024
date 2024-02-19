package org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.DriveTrainSubsystem;

import java.util.function.Supplier;

public class DriveBackAndForceCommand extends CommandBase{
        private DriveTrainSubsystem driveTrainSubsystem;
        Supplier<Double> powewr;
        MecanumDrive m_drive;
        public DriveBackAndForceCommand(DriveTrainSubsystem subsystem, Supplier<Double> power) {
            this.driveTrainSubsystem = subsystem;
            this.powewr=power;
            m_drive = (MecanumDrive) driveTrainSubsystem.getDrive();
            addRequirements(subsystem);
        }

        @Override
        public void initialize() {
            driveTrainSubsystem.rightRear.set(0.25*powewr.get());
            driveTrainSubsystem.rightFront.set(0.25*powewr.get());
            driveTrainSubsystem.leftRear.set(0.25*powewr.get());
            driveTrainSubsystem.leftFront.set(0.25*powewr.get());
        }

    @Override
    public void end(boolean interrupted) {
        driveTrainSubsystem.rightRear.set(0);
        driveTrainSubsystem.rightFront.set(0);
        driveTrainSubsystem.leftRear.set(0);
        driveTrainSubsystem.leftFront.set(0);
    }

}

