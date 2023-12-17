package org.firstinspires.ftc.teamcode.BasicSample.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.BasicSample.subsystems.LiftSubsystem;

import java.util.function.Supplier;

public class MoveLiftCommand extends CommandBase {
    private LiftSubsystem lift;
    private Supplier<Double> joyStick;

    public MoveLiftCommand(LiftSubsystem subsystem, Supplier<Double> supplier) {
        this.lift = subsystem;
        joyStick = supplier;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        lift.setRunMode(Motor.RunMode.RawPower);
        if(joyStick.get()>0 && !lift.isTop())lift.setPower(joyStick.get());
        else if (joyStick.get()<0 && !lift.isBottom())lift.setPower(joyStick.get());
        else lift.setPower(joyStick.get());
    }

    @Override
    public void execute() {
        double power = joyStick.get();
        if (!((lift.isBottom() && power < 0) || (lift.isTop() && power > 0))) {
            lift.setPower(joyStick.get());
        } else {
            lift.setPower(0);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
