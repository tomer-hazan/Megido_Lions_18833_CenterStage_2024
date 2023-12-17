package org.firstinspires.ftc.teamcode.firstCompBot.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.AirplaneSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.LiftSubsystem;

import java.util.function.Supplier;

public class LaunchAirplaneCommand extends CommandBase {
    private AirplaneSubsystem airplaneSubsystem;

    public LaunchAirplaneCommand(AirplaneSubsystem subsystem) {
        this.airplaneSubsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        if(!airplaneSubsystem.hasLunched()){
            airplaneSubsystem.lunch();
            airplaneSubsystem.setPosition(1);
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
