//package org.firstinspires.ftc.teamcode.firstCompBot.opModes;
//
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.geometry.Vector2d;
//import com.arcrobotics.ftclib.command.CommandOpMode;
//import com.arcrobotics.ftclib.command.SequentialCommandGroup;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//
//import org.firstinspires.ftc.teamcode.drive.DriveConstants;
//import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
//import org.firstinspires.ftc.teamcode.firstCompBot.Constants.GameConstants;
//import org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands.CartridgeRotatelCommand;
//import org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands.DriveTrajectorySequenceCommand;
//import org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands.DropPixelCommand;
//import org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands.MoveLiftToHeightCommand;
//import org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands.PutPixelCommand;
//import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CartridgeSubsystam;
//import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.DriveTrainSubsystem;
//import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.InTakeSubsystem;
//import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.LiftSubsystem;
//import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
//
//@Autonomous
//public class AutoTest extends CommandOpMode {
//    DriveTrainSubsystem driveTrainSubsystem;
//    SampleMecanumDrive drive;
//    LiftSubsystem liftSubsystem;
//    InTakeSubsystem inTakeSubsystem;
//    PutPixelCommand putPixelCommand;
//    MoveLiftToHeightCommand moveLiftToHeightCommand;
//    CartridgeSubsystam cartridge;
//    SequentialCommandGroup auto;
//    GameConstants.GameType gameType;
//    int step=0;
//    @Override
//    public void initialize() {
//        gameType= GameConstants.GameType.RIGHT;
//        initSubsytems();
//        drive.setPoseEstimate(new Pose2d(11.61, -69.36, Math.toRadians(90.00)));
//        initAuto();
//        schedule(auto);
//    }
//    private void initSubsytems(){
//        driveTrainSubsystem = new DriveTrainSubsystem(hardwareMap);
//        drive = driveTrainSubsystem.getSampleDrive();
//        liftSubsystem = new LiftSubsystem(hardwareMap);
//        inTakeSubsystem = new InTakeSubsystem(hardwareMap);
//        cartridge = new CartridgeSubsystam(hardwareMap);
//    }
//    private void initAuto(){
//        DriveTrajectorySequenceCommand moveToPixle = new DriveTrajectorySequenceCommand(driveTrainSubsystem,pixelPlace(drive.getPoseEstimate()));
//        //DriveRotateCommand rotateCommand = new DriveRotateCommand(driveTrainSubsystem,()->getRotation());
//        PutPixelCommand putPixel = new PutPixelCommand(inTakeSubsystem,telemetry);
//        DriveTrajectorySequenceCommand moveToBoard = new DriveTrajectorySequenceCommand(driveTrainSubsystem,moveToBoard(moveToPixle.getTrajectory().end(),new Pose2d(53, getPoseOnBoard())));
//        MoveLiftToHeightCommand raiseLiftToBoard = new MoveLiftToHeightCommand(liftSubsystem,telemetry,10500);
//        DriveTrajectorySequenceCommand wait2 = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(moveToBoard.getTrajectory().end()).waitSeconds(2).build());
//        DropPixelCommand dropPixel = new DropPixelCommand(cartridge,telemetry);
//        MoveLiftToHeightCommand lowerLift = new MoveLiftToHeightCommand(liftSubsystem,telemetry,0);
//        DriveTrajectorySequenceCommand park =  new DriveTrajectorySequenceCommand(driveTrainSubsystem,park(moveToBoard.getTrajectory().end()));
//        CartridgeRotatelCommand rotateCartridge = new CartridgeRotatelCommand(cartridge,telemetry);
//        auto=new SequentialCommandGroup(
//                moveToPixle,
//                putPixel,
//                moveToBoard,
//                raiseLiftToBoard,
//                rotateCartridge,
//                dropPixel,
//                lowerLift,
//                park
//        );
//    }
//
//
//
//
//
//    public TrajectorySequence park(Pose2d start){
//        TrajectorySequence myTrajectory = drive.trajectorySequenceBuilder(start)
//                .lineTo(new Vector2d(38,-45))
//                .lineTo(new Vector2d(38,-20))
//                .lineTo(new Vector2d(57,-20))
//                .waitSeconds(2)
//
//                .lineTo(new Vector2d(30,-20))                                       //place
//                .lineTo(new Vector2d(15,-20))
//                .waitSeconds(0.2)
//                .turn(Math.toRadians(-90))
//                .lineTo(new Vector2d(12, -74),
//                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL/2,DriveConstants.MAX_ANG_VEL/2,DriveConstants.TRACK_WIDTH),
//                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL/2))
//                .build();
//        return myTrajectory;
//    }
////    public TrajectorySequence moveToBoard(){
////        return drive.trajectorySequenceBuilder(new Pose2d(11.61, -69.36, Math.toRadians(90.00)))
////                .lineTo(new Vector2d(11.19, -50))                       //zihoy
////                .waitSeconds(0.5)
////                .lineTo(new Vector2d(11.19, -65))
////
////                .lineToSplineHeading(new Pose2d(30, -65,Math.toRadians(180)))                           //pixel
////                .splineToConstantHeading(new Vector2d(40, -45), Math.toRadians(0),
////                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL/2, DriveConstants.MAX_ANG_VEL/2,DriveConstants.TRACK_WIDTH),
////                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL/2))
////                .waitSeconds(0.1)
////                .build();
////    }
//    public TrajectorySequence pixelPlace(Pose2d start){
//        return drive.trajectorySequenceBuilder(start)
//                .lineTo(new Vector2d(11.19, -43))
//                .turn(getRotation())
//                .build();
//    }
//    public TrajectorySequence moveToBoard(Pose2d start,Pose2d poseOnBoard){
//        return drive.trajectorySequenceBuilder(start)
//                .lineTo(new Vector2d(11.19, -65))
//
//                .lineToSplineHeading(new Pose2d(30, -65,Math.toRadians(180)))                           //pixel
//                .splineToConstantHeading(new Vector2d(40, -37), Math.toRadians(0),
//                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL/2, DriveConstants.MAX_ANG_VEL/2,DriveConstants.TRACK_WIDTH),
//                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL/2))
//                .lineTo(new Vector2d(53, -37),
//                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL/2, DriveConstants.MAX_ANG_VEL/2,DriveConstants.TRACK_WIDTH),
//                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL/2))
//                .build();
//    }
//    private double getRotation() {
//        switch (gameType) {
//            case LEFT:
//                return Math.toRadians(90);
//            case CENTER:
//                return Math.toRadians(0);
//            case RIGHT:
//                return Math.toRadians(-90);
//        }
//        return 0;
//    }
//        private double getPoseOnBoard(){
//            switch (gameType){
//                case LEFT:
//                    return 38.5;
//                case CENTER:
//                    return Math.toRadians(33);
//                case RIGHT:
//                    return Math.toRadians(27.5);
//            }
//            return 0;
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


