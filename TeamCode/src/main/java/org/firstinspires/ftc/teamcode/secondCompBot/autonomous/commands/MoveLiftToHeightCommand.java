package org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.SlideSubsystem;

public class MoveLiftToHeightCommand extends CommandBase {
    private SlideSubsystem lift;
    int target = 8600;
    Telemetry telemetry;
    boolean isFinished;

    public MoveLiftToHeightCommand(SlideSubsystem subsystem, Telemetry telemetry, int target) {
        this.lift = subsystem;
        this.telemetry =telemetry;
        this.target=target;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        lift.setRunMode(Motor.RunMode.PositionControl);
        lift.motor1.setPositionTolerance(200);
        lift.motor1.setPositionTolerance(200);
        lift.motor1.setPositionTolerance(200);
        lift.motor1.setPositionCoefficient(0.5);
        isFinished= false;
        lift.setTarget(target);
    }

    @Override
    public void execute() {
        lift.setTarget(target);
        lift.setPower(0.005);
        telemetry.addData("at pos",lift.motor1.atTargetPosition());
        telemetry.addData("pos",lift.getHeight());
        telemetry.addData("power",lift.motor1.get());
        telemetry.update();
    }

    @Override
    public void end(boolean interrupted) {
        lift.setPower(0);
    }

        @Override
    public boolean isFinished() {
        return lift.motor1.atTargetPosition();
    }
}
