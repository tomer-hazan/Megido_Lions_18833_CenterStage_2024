//package org.firstinspires.ftc.teamcode.firstCompBot.opModes;
//
//import com.arcrobotics.ftclib.command.Command;
//import com.arcrobotics.ftclib.command.CommandOpMode;
//import com.arcrobotics.ftclib.command.button.GamepadButton;
//import com.arcrobotics.ftclib.gamepad.GamepadEx;
//import com.arcrobotics.ftclib.gamepad.GamepadKeys;
//import com.arcrobotics.ftclib.geometry.Pose2d;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.firstCompBot.Constants;
//import CartridgeCollectCommand;
//import CartridgeDropCommand;
//import DeployHookCommand;
//import DriveCommand;
//import DriveHorizontalCommand;
//import EjectionCommand;
//import InTakeCommand;
//import LaunchAirplaneCommand;
//import MoveLiftCommand;
//import org.firstinspires.ftc.teamcode.firstCompBot.commands.PullDownCommand;
//import org.firstinspires.ftc.teamcode.firstCompBot.commands.PullUpCommand;
//import ReturnHookCommand;
//import RotateToCollectionCommand;
//import RotateToDropCommand;
//import AirplaneSubsystem;
//import CameraSubsystem;
//import CartridgeSubsystam;
//import DriveTrainSubsystem;
//import HookSubsystem;
//import InTakeSubsystem;
//import LiftSubsystem;
//import MyOdometrySubsystem;
//
//@TeleOp
//public class RobotOpModeTest extends CommandOpMode {
//    //controllers
//    GamepadEx driver;
//    GamepadEx controller;
//    //subsystems
//    LiftSubsystem liftSubsystem;
//    DriveTrainSubsystem driveTrainSubsystem;
//    AirplaneSubsystem airplaneSubsystem;
//    CartridgeSubsystam cartridgeSubsystam;
//    HookSubsystem hookSubsystem;
//    InTakeSubsystem inTakeSubsystem;
//    CameraSubsystem cameraSubsystem;
//    MyOdometrySubsystem odometrySubsystem;
//    //commands
//    DeployHookCommand deployHookCommand;
//    DriveCommand driveCommand;
//    EjectionCommand ejectionCommand;
//    InTakeCommand inTakeCommand;
//    LaunchAirplaneCommand launchAirplaneCommand;
//    MoveLiftCommand moveLiftCommand;
//    PullDownCommand pullDownCommand;
//    PullUpCommand pullUpCommand;
//    ReturnHookCommand returnHookCommand;
//    DriveHorizontalCommand driveHorizontalCommand;
//    CartridgeCollectCommand cartridgeCollectCommand;
//    CartridgeDropCommand cartridgeDropCommand;
//    RotateToCollectionCommand rotateToCollectionCommand;
//    RotateToDropCommand rotateToDropCommand;
//    //test
//    Command cartridgeDropOneCommandNotReal;
//    //test
//    double time;
//    Constants.GameConstants.gamePeriod period;
//    Pose2d pose;
//
//
//    @Override
//    public void initialize() {
//        driver = new GamepadEx(gamepad1);
//        controller = new GamepadEx(gamepad2);
//        pose = new Pose2d();
//        initSubsystems();
//        initCommands();
//        //CommandScheduler.getInstance().onCommandExecute(this::telemetry);
//        period = Constants.GameConstants.gamePeriod.teleOp;
//    }
//    private void initSubsystems(){
////        liftSubsystem = new LiftSubsystem(hardwareMap);
////        driveTrainSubsystem = new DriveTrainSubsystem(hardwareMap);
////        airplaneSubsystem = new AirplaneSubsystem(hardwareMap);
//        cartridgeSubsystam = new  CartridgeSubsystam(hardwareMap);
////        hookSubsystem = new HookSubsystem(hardwareMap);
////        inTakeSubsystem = new InTakeSubsystem(hardwareMap);
////        odometrySubsystem = new MyOdometrySubsystem(hardwareMap);
////        cameraSubsystem = new CameraSubsystem(hardwareMap);
//
//    }
//    private void initCommands(){
////        constructCommands();
////        assignCommands();
//        //test
//        cartridgeDropCommand = new CartridgeDropCommand(cartridgeSubsystam);
//        cartridgeCollectCommand = new CartridgeCollectCommand(cartridgeSubsystam);
//        cartridgeDropOneCommandNotReal = (new CartridgeDropCommand(cartridgeSubsystam)).withTimeout(10000);
//        new GamepadButton(controller, GamepadKeys.Button.B).whenPressed(cartridgeDropOneCommandNotReal);
//        new GamepadButton(controller,GamepadKeys.Button.A).toggleWhenPressed(cartridgeDropCommand,cartridgeCollectCommand);
//        //test
//    }
//    private void constructCommands(){
//        deployHookCommand = new DeployHookCommand(hookSubsystem);
//        driveCommand= new DriveCommand(driveTrainSubsystem,() -> driver.getLeftX(),() -> driver.getLeftY(),() -> driver.getRightX(),() -> false);
//        ejectionCommand = new EjectionCommand(inTakeSubsystem,()->controller.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER));
//        inTakeCommand = new InTakeCommand(inTakeSubsystem,()->controller.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER));
//        launchAirplaneCommand = new LaunchAirplaneCommand(airplaneSubsystem);
//        moveLiftCommand = new MoveLiftCommand(liftSubsystem,() -> controller.getLeftY());
//        pullDownCommand = new PullDownCommand(hookSubsystem);
//        pullUpCommand = new PullUpCommand(hookSubsystem);
//        returnHookCommand = new ReturnHookCommand(hookSubsystem);
//        driveHorizontalCommand = new DriveHorizontalCommand(driveTrainSubsystem,()-> driver.getLeftX(),() -> pose.getHeading());
//        cartridgeCollectCommand = new CartridgeCollectCommand(cartridgeSubsystam);
//        cartridgeDropCommand = new CartridgeDropCommand(cartridgeSubsystam);
//        rotateToCollectionCommand= new RotateToCollectionCommand(cartridgeSubsystam);
//        rotateToDropCommand = new RotateToDropCommand(cartridgeSubsystam);
//
//    }
//    private void assignCommands(){
//        //default commands
//        liftSubsystem.setDefaultCommand(moveLiftCommand);
//        driveTrainSubsystem.setDefaultCommand(driveCommand);
//        //interactive commands
//        new GamepadButton(controller, GamepadKeys.Button.LEFT_BUMPER).whenHeld(inTakeCommand);
//        new GamepadButton(controller, GamepadKeys.Button.RIGHT_BUMPER).whenHeld(ejectionCommand);
//        new GamepadButton(controller, GamepadKeys.Button.A).whenHeld(cartridgeCollectCommand);
//        //new GamepadButton(controller, GamepadKeys.Button.B).whenHeld(cartridgeDropCommand);
//
//        new GamepadButton(controller,GamepadKeys.Button.Y).cancelWhenActive(cartridgeCollectCommand).cancelWhenActive(cartridgeDropCommand).cancelWhenActive(ejectionCommand).cancelWhenActive(inTakeCommand);
//        new GamepadButton(controller,GamepadKeys.Button.DPAD_UP).whenActive(rotateToDropCommand);
//        new GamepadButton(controller,GamepadKeys.Button.DPAD_DOWN).whenActive(rotateToCollectionCommand);
//    }
//    private void assignEndGameCommands(){
//        new GamepadButton(controller, GamepadKeys.Button.X).whenActive(launchAirplaneCommand);
//        new GamepadButton(controller, GamepadKeys.Button.DPAD_UP).whenActive(pullUpCommand);
//        new GamepadButton(controller, GamepadKeys.Button.DPAD_DOWN).whenActive(pullDownCommand);
//    }
//
//
//    private void telemetry(Command command) {
//        telemetry.addData("lift height: ", liftSubsystem.getHeight());
////        telemetry.addData("lift power1: ", moveLiftCommand.getRealPower1());
////        telemetry.addData("lift power2: ", moveLiftCommand.getRealPower2());
////        telemetry.addData("off set: ",String.valueOf(liftSubsystem.getEncoderOffset()));
////        telemetry.addData("buttom ",String.valueOf(liftSubsystem.isBottom()));
////        telemetry.addData("top ",String.valueOf(liftSubsystem.isTop()));
////        telemetry.addData("time ",time);
////        telemetry.addData("pos","("+pose.getX()+", "+pose.getY()+")");
////        telemetry.addData("heading",pose.getHeading());
//        telemetry.addData("target 1",liftSubsystem.motor1.motor.getTargetPosition());
//        telemetry.addData("target 2",liftSubsystem.motor2.motor.getTargetPosition());
//        telemetry.update();
//    }
//    @Override
//    public void run() {
//        super.run();
//        time= getRuntime();
//        //pose = odometrySubsystem.getPose();
////        if(period== Constants.GameConstants.gamePeriod.teleOp&&time>=endGameTime){
////            period = Constants.GameConstants.gamePeriod.endGame;
////            assignEndGameCommands();
////        }
//
////        ConditionalCommand pressBeacon = new ConditionalCommand(
////                new InstantCommand(driveHorizontalCommand::drive, driveTrainSubsystem),
////                new InstantCommand(),
////                () -> (pose.getY()>50 &&pose.getX()>20)
////        );
////        pressBeacon.schedule();
//
//    }
//
//
//}
