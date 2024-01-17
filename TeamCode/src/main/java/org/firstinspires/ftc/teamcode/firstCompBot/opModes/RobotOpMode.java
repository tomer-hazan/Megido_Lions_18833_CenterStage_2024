package org.firstinspires.ftc.teamcode.firstCompBot.opModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.firstCompBot.Constants;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.DeployHookCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.EjectionCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.InTakeCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.LaunchAirplaneCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.MoveLiftCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.PullDownCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.PullUpCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.ReturnHookCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.AirplaneSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CameraSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CartridgeSubsystam;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.HookSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.InTakeSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.MyOdometrySubsystem;

import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.GameConstants.autoTime;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.GameConstants.endGameTime;

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
    double time;
    Constants.GameConstants.gamePeriod period;


    @Override
    public void initialize() {
        driver = new GamepadEx(gamepad1);
        controller = new GamepadEx(gamepad2);
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
        driveCommand= new DriveCommand(driveTrainSubsystem,() -> driver.getLeftX(),() -> driver.getLeftY(),() -> driver.getRightX());
        ejectionCommand = new EjectionCommand(inTakeSubsystem);
        inTakeCommand = new InTakeCommand(inTakeSubsystem);
        launchAirplaneCommand = new LaunchAirplaneCommand(airplaneSubsystem);
        moveLiftCommand = new MoveLiftCommand(liftSubsystem,() -> controller.getLeftY());
        pullDownCommand = new PullDownCommand(hookSubsystem);
        pullUpCommand = new PullUpCommand(hookSubsystem);
        returnHookCommand = new ReturnHookCommand(hookSubsystem);

    }
    private void assignCommands(){
        //default commands
        liftSubsystem.setDefaultCommand(moveLiftCommand);
        driveTrainSubsystem.setDefaultCommand(driveCommand);
        //interactive commands


        //end game
        if(time>autoTime){
            new GamepadButton(controller, GamepadKeys.Button.X).whenActive(launchAirplaneCommand);
            new GamepadButton(controller, GamepadKeys.Button.DPAD_UP).whenActive(pullUpCommand);
            new GamepadButton(controller, GamepadKeys.Button.DPAD_DOWN).whenActive(pullDownCommand);
        }
    }


    private void telemetry(Command command) {
        telemetry.addData("lift height: ",liftSubsystem.getHeight());
        telemetry.addData("off set: ",String.valueOf(liftSubsystem.getEncoderOffset()));
        telemetry.addData("buttom ",String.valueOf(liftSubsystem.isBottom()));
        telemetry.addData("top ",String.valueOf(liftSubsystem.isTop()));
        telemetry.addData("time ",time);
        telemetry.update();
    }
    @Override
    public void run() {
        super.run();
        time= getRuntime();
        if(period== Constants.GameConstants.gamePeriod.teleOp&&time>=endGameTime){
            period = Constants.GameConstants.gamePeriod.endGame;
        }

    }

}
