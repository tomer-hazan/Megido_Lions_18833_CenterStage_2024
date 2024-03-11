package org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ArmSubsystem;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ArmConstants.f;

public class ControlArmCommand extends CommandBase {
    ArmSubsystem subsystem;
    public ControlArmCommand(ArmSubsystem subsystem){
        this.subsystem=subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.setPower(Math.cos(subsystem.getAngle())*f);
        Log.d("default command","is running");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
