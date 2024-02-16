package org.firstinspires.ftc.teamcode.firstCompBot.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.LiftSubsystem;

public class MoveLiftToIntakeCommand extends CommandBase {
    private LiftSubsystem lift;
    public final double maxHeight = 23000;
    Telemetry telemetry;

    public MoveLiftToIntakeCommand(LiftSubsystem subsystem, Telemetry telemetry) {
        this.lift = subsystem;
        this.telemetry =telemetry;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        lift.setRunMode(Motor.RunMode.PositionControl);
        // set and get the position coefficient
        lift.motor1.setPositionCoefficient(0.1);
        lift.motor2.setPositionCoefficient(0.1);
        lift.motor3.setPositionCoefficient(0.1);
        // set the tolerance
        lift.motor1.setPositionTolerance(50);   // allowed maximum error
        lift.motor2.setPositionTolerance(50);
        lift.motor3.setPositionTolerance(50);
        lift.setTarget(lift.motor1.getCurrentPosition());


    }

    @Override
    public void execute() {
        lift.setTarget(1000);
        lift.setPower(0.1);
    }
    public double getRealPower1(){
        return lift.motor1.get();
    }
    public double getRealPower2(){
        return lift.motor2.get();
    }

    @Override
    public boolean isFinished() {
        return lift.motor1.atTargetPosition();
    }
}
