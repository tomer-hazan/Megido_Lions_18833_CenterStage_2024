package org.firstinspires.ftc.teamcode.secondCompBot.opModes;

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

import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.AirplaneSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.HookSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.LEDSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.SlideSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.MyOdometrySubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.clawSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlRightClawCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.CartridgeDropCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ChangeColorsCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ChangeSpeedCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.DeployHookCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.DriveBackAndForceCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.DriveHorizontalCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.EjectionCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ForceCloseCartridgeCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.InTakeCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.LaunchAirplaneCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.MecanumMovmentCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.MoveLiftCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.MoveLiftToIntakeCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.MoveLiftWithLimitsCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ReturnHookCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.RotateToCollectionCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.RotateToDropCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.SwitchColorsCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.VeloMoveLiftCommand;

@TeleOp
public class RobotOpMode extends CommandOpMode {
    //controllers
    static GamepadEx driver;
    public static Constants.GameConstants.GameType gameType;
    GamepadEx controller;
    //subsystems
    SlideSubsystem slideSubsystem;
    static DriveTrainSubsystem driveTrainSubsystem;
    AirplaneSubsystem airplaneSubsystem;
    clawSubsystem clawSubsystem;
    HookSubsystem hookSubsystem;
    InTakeSubsystem inTakeSubsystem;
    MyOdometrySubsystem odometrySubsystem;
    VisionSubsystem visionSubsystem;
    LEDSubsystem ledSubsystem;
    public static int speed;
    //commands
    DeployHookCommand deployHookCommand;
    static DriveCommand driveCommand;
    EjectionCommand ejectionCommand;
    InTakeCommand inTakeCommand;
    LaunchAirplaneCommand launchAirplaneCommand;
    MoveLiftCommand moveLiftCommand;
    MoveLiftCommand moveLiftSlowCommand;
//    PullDownCommand pullDownCommand;
//    PullUpCommand pullUpCommand;
    ReturnHookCommand returnHookCommand;
    DriveHorizontalCommand driveHorizontalCommandLeft;
    DriveHorizontalCommand driveHorizontalCommandRight;
    ControlRightClawCommand controlRightClawCommand;
    CartridgeDropCommand cartridgeDropCommand;
    RotateToCollectionCommand rotateToCollectionCommand;
    SwitchColorsCommand switchColorsCommand;
    RotateToDropCommand rotateToDropCommand;
    static MecanumMovmentCommand strafeLeft;
    static MecanumMovmentCommand strafeRight;
    MecanumMovmentCommand strafeLeftSlow;
    MecanumMovmentCommand strafeRightSlow;
    DriveBackAndForceCommand driveBackCommand;
    static DriveBackAndForceCommand driveForwardCommand;
//    PullRobotCommand pullRobotCommand;
    VeloMoveLiftCommand veloMoveLiftCommand;
    MoveLiftWithLimitsCommand moveLiftWithLimitsCommand;
    ChangeColorsCommand changeToGreenCommand;
    ChangeColorsCommand changeToNoneCommand;
    MoveLiftToIntakeCommand moveLiftToIntakeCommandSmall;
    MoveLiftToIntakeCommand moveLiftToIntakeCommandBig;
    ForceCloseCartridgeCommand forceCloseCartridgeCommand;
    ChangeSpeedCommand changeSpeedCommand;

    double time;
    Constants.GameConstants.gamePeriod period;
    Pose2d pose;


    @Override
    public void initialize() {
        speed = 1;
        driver = new GamepadEx(gamepad1);
        controller = new GamepadEx(gamepad2);
        pose = new Pose2d();
        initSubsystems();
        slideSubsystem.resetEncoders();
        driveTrainSubsystem.resetEncoders();
        initCommands();
        CommandScheduler.getInstance().onCommandExecute(this::telemetry);
        period = Constants.GameConstants.gamePeriod.teleOp;
    }
    private void initSubsystems(){
        slideSubsystem = new SlideSubsystem(hardwareMap);
        driveTrainSubsystem = new DriveTrainSubsystem(hardwareMap);
        airplaneSubsystem = new AirplaneSubsystem(hardwareMap);
        clawSubsystem = new clawSubsystem(hardwareMap);
        hookSubsystem = new HookSubsystem(hardwareMap);
        inTakeSubsystem = new InTakeSubsystem(hardwareMap);
        odometrySubsystem = new MyOdometrySubsystem(hardwareMap);
        ledSubsystem = new LEDSubsystem(hardwareMap);
        //visionSubsystem = new VisionSubsystem(hardwareMap,telemetry);


    }
    private void initCommands(){
        constructCommands();
        assignCommands();
    }
    private void constructCommands(){
        deployHookCommand = new DeployHookCommand(hookSubsystem);
        driveCommand= new DriveCommand(driveTrainSubsystem,() -> driver.getLeftX(),() -> driver.getLeftY(),() -> driver.getRightX());
        ejectionCommand = new EjectionCommand(inTakeSubsystem,() -> controller.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER));
        inTakeCommand = new InTakeCommand(inTakeSubsystem,() -> controller.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER));
        launchAirplaneCommand = new LaunchAirplaneCommand(airplaneSubsystem);
        moveLiftCommand = new MoveLiftCommand(slideSubsystem,() -> controller.getRightY());
