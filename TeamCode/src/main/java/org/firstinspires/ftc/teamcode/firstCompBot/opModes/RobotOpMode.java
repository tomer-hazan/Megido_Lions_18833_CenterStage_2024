package org.firstinspires.ftc.teamcode.firstCompBot.opModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.firstCompBot.Constants;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.CartridgeCollectCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.CartridgeDropCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.ChangeColorsCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.DeployHookCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.DriveHorizontalCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.EjectionCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.ForceCloseCartridgeCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.InTakeCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.LaunchAirplaneCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.MoveLiftCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.MoveLiftToIntakeCommand2;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.MoveLiftWithLimitsCommand;
//import org.firstinspires.ftc.teamcode.firstCompBot.commands.PullDownCommand;
//import org.firstinspires.ftc.teamcode.firstCompBot.commands.PullRobotCommand;
//import org.firstinspires.ftc.teamcode.firstCompBot.commands.PullUpCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.ReturnHookCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.RotateToCollectionCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.RotateToDropCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.SwitchColorsCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.VeloMoveLiftCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.AirplaneSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CameraSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CartridgeSubsystam;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.HookSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.InTakeSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.LEDSubsystem;
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
    LEDSubsystem ledSubsystem;
    //commands
    DeployHookCommand deployHookCommand;
    DriveCommand driveCommand;
    EjectionCommand ejectionCommand;
    InTakeCommand inTakeCommand;
    LaunchAirplaneCommand launchAirplaneCommand;
    MoveLiftCommand moveLiftCommand;
//    PullDownCommand pullDownCommand;
//    PullUpCommand pullUpCommand;
    ReturnHookCommand returnHookCommand;
    DriveHorizontalCommand driveHorizontalCommand;
    CartridgeCollectCommand cartridgeCollectCommand;
    CartridgeDropCommand cartridgeDropCommand;
    RotateToCollectionCommand rotateToCollectionCommand;
    SwitchColorsCommand switchColorsCommand;
    RotateToDropCommand rotateToDropCommand;
//    PullRobotCommand pullRobotCommand;
    VeloMoveLiftCommand veloMoveLiftCommand;
    MoveLiftWithLimitsCommand moveLiftWithLimitsCommand;
    ChangeColorsCommand changeToGreenCommand;
    ChangeColorsCommand changeToNoneCommand;
    MoveLiftToIntakeCommand2 moveLiftToIntakeCommand;
    ForceCloseCartridgeCommand forceCloseCartridgeCommand;
    double time;
    Constants.GameConstants.gamePeriod period;
    Pose2d pose;


    @Override
    public void initialize() {
        driver = new GamepadEx(gamepad1);
        controller = new GamepadEx(gamepad2);
        pose = new Pose2d();
        initSubsystems();
        liftSubsystem.resetEncoders();
        driveTrainSubsystem.resetEncoders();
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
        ledSubsystem = new LEDSubsystem(hardwareMap);

    }
    private void initCommands(){
        constructCommands();
        assignCommands();
    }
    private void constructCommands(){
        deployHookCommand = new DeployHookCommand(hookSubsystem);
        driveCommand= new DriveCommand(driveTrainSubsystem,() -> driver.getLeftX(),() -> driver.getLeftY(),() -> driver.getRightX(),() -> false);
        ejectionCommand = new EjectionCommand(inTakeSubsystem,() -> controller.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER));
        inTakeCommand = new InTakeCommand(inTakeSubsystem,() -> controller.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER));
        launchAirplaneCommand = new LaunchAirplaneCommand(airplaneSubsystem);
        moveLiftCommand = new MoveLiftCommand(liftSubsystem,() -> controller.getRightY());