package org.firstinspires.ftc.teamcode.firstCompBot.opModes;

        import com.acmerobotics.dashboard.config.Config;
        import com.acmerobotics.roadrunner.geometry.Pose2d;
        import com.acmerobotics.roadrunner.geometry.Vector2d;
        import com.arcrobotics.ftclib.command.CommandOpMode;
        import com.arcrobotics.ftclib.command.SequentialCommandGroup;
        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

        import org.firstinspires.ftc.robotcore.external.Telemetry;
        import org.firstinspires.ftc.teamcode.drive.DriveConstants;
        import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
        import org.firstinspires.ftc.teamcode.firstCompBot.Constants.GameConstants;
        import org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands.CartridgeRotatelCommand;
        import org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands.DriveTrajectorySequenceCommand;
        import org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands.DropPixelCommand;
        import org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands.MoveLiftToHeightCommand;
        import org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands.PutPixelCommand;
        import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CartridgeSubsystam;
        import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.DriveTrainSubsystem;
        import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.InTakeSubsystem;
        import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.LiftSubsystem;
        import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.VisionSubsystem;
        import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
@Config
public class AutoTest extends CommandOpMode {
    static DriveTrainSubsystem driveTrainSubsystem;
    static SampleMecanumDrive drive;
    static LiftSubsystem liftSubsystem;
    static InTakeSubsystem inTakeSubsystem;
    public boolean isVisoned = false;
    VisionSubsystem visionSubsystem;
    PutPixelCommand putPixelCommand;

