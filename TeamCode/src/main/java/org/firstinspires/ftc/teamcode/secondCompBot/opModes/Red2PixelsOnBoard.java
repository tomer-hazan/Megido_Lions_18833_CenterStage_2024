package org.firstinspires.ftc.teamcode.secondCompBot.opModes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands.ControlArmCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands.DriveTrajectorySequenceCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands.SetArmsTarget;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.JointSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlClawsPosCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlLeftClawCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlRightClawCommand;

@Autonomous
@Config
public class Red2PixelsOnBoard extends CommandOpMode {
    SampleMecanumDrive drive;
    DriveTrainSubsystem driveTrainSubsystem;
    ClawSubsystem clawSubsystem;
    ArmSubsystem armSubsystem;
    JointSubsystem jointSubsystem;
    SequentialCommandGroup auto;
    Pose2d startPos;

    @Override
    public void initialize() {
        startPos = new Pose2d(15, -59, Math.toRadians(-90));
        initSubsystems();
        initRobot();
        initAutos();
        telemetry.update();
        drive.setPoseEstimate(startPos);
    }
    private void initAutos(){
        auto=makeAuto();
    }
    private void initSubsystems(){
        driveTrainSubsystem = new DriveTrainSubsystem(hardwareMap);
        drive = driveTrainSubsystem.getSampleDrive();
        clawSubsystem = new ClawSubsystem(hardwareMap,()->getRuntime());
        armSubsystem = new ArmSubsystem(hardwareMap);
        jointSubsystem = new JointSubsystem(hardwareMap);
    }
    private void initRobot(){
        clawSubsystem.openOrCloseRight(Constants.ClawConstants.Positions.CLOSE);
        clawSubsystem.openOrCloseLeft(Constants.ClawConstants.Positions.CLOSE);
        jointSubsystem.setDefaultCommand(new ControlClawsPosCommand(jointSubsystem,()->getRuntime()));
        armSubsystem.setDefaultCommand(new ControlArmCommand(armSubsystem));
    }
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();

        waitForStart();
        schedule(auto);
        if(isStopRequested())return;


        // run the scheduler
        while (!isStopRequested() && opModeIsActive()) {
            run();
        }
        reset();
    }
    public SequentialCommandGroup makeAuto(){
        DriveTrajectorySequenceCommand moveToBoard = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(startPos)
                .lineTo(new Vector2d(15, -28))
                .turn(Math.toRadians(-90))
                .lineTo(new Vector2d(47, -28))
                .build());
        SequentialCommandGroup auto;
        auto=new SequentialCommandGroup(
                moveToBoard,
                new SetArmsTarget(armSubsystem,4000),
                new ControlLeftClawCommand(clawSubsystem,true),
                new ControlRightClawCommand(clawSubsystem,true)
        );
        return  auto;
    }

    private boolean updates(){
        drive.update();
        telemetry.addData("pos",drive.getPoseEstimate());
        telemetry.update();
        return isStopRequested();
    }
    @Override
    public void run() {
        super.run();
        updates();
    }
}