//        pullRobotCommand = new PullRobotCommand(hookSubsystem,()->-controller.getLeftY());
        returnHookCommand = new ReturnHookCommand(hookSubsystem);
        driveHorizontalCommandLeft = new DriveHorizontalCommand(driveTrainSubsystem,()-> driver.getLeftX(),() -> 1.0);
        driveHorizontalCommandRight = new DriveHorizontalCommand(driveTrainSubsystem,()-> driver.getLeftX(),() -> -1.0);
        controlRightClawCommand = new ControlRightClawCommand(clawSubsystem);
        cartridgeDropCommand = new CartridgeDropCommand(clawSubsystem);
        rotateToCollectionCommand= new RotateToCollectionCommand(clawSubsystem);
        rotateToDropCommand = new RotateToDropCommand(clawSubsystem);
        veloMoveLiftCommand = new VeloMoveLiftCommand(slideSubsystem,() -> controller.getRightY());
        moveLiftWithLimitsCommand = new MoveLiftWithLimitsCommand(slideSubsystem,() -> -controller.getRightY(),telemetry);
        switchColorsCommand = new SwitchColorsCommand(ledSubsystem);
        changeToGreenCommand = new ChangeColorsCommand(ledSubsystem,0);
        changeToNoneCommand = new ChangeColorsCommand(ledSubsystem,3);
        moveLiftToIntakeCommandSmall = new MoveLiftToIntakeCommand(slideSubsystem,telemetry,77,0.7);
        moveLiftToIntakeCommandBig = new MoveLiftToIntakeCommand(slideSubsystem,telemetry,200,1);
        forceCloseCartridgeCommand = new ForceCloseCartridgeCommand(clawSubsystem);
        strafeLeft = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(-1));
        strafeRight = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(1));
        strafeLeftSlow = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(-0.5));
        strafeRightSlow = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(0.5));
        moveLiftSlowCommand = new MoveLiftCommand(slideSubsystem,() -> -controller.getLeftY()*0.65);
        driveBackCommand = new DriveBackAndForceCommand(driveTrainSubsystem,()->-1.0);
        driveForwardCommand = new DriveBackAndForceCommand(driveTrainSubsystem,()->1.0);
        changeSpeedCommand = new ChangeSpeedCommand();

    }
    private void assignCommands(){
        //default commands
        //liftSubsystem.setDefaultCommand(moveLiftCommand);
        //new GamepadButton(controller,GamepadKeys.Button.DPAD_RIGHT).whenHeld(moveLiftCommand);
        schedule(rotateToCollectionCommand);
        new Trigger( () ->new TriggerReader(controller,GamepadKeys.Trigger.RIGHT_TRIGGER).isDown()).whenActive(inTakeCommand).whileActiveOnce(controlRightClawCommand);
        new Trigger( () ->new TriggerReader(controller,GamepadKeys.Trigger.LEFT_TRIGGER).isDown()).whenActive(ejectionCommand);
        slideSubsystem.setDefaultCommand(moveLiftCommand);//liftSubsystem.setDefaultCommand(moveLiftWithLimitsCommand);

//        new GamepadButton(controller, GamepadKeys.Button.X).toggleWhenPressed(moveLiftWithLimitsCommand,moveLiftCommand);
        //new GamepadButton(controller, GamepadKeys.Button.A).toggleWhenPressed(cartridgeIntakeMode);
//        hookSubsystem.setDefaultCommand(pullRobotCommand);
        new Trigger(()-> (getRuntime()>=90&& driver.getButton(GamepadKeys.Button.Y))).whileActiveOnce(launchAirplaneCommand);
        new GamepadButton(driver,GamepadKeys.Button.DPAD_UP).whileHeld(driveForwardCommand);
        new GamepadButton(driver,GamepadKeys.Button.DPAD_DOWN).whileHeld(driveBackCommand);
//        new GamepadButton(driver,GamepadKeys.Button.DPAD_UP).whenHeld(deployHookCommand);
//        new GamepadButton(driver,GamepadKeys.Button.DPAD_DOWN).whenHeld(returnHookCommand);
//        new GamepadButton(controller,GamepadKeys.Button.DPAD_LEFT).whenHeld(deployHookCommand);
//        new GamepadButton(controller,GamepadKeys.Button.DPAD_RIGHT).whenHeld(returnHookCommand);
//        new GamepadButton(driver,GamepadKeys.Button.DPAD_LEFT).whileActiveOnce(driveHorizontalCommandLeft);
//        new GamepadButton(driver,GamepadKeys.Button.DPAD_RIGHT).whileActiveOnce(driveHorizontalCommandRight);
        new GamepadButton(controller,GamepadKeys.Button.RIGHT_BUMPER).whenHeld(controlRightClawCommand);
        new GamepadButton(controller,GamepadKeys.Button.LEFT_BUMPER).whenHeld(cartridgeDropCommand);
        new GamepadButton(controller, GamepadKeys.Button.A).whenPressed(moveLiftToIntakeCommandSmall);
        new GamepadButton(controller, GamepadKeys.Button.Y).whenPressed(moveLiftToIntakeCommandBig);
        new GamepadButton(controller, GamepadKeys.Button.B).whenPressed(switchColorsCommand);
        new Trigger(() -> slideSubsystem.isBottom()).whileActiveOnce(changeToGreenCommand);
        new Trigger(() -> !slideSubsystem.isBottom()).whileActiveOnce(changeToNoneCommand);
        if(slideSubsystem.isBottom())schedule(changeToGreenCommand);
        else schedule(changeToNoneCommand);
        driveTrainSubsystem.setDefaultCommand(driveCommand);
        new Trigger(() -> slideSubsystem.getHeight()<7500).whileActiveOnce(forceCloseCartridgeCommand);
        new Trigger(() -> (controller.getButton(GamepadKeys.Button.DPAD_UP) && clawSubsystem.getCurrentCommand()!=forceCloseCartridgeCommand)).whileActiveOnce(rotateToDropCommand);
        new Trigger(() -> (controller.getButton(GamepadKeys.Button.DPAD_DOWN) && clawSubsystem.getCurrentCommand()!=forceCloseCartridgeCommand)).whileActiveOnce(rotateToCollectionCommand);
        new Trigger(() -> driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)>0.05).whileActiveContinuous(strafeRightSlow);
        new Trigger(() -> driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)>0.05).whileActiveContinuous(strafeLeftSlow);
        new GamepadButton(driver,GamepadKeys.Button.LEFT_BUMPER).whileHeld(strafeLeft);
        new GamepadButton(driver,GamepadKeys.Button.RIGHT_BUMPER).whileHeld(strafeRight);
        new Trigger(() -> Math.abs(controller.getLeftY())>0.05).whileActiveContinuous(moveLiftSlowCommand);
        new GamepadButton(driver, GamepadKeys.Button.X).whenPressed(changeSpeedCommand);

