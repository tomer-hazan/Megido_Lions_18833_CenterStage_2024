//package org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands;
//
//
//import com.arcrobotics.ftclib.command.CommandBase;
//
//import org.firstinspires.ftc.teamcode.firstCompBot.Constants.HookConstants;
//import HookSubsystem;
//
//import java.util.function.Supplier;
//
//public class PullRobotCommand extends CommandBase {
//    private HookSubsystem subsystem;
//    Supplier<Double> power;
//    public PullRobotCommand(HookSubsystem subsystem, Supplier<Double> power) {
//        this.subsystem = subsystem;
//        addRequirements(subsystem);
//        this.power=power;
//    }
//
//    @Override
//    public void initialize() {
//        subsystem.setSuspensionDirection(HookConstants.up_direction_inverted);
//    }
//
//    @Override
//    public void execute() {
//        subsystem.setSuspensionPower(HookConstants.power*power.get());
//    }
//
//    @Override
//    public boolean isFinished() {
//        return false;
//    }
//}
