package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ClawSubsystem;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.angle_threshold;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.rotation_limit;

public class ControlClawsAngleCommand extends CommandBase {
    ClawSubsystem subsystem;
    private Supplier<Double> armAngle;

    public ControlClawsAngleCommand(ClawSubsystem subsystem, Supplier<Double> angle){
        this.subsystem=subsystem;
        this.armAngle=angle;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        if(armAngle.get()<angle_threshold)subsystem.setPos(0);
        else subsystem.turnToAngle(rotation_limit- armAngle.get());

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
