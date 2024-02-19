package org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.AirplaneSubsystem;


public class LaunchAirplaneCommand extends CommandBase {
    private AirplaneSubsystem airplaneSubsystem;

    public LaunchAirplaneCommand(AirplaneSubsystem subsystem) {
        this.airplaneSubsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        airplaneSubsystem.lunch();
    }

    @Override
    public void end(boolean interrupted) {
        airplaneSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
