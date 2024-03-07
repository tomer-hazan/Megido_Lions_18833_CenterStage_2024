package org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.JointSubsystem;

public class RotateClawsToOneAngleCommand extends CommandBase {
    JointSubsystem subsystem;
    private  double angle;

    public RotateClawsToOneAngleCommand(JointSubsystem subsystem, double angle){
        this.subsystem=subsystem;
        this.angle=angle;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        subsystem.turnToAngle(angle);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