    MoveLiftToHeightCommand moveLiftToHeightCommand;
     CartridgeSubsystam cartridge;
     SequentialCommandGroup auto;
    public GameConstants.GameType gameType;
    int step=0;
    @Override
    public void initialize() {
        initSubsytems();
        telemetry.addData("gameType",gameType);
        telemetry.update();
        drive.setPoseEstimate(new Pose2d(11.61, -69.36, Math.toRadians(90.00)));
    }
    private void initSubsytems(){
        driveTrainSubsystem = new DriveTrainSubsystem(hardwareMap);
        drive = driveTrainSubsystem.getSampleDrive();
        liftSubsystem = new LiftSubsystem(hardwareMap);
        inTakeSubsystem = new InTakeSubsystem(hardwareMap);
        cartridge = new CartridgeSubsystam(hardwareMap);
        visionSubsystem = new VisionSubsystem(hardwareMap,telemetry,this);
    }
    public  void initAuto(Telemetry telemetry, GameConstants.GameType gameType){
        DriveTrajectorySequenceCommand moveToPixle = new DriveTrajectorySequenceCommand(driveTrainSubsystem,pixelPlace(drive.getPoseEstimate()));
        //DriveRotateCommand rotateCommand = new DriveRotateCommand(driveTrainSubsystem,()->getRotation());
        PutPixelCommand putPixel = new PutPixelCommand(inTakeSubsystem,telemetry,gameType);
        DriveTrajectorySequenceCommand moveToBoard = new DriveTrajectorySequenceCommand(driveTrainSubsystem,moveToBoard(moveToPixle.getTrajectory().end(),getPoseOnBoard()));
        MoveLiftToHeightCommand raiseLiftToBoard = new MoveLiftToHeightCommand(liftSubsystem,telemetry,10500);
        DriveTrajectorySequenceCommand wait2 = new DriveTrajectorySequenceCommand(driveTrainSubsystem,drive.trajectorySequenceBuilder(moveToBoard.getTrajectory().end()).waitSeconds(2).build());
        DropPixelCommand dropPixel = new DropPixelCommand(cartridge,telemetry);
        MoveLiftToHeightCommand lowerLift = new MoveLiftToHeightCommand(liftSubsystem,telemetry,0);
        DriveTrajectorySequenceCommand park =  new DriveTrajectorySequenceCommand(driveTrainSubsystem,park(moveToBoard.getTrajectory().end()));
        CartridgeRotatelCommand rotateCartridge = new CartridgeRotatelCommand(cartridge,telemetry);
        auto=new SequentialCommandGroup(
                moveToPixle,
                putPixel,
                moveToBoard,
                raiseLiftToBoard,
                rotateCartridge,
                dropPixel,
                lowerLift,
                park
        );
        isVisoned=true;
        schedule(auto);

    }





    public  TrajectorySequence park(Pose2d start){
        TrajectorySequence myTrajectory = drive.trajectorySequenceBuilder(start)
                .lineTo(new Vector2d(38,-45))
                .lineTo(new Vector2d(38,-20))
                .lineTo(new Vector2d(57,-20))
                .waitSeconds(2)

//                .lineTo(new Vector2d(30,-20))                                       //place
//                .lineTo(new Vector2d(15,-20))
//                .waitSeconds(0.2)
//                .turn(Math.toRadians(-90))
//                .lineTo(new Vector2d(12, -74),
//                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL/2,DriveConstants.MAX_ANG_VEL/2,DriveConstants.TRACK_WIDTH),
//                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL/2))
                .build();
        return myTrajectory;
    }
    //    public TrajectorySequence moveToBoard(){
//        return drive.trajectorySequenceBuilder(new Pose2d(11.61, -69.36, Math.toRadians(90.00)))
//                .lineTo(new Vector2d(11.19, -50))                       //zihoy
//                .waitSeconds(0.5)
//                .lineTo(new Vector2d(11.19, -65))
//
//                .lineToSplineHeading(new Pose2d(30, -65,Math.toRadians(180)))                           //pixel
//                .splineToConstantHeading(new Vector2d(40, -45), Math.toRadians(0),
//                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL/2, DriveConstants.MAX_ANG_VEL/2,DriveConstants.TRACK_WIDTH),
//                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL/2))
//                .waitSeconds(0.1)
//                .build();
//    }

    public TrajectorySequence pixelPlace(Pose2d start){
        return drive.trajectorySequenceBuilder(start)
                .lineTo(new Vector2d(11.19, -43))
                .turn(getRotation())
                .build();
    }
    public TrajectorySequence moveToBoard(Pose2d start, double y){
        return drive.trajectorySequenceBuilder(start)
                .lineTo(new Vector2d(11.19, -65))
                .waitSeconds(1)
                .lineTo(new Vector2d(30, -65))
                .turn(Math.toRadians(90) - getRotation())
                .waitSeconds(1)
                .splineToConstantHeading(new Vector2d(40,y), Math.toRadians(0),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL/2, DriveConstants.MAX_ANG_VEL/2,DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL/2))
                .lineTo(new Vector2d(53,y),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL/2, DriveConstants.MAX_ANG_VEL/2,DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL/2))
                .build();
    }
    private double getRotation() {
        switch (gameType) {
            case LEFT:
                return Math.toRadians(90);
            case CENTER:
                return Math.toRadians(0);
            case RIGHT:
                return Math.toRadians(-90);
        }
        return 0;
    }
    private double getPoseOnBoard(){
        switch (gameType){
            case LEFT:
                return -37;
            case CENTER:
                return -46;
            case RIGHT:
                return -50.5;
        }
        return 0;
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

