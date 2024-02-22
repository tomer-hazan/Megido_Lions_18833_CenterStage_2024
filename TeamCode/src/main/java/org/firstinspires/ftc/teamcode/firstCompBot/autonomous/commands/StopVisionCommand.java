package org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.VisionSubsystem;

public class StopVisionCommand extends CommandBase {
    VisionSubsystem visionSubsystem;
    public StopVisionCommand(VisionSubsystem visionSubsystem){
        this.visionSubsystem=visionSubsystem;
    }

    @Override
    public void initialize() {
        visionSubsystem.stop();
    }
}
