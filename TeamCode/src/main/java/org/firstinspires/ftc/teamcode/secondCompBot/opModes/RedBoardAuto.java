//package org.firstinspires.ftc.teamcode.secondCompBot.opModes;
//
//import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.geometry.Vector2d;
//import com.arcrobotics.ftclib.command.CommandOpMode;
//import com.arcrobotics.ftclib.command.SequentialCommandGroup;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//
//import org.firstinspires.ftc.teamcode.drive.DriveConstants;
//import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
//import org.firstinspires.ftc.teamcode.secondCompBot.Constants.GameConstants;
//import org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands.CartridgeRotatelCommand;
//import org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands.DriveTrajectorySequenceCommand;
//import org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands.DropPixelCommand;
//import org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands.MoveLiftToHeightCommand;
//import org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands.StopVisionCommand;
//import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ClawSubsystem;
//import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.DriveTrainSubsystem;
//import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.SlideSubsystem;
//import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.VisionSubsystem;
//import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
//
//@Autonomous
//@Config
//public class RedBoardAuto extends CommandOpMode {
//    SampleMecanumDrive drive;
//    DriveTrainSubsystem driveTrainSubsystem;
//    SlideSubsystem slideSubsystem;
//    InTakeSubsystem inTakeSubsystem;
//    VisionSubsystem visionSubsystem;
//    ClawSubsystem cartridge;
//    SequentialCommandGroup auto;
//    GameConstants.GameType gameType;
//    @Override
//    public void initialize() {
//        initSubsytems();
//        telemetry.addData("gameType",gameType);
//        telemetry.update();
//        drive.setPoseEstimate(new Pose2d(11.61, -69.36, Math.toRadians(90)));
//    }
//    private void initSubsytems(){
//        driveTrainSubsystem = new DriveTrainSubsystem(hardwareMap);
//        drive = driveTrainSubsystem.getSampleDrive();
//        slideSubsystem = new SlideSubsystem(hardwareMap);
//        inTakeSubsystem = new InTakeSubsystem(hardwareMap);
//        cartridge = new ClawSubsystem(hardwareMap);
//        visionSubsystem = new VisionSubsystem(hardwareMap,telemetry, GameConstants.StartingPosition.RED);
//    }
//    @Override
//    public void runOpMode() throws InterruptedException {
//        initialize();
//
//        waitForStart();
//        gameType = visionSubsystem.getGameType();
//        visionSubsystem.stop();
//        switch (gameType){
//            case LEFT:
//                autoLeft();
//                break;
//            case CENTER:
//                autoCenter();
//                break;
//            case RIGHT:
//                autoRight();
//                break;
//        }
//        schedule(auto);
//
//        // run the scheduler
//        while (!isStopRequested() && opModeIsActive()) {
//            run();
//        }
//        reset();
//    }
//    public void autoLeft(){
//        DriveTrajectorySequenceCommand moveToPixel = new DriveTrajectorySequenceCommand(driveTrainSubsystem,pixelPlace(drive.getPoseEstimate()));
//        DriveTrajectorySequenceCommand moveToPixelPlace = new DriveTrajectorySequenceCommand(driveTrainSubsystem,moveToPixelPlaceLeft(moveToPixel.getTrajectory().end()));
//        PutPixelCommand putPixel = new PutPixelCommand(inTakeSubsystem,telemetry,gameType);
//        DriveTrajectorySequenceCommand moveToBoard = new DriveTrajectorySequenceCommand(driveTrainSubsystem,moveToBoard(moveToPixelPlace.getTrajectory().end(),getPoseOnBoard()));
//        MoveLiftToHeightCommand raiseLiftToBoard = new MoveLiftToHeightCommand(slideSubsystem,telemetry,10500);
//        DriveTrajectorySequenceCommand wait2 = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(moveToBoard.getTrajectory().end()).waitSeconds(2).build());
//        DropPixelCommand dropPixel = new DropPixelCommand(cartridge,telemetry);
//        MoveLiftToHeightCommand lowerLift = new MoveLiftToHeightCommand(slideSubsystem,telemetry,0);
//        DriveTrajectorySequenceCommand park =  new DriveTrajectorySequenceCommand(driveTrainSubsystem,park(moveToBoard.getTrajectory().end()));
//        CartridgeRotatelCommand rotateCartridge = new CartridgeRotatelCommand(cartridge,telemetry);
//        StopVisionCommand stopVisionCommand = new StopVisionCommand(visionSubsystem);
//        auto=new SequentialCommandGroup(
//                stopVisionCommand,
//                moveToPixel,
//                putPixel,
//                moveToBoard,
//                raiseLiftToBoard,
//                rotateCartridge,
//                dropPixel
////                lowerLift,
////                park
//        );
//    }
//    public void autoCenter(){
//        DriveTrajectorySequenceCommand moveToPixel = new DriveTrajectorySequenceCommand(driveTrainSubsystem,pixelPlace(drive.getPoseEstimate()));
//        PutPixelCommand putPixel = new PutPixelCommand(inTakeSubsystem,telemetry,gameType);
//        DriveTrajectorySequenceCommand moveToBoard = new DriveTrajectorySequenceCommand(driveTrainSubsystem,moveToBoard(moveToPixel.getTrajectory().end(),getPoseOnBoard()));
//        MoveLiftToHeightCommand raiseLiftToBoard = new MoveLiftToHeightCommand(slideSubsystem,telemetry,10500);
//        DriveTrajectorySequenceCommand wait2 = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(moveToBoard.getTrajectory().end()).waitSeconds(2).build());
//        DropPixelCommand dropPixel = new DropPixelCommand(cartridge,telemetry);
//        MoveLiftToHeightCommand lowerLift = new MoveLiftToHeightCommand(slideSubsystem,telemetry,0);
//        DriveTrajectorySequenceCommand park =  new DriveTrajectorySequenceCommand(driveTrainSubsystem,park(moveToBoard.getTrajectory().end()));
//        CartridgeRotatelCommand rotateCartridge = new CartridgeRotatelCommand(cartridge,telemetry);
//        StopVisionCommand stopVisionCommand = new StopVisionCommand(visionSubsystem);
//        auto=new SequentialCommandGroup(
//                stopVisionCommand,
//                moveToPixel,
//                putPixel,
//                moveToBoard,
//                raiseLiftToBoard,
//                rotateCartridge,
//                dropPixel,
//                lowerLift,
//                park
//        );
//    }
//    public void autoRight(){
//        DriveTrajectorySequenceCommand moveToPixel = new DriveTrajectorySequenceCommand(driveTrainSubsystem,pixelPlace(drive.getPoseEstimate()));
//        PutPixelCommand putPixel = new PutPixelCommand(inTakeSubsystem,telemetry,gameType);
//        DriveTrajectorySequenceCommand moveToBoard = new DriveTrajectorySequenceCommand(driveTrainSubsystem,moveToBoard(moveToPixel.getTrajectory().end(),getPoseOnBoard()));
//        MoveLiftToHeightCommand raiseLiftToBoard = new MoveLiftToHeightCommand(slideSubsystem,telemetry,10500);
//
//
//
//        DriveTrajectorySequenceCommand wait2 = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(moveToBoard.getTrajectory().end()).waitSeconds(2).build());
//        DropPixelCommand dropPixel = new DropPixelCommand(cartridge,telemetry);
//        MoveLiftToHeightCommand lowerLift = new MoveLiftToHeightCommand(slideSubsystem,telemetry,0);
//        DriveTrajectorySequenceCommand park =  new DriveTrajectorySequenceCommand(driveTrainSubsystem,park(moveToBoard.getTrajectory().end()));
//        CartridgeRotatelCommand rotateCartridge = new CartridgeRotatelCommand(cartridge,telemetry);
//        StopVisionCommand stopVisionCommand = new StopVisionCommand(visionSubsystem);
//        auto=new SequentialCommandGroup(
//                stopVisionCommand,
//                moveToPixel,
//                putPixel,
//                moveToBoard,
//                raiseLiftToBoard,
//                rotateCartridge,
//                dropPixel,
//                lowerLift,
//                park
//        );
//    }
//    public TrajectorySequence moveToPixelPlaceLeft(Pose2d start){
//        return drive.trajectorySequenceBuilder(start).forward(3).strafeRight(5).build();
//    }
//    public TrajectorySequence moveToPixelPlaceCenter(Pose2d start){
//        return drive.trajectorySequenceBuilder(start).forward(5).back(2).build();
//    }
//    public TrajectorySequence moveToPixelPlaceRight(Pose2d start){
//        return drive.trajectorySequenceBuilder(start).strafeRight(10).strafeLeft(5).build();
//    }
//
//
//
//
//
//    public  TrajectorySequence park(Pose2d start){
//        TrajectorySequence myTrajectory = drive.trajectorySequenceBuilder(start)
//                .lineTo(new Vector2d(38,-45))
//                .lineTo(new Vector2d(38,-20))
//                .lineTo(new Vector2d(57,-20))
//                .build();
//        return myTrajectory;
//    }
//
//    public TrajectorySequence pixelPlace(Pose2d start){
//        return drive.trajectorySequenceBuilder(start)
//                .lineTo(new Vector2d(11.19, -40))
//                .lineTo(new Vector2d(11.19, -43))
//                .turn(getRotation())
//                .build();
//    }
//    public TrajectorySequence moveToBoard(Pose2d start, double y){
//        return drive.trajectorySequenceBuilder(start)
//                .lineTo(new Vector2d(11.19, -65))
//                .waitSeconds(1)
//                .lineTo(new Vector2d(30, -65))
//                .turn(Math.toRadians(90) - getRotation())
//                .waitSeconds(1)
//                .splineToConstantHeading(new Vector2d(40,y), Math.toRadians(0),
//                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL/2, DriveConstants.MAX_ANG_VEL/2,DriveConstants.TRACK_WIDTH),
//                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL/2))
//                .lineTo(new Vector2d(53,y),
//                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL/2, DriveConstants.MAX_ANG_VEL/2,DriveConstants.TRACK_WIDTH),
//                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL/2))
//                .build();
//    }
//    private double getRotation() {
//        switch (gameType) {
//            case LEFT:
//                return Math.toRadians(-90);
//            case CENTER:
//                return Math.toRadians(0);
//            case RIGHT:
//                return Math.toRadians(90);
//        }
//        return 0;
//    }
//    private double getPoseOnBoard(){
//        switch (gameType){
//            case LEFT:
//                return -37;
//            case CENTER:
//                return -46;
//            case RIGHT:
//                return -50.5;
//        }
//        return 0;
//    }
//
//    private boolean updates(){
//        drive.update();
//        telemetry.addData("pos",drive.getPoseEstimate());
//        telemetry.update();
//        return isStopRequested();
//    }
//    @Override
//    public void run() {
//        super.run();
//        updates();
//    }
//}
//
//
