package org.firstinspires.ftc.teamcode.firstCompBot.opModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.firstCompBot.Constants;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.CartridgeCollectCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.CartridgeDropCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.DeployHookCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.DriveHorizontalCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.EjectionCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.InTakeCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.LaunchAirplaneCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.MoveLiftCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.PullDownCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.PullUpCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.ReturnHookCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.RotateToCollectionCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.RotateToDropCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.VeloMoveLiftCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.AirplaneSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CameraSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CartridgeSubsystam;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.HookSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.InTakeSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.MyOdometrySubsystem;

@TeleOp
public class RobotOpMode extends CommandOpMode {
    //controllers
    GamepadEx driver;
    GamepadEx controller;
    //subsystems
    LiftSubsystem liftSubsystem;
    DriveTrainSubsystem driveTrainSubsystem;
    AirplaneSubsystem airplaneSubsystem;
    CartridgeSubsystam cartridgeSubsystam;
    HookSubsystem hookSubsystem;
    InTakeSubsystem inTakeSubsystem;
    CameraSubsystem cameraSubsystem;
    MyOdometrySubsystem odometrySubsystem;
    //commands
    DeployHookCommand deployHookCommand;
    DriveCommand driveCommand;
    EjectionCommand ejectionCommand;
    InTakeCommand inTakeCommand;
    LaunchAirplaneCommand launchAirplaneCommand;
    MoveLiftCommand moveLiftCommand;
    PullDownCommand pullDownCommand;
    PullUpCommand pullUpCommand;
    ReturnHookCommand returnHookCommand;
    DriveHorizontalCommand driveHorizontalCommand;
    CartridgeCollectCommand cartridgeCollectCommand;
    CartridgeDropCommand cartridgeDropCommand;
    RotateToCollectionCommand rotateToCollectionCommand;
    RotateToDropCommand rotateToDropCommand;
    VeloMoveLiftCommand veloMoveLiftCommand;
    double time;
    Constants.GameConstants.gamePeriod period;
    Pose2d pose;


    @Override
    public void initialize() {
        driver = new GamepadEx(gamepad1);
        controller = new GamepadEx(gamepad2);
        pose = new Pose2d();
        initSubsystems();
        initCommands();
        CommandScheduler.getInstance().onCommandExecute(this::telemetry);
        period = Constants.GameConstants.gamePeriod.teleOp;
    }
    private void initSubsystems(){
        liftSubsystem = new LiftSubsystem(hardwareMap);
        driveTrainSubsystem = new DriveTrainSubsystem(hardwareMap);
        airplaneSubsystem = new AirplaneSubsystem(hardwareMap);
        cartridgeSubsystam = new  CartridgeSubsystam(hardwareMap);
        hookSubsystem = new HookSubsystem(hardwareMap);
        inTakeSubsystem = new InTakeSubsystem(hardwareMap);
        odometrySubsystem = new MyOdometrySubsystem(hardwareMap);
        cameraSubsystem = new CameraSubsystem(hardwareMap);

    }
    private void initCommands(){
        constructCommands();
        assignCommands();
    }
    private void constructCommands(){
        deployHookCommand = new DeployHookCommand(hookSubsystem);
        driveCommand= new DriveCommand(driveTrainSubsystem,() -> driver.getLeftX(),() -> driver.getLeftY(),() -> driver.getRightX(),() -> false);
        ejectionCommand = new EjectionCommand(inTakeSubsystem);
        inTakeCommand = new InTakeCommand(inTakeSubsystem);
        launchAirplaneCommand = new LaunchAirplaneCommand(airplaneSubsystem);
        moveLiftCommand = new MoveLiftCommand(liftSubsystem,() -> controller.getLeftY());
        pullDownCommand = new PullDownCommand(hookSubsystem);
        pullUpCommand = new PullUpCommand(hookSubsystem);
        returnHookCommand = new ReturnHookCommand(hookSubsystem);
        driveHorizontalCommand = new DriveHorizontalCommand(driveTrainSubsystem,()-> driver.getLeftX(),() -> pose.getHeading());
        cartridgeCollectCommand = new CartridgeCollectCommand(cartridgeSubsystam);
        cartridgeDropCommand = new CartridgeDropCommand(cartridgeSubsystam);
        rotateToCollectionCommand= new RotateToCollectionCommand(cartridgeSubsystam);
        rotateToDropCommand = new RotateToDropCommand(cartridgeSubsystam);
        veloMoveLiftCommand = new VeloMoveLiftCommand(liftSubsystem,() -> controller.getRightY());

    }
    private void assignCommands(){
        //default commands
        liftSubsystem.setDefaultCommand(moveLiftCommand);
        //new GamepadButton(controller,GamepadKeys.Button.DPAD_RIGHT).whenHeld(veloMoveLiftCommand);
        driveTrainSubsystem.setDefaultCommand(driveCommand);
        //interactive commands
        new GamepadButton(controller, GamepadKeys.Button.LEFT_BUMPER).whenHeld(inTakeCommand);
        new GamepadButton(controller, GamepadKeys.Button.RIGHT_BUMPER).whenHeld(ejectionCommand);
        new GamepadButton(controller, GamepadKeys.Button.DPAD_UP).whenHeld(cartridgeCollectCommand);
        new GamepadButton(controller, GamepadKeys.Button.DPAD_DOWN).whenHeld(cartridgeDropCommand);

        new GamepadButton(controller,GamepadKeys.Button.Y).cancelWhenActive(cartridgeCollectCommand).cancelWhenActive(cartridgeDropCommand).cancelWhenActive(ejectionCommand).cancelWhenActive(inTakeCommand);
        new GamepadButton(controller, GamepadKeys.Button.A).toggleWhenPressed(rotateToCollectionCommand,rotateToDropCommand);
    }
    private void assignEndGameCommands(){
        new GamepadButton(controller, GamepadKeys.Button.X).whenActive(launchAirplaneCommand);
        new GamepadButton(controller, GamepadKeys.Button.DPAD_UP).whenActive(pullUpCommand);
        new GamepadButton(controller, GamepadKeys.Button.DPAD_DOWN).whenActive(pullDownCommand);
    }


    private void telemetry(Command command) {
        //telemetry.addData("lift height: ", liftSubsystem.getHeight());
        telemetry.addData("lift height1: ", liftSubsystem.getHeight1());
        telemetry.addData("lift height2: ", liftSubsystem.getHeight2());
        telemetry.addData("off set: ",String.valueOf(liftSubsystem.getEncoderOffset()));
        telemetry.addData("buttom ",String.valueOf(liftSubsystem.isBottom()));
        telemetry.addData("top ",String.valueOf(liftSubsystem.isTop()));
        telemetry.addData("time ",time);
        telemetry.addData("pos","("+pose.getX()+", "+pose.getY()+")");
        telemetry.addData("heading",pose.getHeading());
        telemetry.update();
    }
    @Override
    public void run() {
        super.run();
        time= getRuntime();
        pose = odometrySubsystem.getPose();
//        if(period== Constants.GameConstants.gamePeriod.teleOp&&time>=endGameTime){
//            period = Constants.GameConstants.gamePeriod.endGame;
//            assignEndGameCommands();
//        }

//        ConditionalCommand pressBeacon = new ConditionalCommand(
//                new InstantCommand(driveHorizontalCommand::drive, driveTrainSubsystem),
//                new InstantCommand(),
//                () -> (pose.getY()>50 &&pose.getX()>20)
//        );
//        pressBeacon.schedule();

    }


}
