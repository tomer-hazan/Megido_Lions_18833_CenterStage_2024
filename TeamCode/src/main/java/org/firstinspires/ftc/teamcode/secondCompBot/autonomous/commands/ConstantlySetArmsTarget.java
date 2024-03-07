package org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ArmSubsystem;

import java.util.function.Supplier;

public class ConstantlySetArmsTarget extends CommandBase {
    ArmSubsystem subsystem;
    Supplier<Double> target;
    PIDController controller;
    public static double p=0.025,i=0.34,d=0.0055;
    public static double f=0.5;
    public ConstantlySetArmsTarget(ArmSubsystem subsystem, Supplier<Double> target){
        this.subsystem=subsystem;
        this.target = target;
        controller = new PIDController(p,i,d);
        controller.setTolerance(100);
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        double armPos = subsystem.motor.getCurrentPosition();
        double pid = controller.calculate(armPos, target.get());
        double ff = Math.cos(subsystem.getAngle())*f;
        double power = pid+ff;
        subsystem.setPower(power);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
