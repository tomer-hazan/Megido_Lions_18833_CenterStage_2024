package org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ArmSubsystem;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ArmConstants.ticks_in_degree;

public class SetArmsTarget extends CommandBase {
    ArmSubsystem subsystem;
    double target;
    PIDController controller;
    public static double p=0.025,i=0.34,d=0.0055;
    public static double f=0.5;
    public SetArmsTarget(ArmSubsystem subsystem, double target){
        this.subsystem=subsystem;
        this.target = target;
        controller = new PIDController(p,i,d);
        controller.setTolerance(100);
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        double armPos = subsystem.motor.getCurrentPosition();
        double pid = controller.calculate(armPos, target);
        double ff = Math.cos(target/ticks_in_degree)*f;
        double power = pid+ff;
        subsystem.setPower(-power);
    }

    @Override
    public boolean isFinished() {
        return controller.atSetPoint();
    }
}