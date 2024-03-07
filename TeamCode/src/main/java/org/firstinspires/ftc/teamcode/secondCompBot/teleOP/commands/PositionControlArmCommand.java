package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ArmSubsystem;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ArmConstants.f;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ArmConstants.ticks_in_degree;

public class PositionControlArmCommand extends CommandBase {
    ArmSubsystem subsystem;
    Supplier<Double> power;
    public int target;
    boolean isStopped;
    public PositionControlArmCommand(ArmSubsystem subsystem, Supplier<Double> power){
        this.subsystem=subsystem;
        this.power = power;
        addRequirements(subsystem);
        target=0;
    }

    @Override
    public void execute() {
        double stoppingPower = 0;
        if(Math.abs(power.get())<0.05){
            stoppingPower=Math.cos(subsystem.motor.getCurrentPosition() /ticks_in_degree)*f;
            isStopped=true;
        }else isStopped=false;
        subsystem.setPower(power.get()-stoppingPower);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
