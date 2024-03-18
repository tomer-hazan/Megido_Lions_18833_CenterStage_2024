package org.firstinspires.ftc.teamcode.secondCompBot.opModes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.VisionSubsystem;

@Autonomous
public class Auto extends CommandOpMode {
    ArmSubsystem armSubsystem;
    ClawSubsystem clawSubsystem;
    VisionSubsystem visionSubsystem;
    private void initSubsystems(){
        armSubsystem = new ArmSubsystem(hardwareMap);
        clawSubsystem = new ClawSubsystem(hardwareMap,()->getRuntime(),()->armSubsystem.getAngle());
        visionSubsystem = new VisionSubsystem(hardwareMap,telemetry, Constants.GameConstants.StartingPosition.BLUE);
    }
    @Override
    public void initialize() {
        initSubsystems();
//        armSubsystem.setDefaultCommand(new InstantCommand(armSubsystem::setPower));
    }
}
