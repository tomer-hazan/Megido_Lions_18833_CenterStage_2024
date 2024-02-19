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
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.AirplaneSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CameraSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CartridgeSubsystam;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.HookSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.InTakeSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.LEDSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.MyOdometrySubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.CartridgeCollectCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.CartridgeDropCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.ChangeColorsCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.ChangeSpeedCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.DeployHookCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.DriveBackAndForceCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.DriveHorizontalCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.EjectionCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.ForceCloseCartridgeCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.InTakeCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.LaunchAirplaneCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.MecanumMovmentCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.MoveLiftCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.MoveLiftToIntakeCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.MoveLiftWithLimitsCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.ReturnHookCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.RotateToCollectionCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.RotateToDropCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.SwitchColorsCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.teleOP.commands.VeloMoveLiftCommand;

@TeleOp
public class RobotOpMode extends CommandOpMode {
    //controllers
    static GamepadEx driver;
    public static Constants.GameConstants.GameType gameType;
    GamepadEx controller;
    //subsystems
    LiftSubsystem liftSubsystem;
    static DriveTrainSubsystem driveTrainSubsystem;
    AirplaneSubsystem airplaneSubsystem;
    CartridgeSubsystam cartridgeSubsystam;
    HookSubsystem hookSubsystem;
    InTakeSubsystem inTakeSubsystem;
    CameraSubsystem cameraSubsystem;
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
    CartridgeCollectCommand cartridgeCollectCommand;
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
        moveLiftCommand = new MoveLiftCommand(liftSubsystem,() -> controller.getRightY());
//        pullRobotCommand = new PullRobotCommand(hookSubsystem,()->-controller.getLeftY());
        returnHookCommand = new ReturnHookCommand(hookSubsystem);
        driveHorizontalCommandLeft = new DriveHorizontalCommand(driveTrainSubsystem,()-> driver.getLeftX(),() -> 1.0);
        driveHorizontalCommandRight = new DriveHorizontalCommand(driveTrainSubsystem,()-> driver.getLeftX(),() -> -1.0);
        cartridgeCollectCommand = new CartridgeCollectCommand(cartridgeSubsystam);
        cartridgeDropCommand = new CartridgeDropCommand(cartridgeSubsystam);
        rotateToCollectionCommand= new RotateToCollectionCommand(cartridgeSubsystam);
        rotateToDropCommand = new RotateToDropCommand(cartridgeSubsystam);
        veloMoveLiftCommand = new VeloMoveLiftCommand(liftSubsystem,() -> controller.getRightY());
        moveLiftWithLimitsCommand = new MoveLiftWithLimitsCommand(liftSubsystem,() -> -controller.getRightY(),telemetry);
        switchColorsCommand = new SwitchColorsCommand(ledSubsystem);
        changeToGreenCommand = new ChangeColorsCommand(ledSubsystem,0);
        changeToNoneCommand = new ChangeColorsCommand(ledSubsystem,3);
        moveLiftToIntakeCommandSmall = new MoveLiftToIntakeCommand(liftSubsystem,telemetry,77,0.7);
        moveLiftToIntakeCommandBig = new MoveLiftToIntakeCommand(liftSubsystem,telemetry,200,1);
        forceCloseCartridgeCommand = new ForceCloseCartridgeCommand(cartridgeSubsystam);
        strafeLeft = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(-1));
        strafeRight = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(1));
        strafeLeftSlow = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(-0.5));
        strafeRightSlow = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(0.5));
        moveLiftSlowCommand = new MoveLiftCommand(liftSubsystem,() -> -controller.getLeftY()*0.65);
        driveBackCommand = new DriveBackAndForceCommand(driveTrainSubsystem,()->-1.0);
        driveForwardCommand = new DriveBackAndForceCommand(driveTrainSubsystem,()->1.0);
        changeSpeedCommand = new ChangeSpeedCommand();

    }
    private void assignCommands(){
        //default commands
        //liftSubsystem.setDefaultCommand(moveLiftCommand);
        //new GamepadButton(controller,GamepadKeys.Button.DPAD_RIGHT).whenHeld(moveLiftCommand);
        schedule(rotateToCollectionCommand);
        new Trigger( () ->new TriggerReader(controller,GamepadKeys.Trigger.RIGHT_TRIGGER).isDown()).whenActive(inTakeCommand).whileActiveOnce(cartridgeCollectCommand);
        new Trigger( () ->new TriggerReader(controller,GamepadKeys.Trigger.LEFT_TRIGGER).isDown()).whenActive(ejectionCommand);
        liftSubsystem.setDefaultCommand(moveLiftCommand);//liftSubsystem.setDefaultCommand(moveLiftWithLimitsCommand);

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
        new GamepadButton(controller,GamepadKeys.Button.RIGHT_BUMPER).whenHeld(cartridgeCollectCommand);
        new GamepadButton(controller,GamepadKeys.Button.LEFT_BUMPER).whenHeld(cartridgeDropCommand);
        new GamepadButton(controller, GamepadKeys.Button.A).whenPressed(moveLiftToIntakeCommandSmall);
        new GamepadButton(controller, GamepadKeys.Button.Y).whenPressed(moveLiftToIntakeCommandBig);
        new GamepadButton(controller, GamepadKeys.Button.B).whenPressed(switchColorsCommand);
        new Trigger(() -> liftSubsystem.isBottom()).whileActiveOnce(changeToGreenCommand);
        new Trigger(() -> !liftSubsystem.isBottom()).whileActiveOnce(changeToNoneCommand);
        if(liftSubsystem.isBottom())schedule(changeToGreenCommand);
        else schedule(changeToNoneCommand);
        driveTrainSubsystem.setDefaultCommand(driveCommand);
        new Trigger(() -> liftSubsystem.getHeight()<7500).whileActiveOnce(forceCloseCartridgeCommand);
        new Trigger(() -> (controller.getButton(GamepadKeys.Button.DPAD_UP) &&cartridgeSubsystam.getCurrentCommand()!=forceCloseCartridgeCommand)).whileActiveOnce(rotateToDropCommand);
        new Trigger(() -> (controller.getButton(GamepadKeys.Button.DPAD_DOWN) &&cartridgeSubsystam.getCurrentCommand()!=forceCloseCartridgeCommand)).whileActiveOnce(rotateToCollectionCommand);
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
        telemetry.addData("lift height1: ", liftSubsystem.getHeight1());
        telemetry.addData("lift height2: ", liftSubsystem.getHeight2());
        telemetry.addData("time ",time);
        telemetry.addData("pos","("+pose.getX()+", "+pose.getY()+")");
        telemetry.addData("heading",pose.getHeading());
        telemetry.addData("lift 1 power",liftSubsystem.motor1.get());
        telemetry.addData("lift 2 power",liftSubsystem.motor2.get());
        telemetry.addData("is bottom",liftSubsystem.isBottom());
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
