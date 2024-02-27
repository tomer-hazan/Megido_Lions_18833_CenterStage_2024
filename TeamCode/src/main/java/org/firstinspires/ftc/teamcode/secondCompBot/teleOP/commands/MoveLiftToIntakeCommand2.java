package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.SlideSubsystem;

public class MoveLiftToIntakeCommand2 extends CommandBase {
    private SlideSubsystem lift;
    public final double maxHeight = 18000;
    PIDController pidController;
    Telemetry telemetry;

    public MoveLiftToIntakeCommand2(SlideSubsystem subsystem, Telemetry telemetry) {
        this.lift = subsystem;
        this.telemetry =telemetry;
        pidController = new PIDController(4,0.2,0.5);
        pidController.setSetPoint(1000);
        pidController.setTolerance(50);
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        lift.setRunMode(Motor.RunMode.VelocityControl);

    }


    @Override
    public void execute() {
        double output = pidController.calculate(lift.motor1.getCurrentPosition());
        lift.setVelocity(output);
        if(pidController.atSetPoint()){
            lift.stopMotors();
            cancel();
        }
    }
    public double getRealPower1(){
        return lift.motor1.get();
    }
    public double getRealPower2(){
        return lift.motor2.get();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
