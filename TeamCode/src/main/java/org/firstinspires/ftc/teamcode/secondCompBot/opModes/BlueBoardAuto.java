package org.firstinspires.ftc.teamcode.secondCompBot.opModes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.Constants.GameConstants;
import org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands.ControlArmCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands.DriveTrajectorySequenceCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands.SetArmsTarget;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.HookSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.JointSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.SlideSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ChangeClawsDefaultPos;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlClawsPosCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlLeftClawCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlRightClawCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.waitCommand;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.JointConstants.deg90Pos;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.JointConstants.groundPos;

@Autonomous
@Config
public class BlueBoardAuto extends CommandOpMode {
    SampleMecanumDrive drive;
    DriveTrainSubsystem driveTrainSubsystem;
    SlideSubsystem slideSubsystem;
    ClawSubsystem clawSubsystem;
    JointSubsystem jointSubsystem;
    VisionSubsystem visionSubsystem;
    ArmSubsystem armSubsystem;
    SequentialCommandGroup autoLeft;
    SequentialCommandGroup autoCenter;
    SequentialCommandGroup autoRight;
    Pose2d startPos;
    HookSubsystem hookSubsystem;

    @Override
    public void initialize() {
        startPos = new Pose2d(15, 60, Math.toRadians(90));
        initSubsystems();
        initRobot();
        initAutos();
        telemetry.addData("gameType",visionSubsystem.getGameType());
        telemetry.update();
        drive.setPoseEstimate(startPos);
    }
    private void initAutos(){
        autoLeft=autoLeft();
        autoCenter=autoCenter();
        autoRight=autoRight();
    }
    private void initSubsystems(){
        driveTrainSubsystem = new DriveTrainSubsystem(hardwareMap);
        drive = driveTrainSubsystem.getSampleDrive();
        slideSubsystem = new SlideSubsystem(hardwareMap);
        jointSubsystem = new JointSubsystem(hardwareMap);
        visionSubsystem = new VisionSubsystem(hardwareMap,telemetry, GameConstants.StartingPosition.BLUE);
        armSubsystem = new ArmSubsystem(hardwareMap);
        hookSubsystem = new HookSubsystem(hardwareMap);
        clawSubsystem = new ClawSubsystem(hardwareMap,()->getRuntime(),()->armSubsystem.getAngle());
    }
    private void initRobot(){
        clawSubsystem.openOrCloseRight(Constants.ClawConstants.Positions.CLOSE);
        clawSubsystem.openOrCloseLeft(Constants.ClawConstants.Positions.CLOSE);
        jointSubsystem.setDefaultCommand(new ControlClawsPosCommand(jointSubsystem,()->getRuntime()));
        armSubsystem.setDefaultCommand(new ControlArmCommand(armSubsystem));
        hookSubsystem.lowerHook();
    }
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();

        waitForStart();
//        gameType = visionSubsystem.getGameType();
        visionSubsystem.stop();
        switch (visionSubsystem.getGameType()){
            case LEFT:
                schedule(autoLeft);
                break;
            case CENTER:
                schedule(autoCenter);
                break;
            case RIGHT:
                schedule(autoRight);
                break;
        }

