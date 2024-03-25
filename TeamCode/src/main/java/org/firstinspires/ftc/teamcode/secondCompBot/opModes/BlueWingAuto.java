package org.firstinspires.ftc.teamcode.secondCompBot.opModes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
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
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.WaitCommand;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.JointConstants.deg90Pos;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.JointConstants.groundPos;

@Autonomous
@Config
public class BlueWingAuto extends CommandOpMode {
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
    public static double slowMove=30;
    public static double normalMove=50;

    @Override
    public void initialize() {
        startPos = new Pose2d(-39, 60, Math.toRadians(90));
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
        visionSubsystem = new VisionSubsystem(hardwareMap,telemetry, GameConstants.StartingColor.BLUE, GameConstants.StartingPos.WING);
        armSubsystem = new ArmSubsystem(hardwareMap);
        hookSubsystem = new HookSubsystem(hardwareMap);
        clawSubsystem = new ClawSubsystem(hardwareMap,()->getRuntime(),()->armSubsystem.getAngle());
    }
    private void initRobot(){
        clawSubsystem.openOrCloseRight(Constants.ClawConstants.Positions.CLOSE);
        clawSubsystem.openOrCloseLeft(Constants.ClawConstants.Positions.CLOSE);
        jointSubsystem.setDefaultCommand(new ControlClawsPosCommand(jointSubsystem,()->0.0));
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

    public SequentialCommandGroup autoRight(){
        DriveTrajectorySequenceCommand moveToPixelPos = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(startPos)
                .setVelConstraint(new MecanumVelocityConstraint(normalMove,DriveConstants.TRACK_WIDTH))
                .setAccelConstraint(new ProfileAccelerationConstraint(normalMove))
                .lineToConstantHeading(new Vector2d(-39,28))
                .turn(Math.toRadians(90))
                .build());
        DriveTrajectorySequenceCommand moveToWaitPos = new DriveTrajectorySequenceCommand(driveTrainSubsystem, drive.trajectorySequenceBuilder(moveToPixelPos.getTrajectory().end())
                .setVelConstraint(new MecanumVelocityConstraint(normalMove,DriveConstants.TRACK_WIDTH))
                .setAccelConstraint(new ProfileAccelerationConstraint(normalMove))
                .lineToConstantHeading(new Vector2d(-39,10))
                .lineToConstantHeading(new Vector2d(12,10))
                .build());
        DriveTrajectorySequenceCommand moveToBoard = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(moveToWaitPos.getTrajectory().end())
                .setVelConstraint(new MecanumVelocityConstraint(normalMove,DriveConstants.TRACK_WIDTH))
                .setAccelConstraint(new ProfileAccelerationConstraint(normalMove))
                .lineToConstantHeading(new Vector2d(40,10))
                .lineToConstantHeading(new Vector2d(40,29))
                .setVelConstraint(new MecanumVelocityConstraint(slowMove,DriveConstants.TRACK_WIDTH))
                .setAccelConstraint(new ProfileAccelerationConstraint(slowMove))
                .lineToConstantHeading(new Vector2d(49, 29))
                .build());
        SequentialCommandGroup auto;
        auto=new SequentialCommandGroup(
                new ChangeClawsDefaultPos(jointSubsystem,groundPos),
                moveToPixelPos,
                new ControlRightClawCommand(clawSubsystem,true),
                new WaitCommand(()->getRuntime(),0.35),
                new ChangeClawsDefaultPos(jointSubsystem,deg90Pos),
                new WaitCommand(()->getRuntime(),0.35),
                new ControlRightClawCommand(clawSubsystem,false),
                moveToWaitPos,
                new SetArmsTarget(armSubsystem,4300),
                new WaitCommand(()->getRuntime(),0.35),//the waiting time
                moveToBoard,
                new ChangeClawsDefaultPos(jointSubsystem,0.8),
                new WaitCommand(()->getRuntime(),0.35),//the waiting time
                new ControlLeftClawCommand(clawSubsystem, true),
        new WaitCommand(()->getRuntime(),0.35),
                new ParallelCommandGroup(new SetArmsTarget(armSubsystem, 0),new SequentialCommandGroup(new WaitCommand(()->getRuntime(),0.2), new ControlLeftClawCommand(clawSubsystem, false)))
        );
        return  auto;
    }
    public SequentialCommandGroup autoCenter(){
        DriveTrajectorySequenceCommand moveToPixelPos1 = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(startPos)
                .setVelConstraint(new MecanumVelocityConstraint(normalMove,DriveConstants.TRACK_WIDTH))
                .setAccelConstraint(new ProfileAccelerationConstraint(normalMove))
                .lineToConstantHeading(new Vector2d(-56,60))
                .lineToConstantHeading(new Vector2d(-56, 12))
                .build());
        DriveTrajectorySequenceCommand moveToPixelPos2 = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(moveToPixelPos1.getTrajectory().end())
                .setVelConstraint(new MecanumVelocityConstraint(normalMove,DriveConstants.TRACK_WIDTH))
                .setAccelConstraint(new ProfileAccelerationConstraint(normalMove))
                .lineToConstantHeading(new Vector2d(-39, 12))
                .build());
        DriveTrajectorySequenceCommand moveToWaitPos = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(moveToPixelPos2.getTrajectory().end())
                .setVelConstraint(new MecanumVelocityConstraint(normalMove,DriveConstants.TRACK_WIDTH))
                .setAccelConstraint(new ProfileAccelerationConstraint(normalMove))
                .back(4)
                .turn(Math.toRadians(90))
                .lineToConstantHeading(new Vector2d(12, 10))
                .build());
        DriveTrajectorySequenceCommand moveToBoard = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(moveToWaitPos.getTrajectory().end())
                .setVelConstraint(new MecanumVelocityConstraint(normalMove,DriveConstants.TRACK_WIDTH))
                .setAccelConstraint(new ProfileAccelerationConstraint(normalMove))
                .lineToConstantHeading(new Vector2d(40,10))
                .turn(Math.toRadians(-100))
                .lineToConstantHeading(new Vector2d(40,30))
                .turn(Math.toRadians(100))
                .build());
        DriveTrajectorySequenceCommand moveInToBoard = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(moveToBoard.getTrajectory().end())
                .setVelConstraint(new MecanumVelocityConstraint(slowMove,DriveConstants.TRACK_WIDTH))
                .setAccelConstraint(new ProfileAccelerationConstraint(slowMove))
                .lineToConstantHeading(new Vector2d(50, 30))
                .build());
        SequentialCommandGroup auto;
        auto=new SequentialCommandGroup(
                new ChangeClawsDefaultPos(jointSubsystem,deg90Pos),
                moveToPixelPos1,
                new ChangeClawsDefaultPos(jointSubsystem,groundPos),
                new WaitCommand(()->getRuntime(),1),
                moveToPixelPos2,
                new ControlRightClawCommand(clawSubsystem,true),
                new WaitCommand(()->getRuntime(),0.35),
                new ChangeClawsDefaultPos(jointSubsystem,deg90Pos),
                new WaitCommand(()->getRuntime(),0.35),
                new ControlRightClawCommand(clawSubsystem,false),
                moveToWaitPos,
                moveToBoard,
                new SetArmsTarget(armSubsystem,4300),
                new WaitCommand(()->getRuntime(),0.35),//the waiting time
                moveInToBoard,
                new ChangeClawsDefaultPos(jointSubsystem,0.8),
                new WaitCommand(()->getRuntime(),0.35),
                new ControlLeftClawCommand(clawSubsystem, true),
        new WaitCommand(()->getRuntime(),0.35),
                new ParallelCommandGroup(new SetArmsTarget(armSubsystem, 0),new SequentialCommandGroup(new WaitCommand(()->getRuntime(),0.2), new ControlLeftClawCommand(clawSubsystem, false)))
        );
        return  auto;
    }
    public SequentialCommandGroup autoLeft() {
        DriveTrajectorySequenceCommand moveToPixelPos = new DriveTrajectorySequenceCommand(driveTrainSubsystem, drive.trajectorySequenceBuilder(startPos)
                .setVelConstraint(new MecanumVelocityConstraint(normalMove,DriveConstants.TRACK_WIDTH))
                .setAccelConstraint(new ProfileAccelerationConstraint(normalMove))
                .lineToConstantHeading(new Vector2d(-38, 30))
                .turn(Math.toRadians(-100))
                .build());
        DriveTrajectorySequenceCommand moveToWaitPos = new DriveTrajectorySequenceCommand(driveTrainSubsystem, drive.trajectorySequenceBuilder(moveToPixelPos.getTrajectory().end())
                .setVelConstraint(new MecanumVelocityConstraint(normalMove,DriveConstants.TRACK_WIDTH))
                .setAccelConstraint(new ProfileAccelerationConstraint(normalMove))
                .lineToConstantHeading(new Vector2d(-50, 30))
                .turn(Math.toRadians(100))
                .lineToConstantHeading(new Vector2d(-50,10))
                .turn(Math.toRadians(90))
                .lineToConstantHeading(new Vector2d(12,10))
                .build());
        DriveTrajectorySequenceCommand moveToBoard = new DriveTrajectorySequenceCommand(driveTrainSubsystem, drive.trajectorySequenceBuilder(moveToWaitPos.getTrajectory().end())
                .setVelConstraint(new MecanumVelocityConstraint(normalMove,DriveConstants.TRACK_WIDTH))
                .setAccelConstraint(new ProfileAccelerationConstraint(normalMove))
                .lineToConstantHeading(new Vector2d(40,10))
                .turn(Math.toRadians(-100))
                .lineToConstantHeading(new Vector2d(40, 38.5))
                .turn(Math.toRadians(100))
                .build());
        DriveTrajectorySequenceCommand moveInToBoard = new DriveTrajectorySequenceCommand(driveTrainSubsystem, drive.trajectorySequenceBuilder(moveToBoard.getTrajectory().end())
                .setVelConstraint(new MecanumVelocityConstraint(slowMove,DriveConstants.TRACK_WIDTH))
                .setAccelConstraint(new ProfileAccelerationConstraint(slowMove))
                .lineToConstantHeading(new Vector2d(49, 38.5))
                .build());
        SequentialCommandGroup auto;
        auto = new SequentialCommandGroup(
                new ChangeClawsDefaultPos(jointSubsystem,groundPos),
                new ParallelCommandGroup(moveToPixelPos, new ChangeClawsDefaultPos(jointSubsystem,groundPos)),
                new ControlRightClawCommand(clawSubsystem,true),
                new WaitCommand(()->getRuntime(),0.35),
                new ChangeClawsDefaultPos(jointSubsystem,deg90Pos),
                new WaitCommand(()->getRuntime(),0.35),
                new ControlRightClawCommand(clawSubsystem,false),
                moveToWaitPos,
                moveToBoard,
                new WaitCommand(()->getRuntime(),2),//the waiting time
                new SetArmsTarget(armSubsystem,4300),
                new WaitCommand(()->getRuntime(),2),//the waiting time
                moveInToBoard,
                new ChangeClawsDefaultPos(jointSubsystem,0.8),
                new WaitCommand(()->getRuntime(),0.35),
                new ControlLeftClawCommand(clawSubsystem, true),
                new WaitCommand(()->getRuntime(),0.35),
                new ParallelCommandGroup(new SetArmsTarget(armSubsystem, 0),new SequentialCommandGroup(new WaitCommand(()->getRuntime(),0.2), new ControlLeftClawCommand(clawSubsystem, false)))
        );
        return auto;
    }

    private boolean updates(){
        drive.update();
        telemetry.addData("pos",drive.getPoseEstimate());
        telemetry.addData("arm pos",armSubsystem.motor.getCurrentPosition());
        telemetry.addData("axon pos",jointSubsystem.rotationServo.getPosition());
        telemetry.update();
        return isStopRequested();
    }
    @Override
    public void run() {
        super.run();
        updates();
    }
}


