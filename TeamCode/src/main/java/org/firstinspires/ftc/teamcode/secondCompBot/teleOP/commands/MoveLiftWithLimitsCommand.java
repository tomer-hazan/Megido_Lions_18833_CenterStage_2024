package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.SlideSubsystem;

import java.util.function.Supplier;

public class MoveLiftWithLimitsCommand extends CommandBase {
    private SlideSubsystem lift;
    private Supplier<Double> joyStick;
    public final double maxHeight = 18000;
    Telemetry telemetry;

    public MoveLiftWithLimitsCommand(SlideSubsystem subsystem, Supplier<Double> supplier, Telemetry telemetry) {
        this.lift = subsystem;
        joyStick = supplier;
        this.telemetry =telemetry;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        lift.setRunMode(Motor.RunMode.PositionControl);
        // set and get the position coefficient
        lift.motor1.setPositionCoefficient(0.005);
        lift.motor2.setPositionCoefficient(0.005);
        lift.motor3.setPositionCoefficient(0.005);
        // set the tolerance
        lift.motor1.setPositionTolerance(100);   // allowed maximum error
        lift.motor2.setPositionTolerance(100);
        lift.motor3.setPositionTolerance(100);
        lift.setTarget(lift.motor1.getCurrentPosition());

    }

    @Override
    public void execute() {
        lift.setRunMode(Motor.RunMode.PositionControl);
        if(joyStick.get()<-0.05){
            lift.setTarget(-1000000000);
            if(!lift.isBottom())lift.setPower(Math.abs(joyStick.get()));
            telemetry.addData("target",0);
        } else if (joyStick.get()>0.05) {
            lift.setTarget((int) maxHeight);
            if(!lift.motor1.atTargetPosition()) lift.setPower(Math.abs(joyStick.get()));
            telemetry.addData("target",maxHeight);
        }
       {

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