//        pullRobotCommand = new PullRobotCommand(hookSubsystem,()->-controller.getLeftY());
        returnHookCommand = new ReturnHookCommand(hookSubsystem);
        driveHorizontalCommand = new DriveHorizontalCommand(driveTrainSubsystem,()-> driver.getLeftX(),() -> pose.getHeading());
        cartridgeCollectCommand = new CartridgeCollectCommand(cartridgeSubsystam);
        cartridgeDropCommand = new CartridgeDropCommand(cartridgeSubsystam);
        rotateToCollectionCommand= new RotateToCollectionCommand(cartridgeSubsystam);
        rotateToDropCommand = new RotateToDropCommand(cartridgeSubsystam);
        veloMoveLiftCommand = new VeloMoveLiftCommand(liftSubsystem,() -> controller.getRightY());
        moveLiftWithLimitsCommand = new MoveLiftWithLimitsCommand(liftSubsystem,() -> -controller.getRightY(),telemetry);
        switchColorsCommand = new SwitchColorsCommand(ledSubsystem);
        changeToGreenCommand = new ChangeColorsCommand(ledSubsystem,0);
        changeToNoneCommand = new ChangeColorsCommand(ledSubsystem,3);
        moveLiftToIntakeCommand = new MoveLiftToIntakeCommand2(liftSubsystem,telemetry);
        forceCloseCartridgeCommand = new ForceCloseCartridgeCommand(cartridgeSubsystam);



    }
    private void assignCommands(){
        //default commands
        //liftSubsystem.setDefaultCommand(moveLiftCommand);
        //new GamepadButton(controller,GamepadKeys.Button.DPAD_RIGHT).whenHeld(moveLiftCommand);
        schedule(rotateToCollectionCommand);
        new GamepadButton(controller,GamepadKeys.Button.DPAD_UP).whenHeld(rotateToDropCommand);
        new GamepadButton(controller,GamepadKeys.Button.DPAD_DOWN).whenHeld(rotateToCollectionCommand);
        new Trigger( () ->new TriggerReader(controller,GamepadKeys.Trigger.RIGHT_TRIGGER).isDown()).whenActive(inTakeCommand);
        new Trigger( () ->new TriggerReader(controller,GamepadKeys.Trigger.LEFT_TRIGGER).isDown()).whenActive(ejectionCommand);
        liftSubsystem.setDefaultCommand(moveLiftCommand);//liftSubsystem.setDefaultCommand(moveLiftWithLimitsCommand);

        new GamepadButton(controller, GamepadKeys.Button.X).toggleWhenPressed(moveLiftWithLimitsCommand,moveLiftCommand);
        //new GamepadButton(controller, GamepadKeys.Button.A).toggleWhenPressed(cartridgeIntakeMode);
//        hookSubsystem.setDefaultCommand(pullRobotCommand);
        new GamepadButton(controller, GamepadKeys.Button.Y).whenActive(launchAirplaneCommand);//toDO need to only work in endgame
        new GamepadButton(driver,GamepadKeys.Button.DPAD_UP).whenHeld(deployHookCommand);
        new GamepadButton(driver,GamepadKeys.Button.DPAD_DOWN).whenHeld(returnHookCommand);
        new GamepadButton(controller,GamepadKeys.Button.DPAD_LEFT).whenHeld(deployHookCommand);
        new GamepadButton(controller,GamepadKeys.Button.DPAD_RIGHT).whenHeld(returnHookCommand);
        new GamepadButton(controller,GamepadKeys.Button.RIGHT_BUMPER).whenHeld(cartridgeDropCommand);
        new GamepadButton(controller,GamepadKeys.Button.LEFT_BUMPER).whenHeld(cartridgeCollectCommand);
        new GamepadButton(controller, GamepadKeys.Button.A).whenPressed(moveLiftToIntakeCommand,false);
        new GamepadButton(controller, GamepadKeys.Button.B).whenPressed(switchColorsCommand);
        new Trigger(() -> liftSubsystem.isBottom()).whileActiveOnce(changeToGreenCommand);
        new Trigger(() -> !liftSubsystem.isBottom()).whileActiveOnce(changeToNoneCommand);
        if(liftSubsystem.isBottom())schedule(changeToGreenCommand);
        else schedule(changeToNoneCommand);
        driveTrainSubsystem.setDefaultCommand(driveCommand);
        new Trigger(() -> liftSubsystem.getHeight()<7500).whileActiveOnce(forceCloseCartridgeCommand);
        new Trigger(() -> (controller.getButton(GamepadKeys.Button.DPAD_UP) &&cartridgeSubsystam.getCurrentCommand()!=forceCloseCartridgeCommand)).whenActive(rotateToDropCommand);
        new Trigger(() -> (controller.getButton(GamepadKeys.Button.DPAD_DOWN) &&cartridgeSubsystam.getCurrentCommand()!=forceCloseCartridgeCommand)).whenActive(rotateToCollectionCommand);


//        //interactive commands
//        new GamepadButton(controller, GamepadKeys.Button.LEFT_BUMPER).whenHeld(inTakeCommand);
//        new GamepadButton(controller, GamepadKeys.Button.RIGHT_BUMPER).whenHeld(ejectionCommand);
//        new GamepadButton(controller, GamepadKeys.Button.DPAD_UP).whenHeld(cartridgeCollectCommand);
//        new GamepadButton(controller, GamepadKeys.Button.DPAD_DOWN).whenHeld(cartridgeDropCommand);
//
//        new GamepadButton(controller,GamepadKeys.Button.Y).cancelWhenActive(cartridgeCollectCommand).cancelWhenActive(cartridgeDropCommand).cancelWhenActive(ejectionCommand).cancelWhenActive(inTakeCommand);
//        new GamepadButton(controller, GamepadKeys.Button.A).toggleWhenPressed(rotateToCollectionCommand,rotateToDropCommand);
    }
    private void assignEndGameCommands(){
        new GamepadButton(controller, GamepadKeys.Button.X).whenActive(launchAirplaneCommand);
//        new GamepadButton(controller, GamepadKeys.Button.DPAD_UP).whenActive(pullUpCommand);
//        new GamepadButton(controller, GamepadKeys.Button.DPAD_DOWN).whenActive(pullDownCommand);
    }


    private void telemetry(Command command) {
        //telemetry.addData("lift height: ", liftSubsystem.getHeight());
        telemetry.addData("lift height1: ", liftSubsystem.getHeight1());
        telemetry.addData("lift height2: ", liftSubsystem.getHeight2());
        telemetry.addData("time ",time);
        telemetry.addData("pos","("+pose.getX()+", "+pose.getY()+")");
        telemetry.addData("heading",pose.getHeading());
        telemetry.addData("lift 1 power",liftSubsystem.motor1.get());
        telemetry.addData("lift 2 power",liftSubsystem.motor2.get());
        telemetry.addData("is bottom",liftSubsystem.isBottom());
        telemetry.addData("color ",switchColorsCommand.getColor());
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