//        //interactive commands
//        new GamepadButton(controller, GamepadKeys.Button.LEFT_BUMPER).whenHeld(inTakeCommand);
//        new GamepadButton(controller, GamepadKeys.Button.RIGHT_BUMPER).whenHeld(ejectionCommand);
//        new GamepadButton(controller, GamepadKeys.Button.DPAD_UP).whenHeld(cartridgeCollectCommand);
//        new GamepadButton(controller, GamepadKeys.Button.DPAD_DOWN).whenHeld(cartridgeDropCommand);
//
//        new GamepadButton(controller,GamepadKeys.Button.Y).cancelWhenActive(cartridgeCollectCommand).cancelWhenActive(cartridgeDropCommand).cancelWhenActive(ejectionCommand).cancelWhenActive(inTakeCommand);
//        new GamepadButton(controller, GamepadKeys.Button.A).toggleWhenPressed(rotateToCollectionCommand,rotateToDropCommand);
    }


    private void telemetry(Command command) {
        //telemetry.addData("lift height: ", liftSubsystem.getHeight());
        telemetry.addData("lift height1: ", slideSubsystem.getHeight1());
        telemetry.addData("lift height2: ", slideSubsystem.getHeight2());
        telemetry.addData("time ",time);
        telemetry.addData("pos","("+pose.getX()+", "+pose.getY()+")");
        telemetry.addData("heading",pose.getHeading());
        telemetry.addData("lift 1 power", slideSubsystem.motor1.get());
        telemetry.addData("lift 2 power", slideSubsystem.motor2.get());
        telemetry.addData("is bottom", slideSubsystem.isBottom());
        telemetry.addData("color ",switchColorsCommand.getColor());
        telemetry.addData("speed",speed);
        telemetry.update();
    }
    @Override
    public void run() {
        super.run();
        time= getRuntime();
        pose = odometrySubsystem.getPose();
        odometrySubsystem.update();

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

//    public static void slow(){
//        switch (speed){
//            case 0:
//                strafeLeft = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(-0.5));
//                strafeRight = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(0.5));
//                driveCommand= new DriveCommand(driveTrainSubsystem,() -> driver.getLeftX()/2,() -> driver.getLeftY()/2,() -> driver.getRightX()/2);
//                driveForwardCommand = new DriveBackAndForceCommand(driveTrainSubsystem,()->0.5);
//                driveForwardCommand = new DriveBackAndForceCommand(driveTrainSubsystem,()->-0.5);
//                break;
//            case 1:
//                strafeLeft = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(-1));
//                strafeRight = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(1));
//                driveCommand= new DriveCommand(driveTrainSubsystem,() -> driver.getLeftX(),() -> driver.getLeftY(),() -> driver.getRightX());
//                driveForwardCommand = new DriveBackAndForceCommand(driveTrainSubsystem,()->0.5);
//                driveForwardCommand = new DriveBackAndForceCommand(driveTrainSubsystem,()->-0.5);
//                break;
//        }
//    }


}
