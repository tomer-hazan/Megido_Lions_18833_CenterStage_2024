package org.firstinspires.ftc.teamcode.firstCompBot.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.LiftSubsystem;

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
//        if(joyStick.get()>0 && !lift.isTop())lift.setPower(joyStick.get());
//        else if (joyStick.get()<0 && !lift.isBottom())lift.setPower(joyStick.get());
//        else lift.setPower(joyStick.get());
        lift.setPower(0);
    }

    @Override
    public void execute() {
        lift.setRunMode(Motor.RunMode.RawPower);
//        double power = joyStick.get();
//        if (!((lift.isBottom() && power < 0) || (lift.isTop() && power > 0))) {
//            lift.setPower(power);
//        } else {
//            lift.setPower(0);
//        }
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
