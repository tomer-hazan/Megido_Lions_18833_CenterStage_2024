package org.firstinspires.ftc.teamcode.BasicSample.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;

import org.firstinspires.ftc.teamcode.BasicSample.subsystems.BasicSubsystem;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class OdometryCommand extends CommandBase {
    private OdometrySubsystem odometry;

    public OdometryCommand(DoubleSupplier leftValue, DoubleSupplier rightValue, DoubleSupplier horizontalValue, double trackWidth, double centerWheelOffset) {
        HolonomicOdometry holonomicOdometry =new HolonomicOdometry(leftValue, rightValue, horizontalValue, trackWidth, centerWheelOffset);
        this.odometry = new OdometrySubsystem(holonomicOdometry);

        addRequirements(odometry);
    }

    @Override
    public void initialize() {
        odometry.periodic();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    public Pose2d getPos(){
        return odometry.getPose();
    }
    @Override
    public void execute() {
        odometry.update();
    }
}