        if(isStopRequested()){
            reset();
            stop();
            return;
        }
        // run the scheduler
        while (!isStopRequested() && opModeIsActive()) {
            run();
        }
        reset();
        stop();
    }
    public SequentialCommandGroup autoRight() {
        DriveTrajectorySequenceCommand moveToPixelPos = new DriveTrajectorySequenceCommand(driveTrainSubsystem, drive.trajectorySequenceBuilder(startPos)
                .lineTo(new Vector2d(12, 34))
                .turn(Math.toRadians(90))
                .forward(1)
                .build());
        DriveTrajectorySequenceCommand moveToBoard = new DriveTrajectorySequenceCommand(driveTrainSubsystem, drive.trajectorySequenceBuilder(moveToPixelPos.getTrajectory().end())
                .lineToConstantHeading(new Vector2d(47, 27))
                .build());
        DriveTrajectorySequenceCommand park = new DriveTrajectorySequenceCommand(driveTrainSubsystem, drive.trajectorySequenceBuilder(moveToBoard.getTrajectory().end())
                .lineTo(new Vector2d(47, 60))
                .lineTo(new Vector2d(60, 60))
                .build());
        SequentialCommandGroup auto;
        auto = new SequentialCommandGroup(
                new ChangeClawsDefaultPos(jointSubsystem,groundPos),
                new ParallelCommandGroup(moveToPixelPos, new ChangeClawsDefaultPos(jointSubsystem,groundPos)),
                new ControlLeftClawCommand(clawSubsystem,true),
                new waitCommand(()->getRuntime(),0.35),
                new ControlLeftClawCommand(clawSubsystem,false),
                new ChangeClawsDefaultPos(jointSubsystem,deg90Pos),
                new ParallelCommandGroup( moveToBoard, new SetArmsTarget(armSubsystem, 2500)),
                new SetArmsTarget(armSubsystem, 4000),
                new waitCommand(()->getRuntime(),0.35),
                new ControlRightClawCommand(clawSubsystem, true),
                new waitCommand(()->getRuntime(),0.35),
                new ParallelCommandGroup(new SetArmsTarget(armSubsystem, 0),new SequentialCommandGroup(new waitCommand(()->getRuntime(),0.1), new ControlRightClawCommand(clawSubsystem, false))),
                park
        );
        return auto;
    }
    public SequentialCommandGroup autoCenter(){
        DriveTrajectorySequenceCommand moveToPixelPos = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(startPos)
                .lineTo(new Vector2d(12,34))
                .turn(Math.toRadians(180))
                .build());
        DriveTrajectorySequenceCommand turnToBoard = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(moveToPixelPos.getTrajectory().end())
                .back(8)
                .turn(Math.toRadians(-90))
                .build());
        DriveTrajectorySequenceCommand moveToBoard = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(turnToBoard.getTrajectory().end())
                .lineToConstantHeading(new Vector2d(47, 32))
                .build());
        DriveTrajectorySequenceCommand park = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(moveToBoard.getTrajectory().end())
                .lineTo(new Vector2d(47, 60))
                .lineTo(new Vector2d(60, 60))
                .build());
        SequentialCommandGroup auto;
        auto=new SequentialCommandGroup(
                new ChangeClawsDefaultPos(jointSubsystem,groundPos),
                new ParallelCommandGroup(moveToPixelPos, new ChangeClawsDefaultPos(jointSubsystem,groundPos)),
                new ControlLeftClawCommand(clawSubsystem,true),
                new waitCommand(()->getRuntime(),0.35),
                new ControlLeftClawCommand(clawSubsystem,false),
                new ChangeClawsDefaultPos(jointSubsystem,deg90Pos),
                turnToBoard,
                new ParallelCommandGroup( moveToBoard, new SetArmsTarget(armSubsystem, 2500)),
                new SetArmsTarget(armSubsystem,4000),
                new waitCommand(()->getRuntime(),0.35),
                new ControlRightClawCommand(clawSubsystem, true),
                new waitCommand(()->getRuntime(),0.35),
                new ParallelCommandGroup(new SetArmsTarget(armSubsystem, 0),new SequentialCommandGroup(new waitCommand(()->getRuntime(),0.1), new ControlRightClawCommand(clawSubsystem, false))),
                park
        );
        return  auto;
    }
    public SequentialCommandGroup autoLeft(){
        DriveTrajectorySequenceCommand moveToPixelPos = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(startPos)
                .strafeRight(24)
                .back(30)
                .turn(Math.toRadians(90))
                .build());
        DriveTrajectorySequenceCommand moveForward = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(moveToPixelPos.getTrajectory().end())
                .forward(7)
                .build());
        DriveTrajectorySequenceCommand moveToBoard = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(moveForward.getTrajectory().end())
                .back(3)
                .lineToConstantHeading(new Vector2d(44, 39))
                .build());
        DriveTrajectorySequenceCommand park = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(moveToBoard.getTrajectory().end())
                .lineTo(new Vector2d(47, 60))
                .lineTo(new Vector2d(60, 60))
                .build());
        SequentialCommandGroup auto;
        auto=new SequentialCommandGroup(
                new ChangeClawsDefaultPos(jointSubsystem,deg90Pos),
                moveToPixelPos,
                new ChangeClawsDefaultPos(jointSubsystem,groundPos),
                moveForward,
                new ControlLeftClawCommand(clawSubsystem,true),
                new waitCommand(()->getRuntime(),0.35),
                new ControlLeftClawCommand(clawSubsystem,false),
                new ChangeClawsDefaultPos(jointSubsystem,deg90Pos),
                new ParallelCommandGroup( moveToBoard, new SetArmsTarget(armSubsystem, 2500)),
                new SetArmsTarget(armSubsystem,4000),
                new waitCommand(()->getRuntime(),0.35),
                new ControlRightClawCommand(clawSubsystem, true),
                new waitCommand(()->getRuntime(),0.35),
                new ParallelCommandGroup(new SetArmsTarget(armSubsystem, 0),new SequentialCommandGroup(new waitCommand(()->getRuntime(),0.1), new ControlRightClawCommand(clawSubsystem, false))),
                park
        );
        return  auto;
    }

    private boolean updates(){
        drive.update();
        telemetry.addData("pos",drive.getPoseEstimate());
        telemetry.addData("arm pos",armSubsystem.motor.getCurrentPosition());
        telemetry.update();
        return isStopRequested();
    }
    @Override
    public void run() {
        super.run();
        updates();
    }
}

