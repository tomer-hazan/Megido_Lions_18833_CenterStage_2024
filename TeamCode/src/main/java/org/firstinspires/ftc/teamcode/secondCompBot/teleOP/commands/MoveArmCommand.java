package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ArmSubsystem;

import java.util.function.Supplier;

public class MoveArmCommand extends CommandBase {
    ArmSubsystem subsystem;
    Supplier<Double> power;
    public MoveArmCommand(ArmSubsystem subsystem,Supplier<Double> power){
        this.subsystem=subsystem;
        this.power = power;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.setPower(power.get());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
