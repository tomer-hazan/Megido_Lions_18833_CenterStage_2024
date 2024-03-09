package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PController;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.SlideSubsystem;

public class MoveLiftToPosCommand extends CommandBase {
    private SlideSubsystem lift;
    private double targetPos;
    PController controller;

    public MoveLiftToPosCommand(SlideSubsystem subsystem, double pos) {
        this.lift = subsystem;
        targetPos =pos;
        addRequirements(subsystem);
        controller = new PController(0.015);
        controller.setSetPoint(pos);
    }

    @Override
    public void initialize() {
        lift.setRunMode(Motor.RunMode.RawPower);
    }

    @Override
    public void execute() {
        lift.setPower(controller.calculate(lift.getHeight()));
    }

    @Override
    public void end(boolean interrupted) {
        lift.setRunMode(Motor.RunMode.RawPower);
        lift.setPower(0);
    }

    @Override
    public boolean isFinished() {
        return controller.atSetPoint();
    }
}
