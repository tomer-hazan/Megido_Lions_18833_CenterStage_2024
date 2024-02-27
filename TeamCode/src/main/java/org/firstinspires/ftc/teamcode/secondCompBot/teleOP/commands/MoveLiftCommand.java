package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.SlideSubsystem;

import java.util.function.Supplier;

public class MoveLiftCommand extends CommandBase {
    private SlideSubsystem lift;
    private Supplier<Double> joyStick;

    public MoveLiftCommand(SlideSubsystem subsystem, Supplier<Double> supplier) {
        this.lift = subsystem;
        joyStick = supplier;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        lift.setRunMode(Motor.RunMode.RawPower);
    }

    @Override
    public void execute() {
        lift.setRunMode(Motor.RunMode.RawPower);
        lift.setPower(joyStick.get());
    }
    public double getPower(){
        return joyStick.get();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
