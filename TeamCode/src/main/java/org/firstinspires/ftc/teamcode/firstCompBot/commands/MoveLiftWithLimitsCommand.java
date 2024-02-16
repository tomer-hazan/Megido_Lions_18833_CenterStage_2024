package org.firstinspires.ftc.teamcode.firstCompBot.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.LiftSubsystem;

import java.util.function.Supplier;

public class MoveLiftWithLimitsCommand extends CommandBase {
    private LiftSubsystem lift;
    private Supplier<Double> joyStick;
    public final double maxHeight = 23000;
    Telemetry telemetry;

    public MoveLiftWithLimitsCommand(LiftSubsystem subsystem, Supplier<Double> supplier, Telemetry telemetry) {
        this.lift = subsystem;
        joyStick = supplier;
        this.telemetry =telemetry;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        lift.setRunMode(Motor.RunMode.PositionControl);
        // set and get the position coefficient
        lift.motor1.setPositionCoefficient(0.05);
        lift.motor2.setPositionCoefficient(0.05);
        // set the tolerance
        lift.motor1.setPositionTolerance(100);   // allowed maximum error
        lift.motor2.setPositionTolerance(100);
        lift.setTarget(lift.motor1.getCurrentPosition());

    }

    @Override
    public void execute() {
        lift.setRunMode(Motor.RunMode.PositionControl);
        if(joyStick.get()<-0.05){
            lift.setTarget(-100);
            telemetry.addData("target",0);
        } else if (joyStick.get()>0.05) {
            lift.setTarget((int) maxHeight);
            telemetry.addData("target",maxHeight);
        }
        if(!lift.motor1.atTargetPosition()){
            lift.setPower(Math.abs(joyStick.get()));;
        }
        else {
            lift.stopMotors();
        }
    }
    public double getPower(){
        return joyStick.get();
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
