package org.firstinspires.ftc.teamcode.secondCompBot.opModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.autonomous.commands.SetArmsTarget;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.SetArmsTargetToNextPreSet;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.SetArmsTargetToPrevPreSet;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.AirplaneSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.HookSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.JointSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.LEDSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.MyOdometrySubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.SlideSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ChangeClawsDefaultPos;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ChangeColorsCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ChangeSpeedCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlClawsAngleCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlClawsCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlClawsPosCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlColorsCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlColorsCommand2;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlLeftClawCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlRightClawCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.DeployHookCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.DriveHorizontalCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.LaunchAirplaneCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.MecanumMovmentCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.MoveArmCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.MoveLiftCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.MoveLiftToPosCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.OpenOrCloseLeftClawCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.OpenOrCloseRightClawCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.PullRobotCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ReturnHookCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.RotateClawsToAngleCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.RotateClawsToPosCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.SwitchColorsCommand;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.JointConstants.groundPosOpenSlide;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.strafeRearWheels1;

@TeleOp
public class RobotOpMode extends CommandOpMode {
    ToggleButtonReader aToggle;
    //controllers
    static GamepadEx driver;
    public static Constants.GameConstants.GameType gameType;
    GamepadEx controller;
    //subsystems
    SlideSubsystem slideSubsystem;
    static DriveTrainSubsystem driveTrainSubsystem;
    public static double sensorFrame=0;
    public static double frameMod=5;
    AirplaneSubsystem airplaneSubsystem;
    ClawSubsystem clawSubsystem;
    HookSubsystem hookSubsystem;
    MyOdometrySubsystem odometrySubsystem;
    ArmSubsystem armSubsystem;
    LEDSubsystem ledSubsystem;
    JointSubsystem jointSubsystem;
    public static double speed;
    public static double turnSpeed;
    //commands
    DeployHookCommand deployHookCommand;
    static DriveCommand driveCommand;
    LaunchAirplaneCommand launchAirplaneCommand;
    MoveLiftCommand moveLiftCommand;
    MoveLiftCommand moveLiftSlowCommand;
    ReturnHookCommand returnHookCommand;
    DriveHorizontalCommand driveHorizontalCommandLeft;
    DriveHorizontalCommand driveHorizontalCommandRight;
    ControlRightClawCommand openRightClawCommand;
    ControlRightClawCommand closeRightClawCommand;
    ControlLeftClawCommand openLeftClawCommand;
    ControlLeftClawCommand closeLeftClawCommand;
    SwitchColorsCommand switchColorsCommand;
    static MecanumMovmentCommand strafeLeft;
    static MecanumMovmentCommand strafeRight;
    MecanumMovmentCommand strafeLeftSlow;
    MecanumMovmentCommand strafeRightSlow;
    PullRobotCommand pullRobotCommand;
    ChangeColorsCommand changeToGreenCommand;
    ChangeColorsCommand changeToNoneCommand;
    ChangeSpeedCommand speed6Command;
    ChangeSpeedCommand speed7Command;
    ChangeSpeedCommand speed1Command;
    ControlClawsAngleCommand controlClawsAngleCommand;
    ControlClawsAngleCommand controlClawsAngleCommand90;
    MoveArmCommand moveArmCommand;
    ControlColorsCommand controlColorsCommand;
    RotateClawsToAngleCommand rotateClawsToAngleCommand;
    ControlClawsCommand closeClawsCommand;
    ControlClawsCommand openClawsCommand;
    ChangeClawsDefaultPos goToGroundCommand;
    ChangeClawsDefaultPos goToGroundOpenSlideCommand;
    RotateClawsToPosCommand rotateClawsToPosCommand;
    ChangeClawsDefaultPos goTo90DegCommand;
    ControlClawsPosCommand controlClawsPosCommand;
    OpenOrCloseRightClawCommand openOrCloseRightClawCommand;
    OpenOrCloseLeftClawCommand openOrCloseLeftClawCommand;
    MoveLiftToPosCommand moveLiftToIntakeCommand;
    ControlColorsCommand2 changeOrangeForPixelsCommand;
//    ControlClawsPosOnceCommand goToGroundCommand;



    double time;
    double loopTime;
    Constants.GameConstants.gamePeriod period;
    Pose2d pose;


    @Override
    public void initialize() {
        speed = 1;
        turnSpeed = 0.75;
        driver = new GamepadEx(gamepad1);
        controller = new GamepadEx(gamepad2);
        pose = new Pose2d();
        initSubsystems();
        slideSubsystem.resetEncoders();
        driveTrainSubsystem.resetEncoders();
        initCommands();
        CommandScheduler.getInstance().onCommandExecute(this::telemetry);
        period = Constants.GameConstants.gamePeriod.teleOp;
        aToggle = new ToggleButtonReader(controller, GamepadKeys.Button.A);
        loopTime=0;
        time=getRuntime();
        hookSubsystem.lowerHook();

    }
    private void initSubsystems(){
        slideSubsystem = new SlideSubsystem(hardwareMap);
        driveTrainSubsystem = new DriveTrainSubsystem(hardwareMap);
        airplaneSubsystem = new AirplaneSubsystem(hardwareMap);
        hookSubsystem = new HookSubsystem(hardwareMap);
        odometrySubsystem = new MyOdometrySubsystem(hardwareMap);
        ledSubsystem = new LEDSubsystem(hardwareMap);
        armSubsystem = new ArmSubsystem(hardwareMap);
        jointSubsystem = new JointSubsystem(hardwareMap);
        clawSubsystem=new ClawSubsystem(hardwareMap,()->getRuntime(),()->armSubsystem.getAngle());

        //visionSubsystem = new VisionSubsystem(hardwareMap,telemetry);


    }
    private void initCommands(){
        constructCommands();
        assignCommands();
    }
    private void constructCommands(){
        controlClawsPosCommand = new ControlClawsPosCommand(jointSubsystem,()-> armSubsystem.getAngle());
        deployHookCommand = new DeployHookCommand(hookSubsystem);
        driveCommand= new DriveCommand(driveTrainSubsystem,() -> driver.getLeftX()*speed,() -> driver.getLeftY()*speed,() -> driver.getRightX()*turnSpeed);
        launchAirplaneCommand = new LaunchAirplaneCommand(airplaneSubsystem);
        moveLiftCommand = new MoveLiftCommand(slideSubsystem,() -> controller.getLeftY());
        returnHookCommand = new ReturnHookCommand(hookSubsystem);
        driveHorizontalCommandLeft = new DriveHorizontalCommand(driveTrainSubsystem,()-> driver.getLeftX()*speed,() -> 1.0);
        driveHorizontalCommandRight = new DriveHorizontalCommand(driveTrainSubsystem,()-> driver.getLeftX()*speed,() -> -1.0);
        openRightClawCommand = new ControlRightClawCommand(clawSubsystem,true);
        closeRightClawCommand = new ControlRightClawCommand(clawSubsystem,false);
        openLeftClawCommand = new ControlLeftClawCommand(clawSubsystem,true);
        closeLeftClawCommand = new ControlLeftClawCommand(clawSubsystem,false);
        switchColorsCommand = new SwitchColorsCommand(ledSubsystem);
        changeToGreenCommand = new ChangeColorsCommand(ledSubsystem,0);
        changeToNoneCommand = new ChangeColorsCommand(ledSubsystem,3);
        strafeLeft = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(-1)*speed,()->Double.valueOf(-1)*speed);
        strafeRight = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(1)*speed,()->Double.valueOf(1)*speed);
        strafeLeftSlow = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(-1)*0.6,()->Double.valueOf(-strafeRearWheels1)*0.6);
        strafeRightSlow = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(1)*0.6,()->Double.valueOf(strafeRearWheels1)*0.6);
        moveLiftSlowCommand = new MoveLiftCommand(slideSubsystem,() -> -controller.getLeftY()*0.65);
        speed6Command = new ChangeSpeedCommand(0.6,0.5);
        speed7Command = new ChangeSpeedCommand(0.7,0.6);
        speed1Command = new ChangeSpeedCommand(1,0.75);
        controlClawsAngleCommand = new ControlClawsAngleCommand(jointSubsystem,() ->armSubsystem.getAngle());
        goToGroundCommand = new ChangeClawsDefaultPos(jointSubsystem,Constants.JointConstants.groundPos);
        goToGroundOpenSlideCommand = new ChangeClawsDefaultPos(jointSubsystem,groundPosOpenSlide);
//        goToGroundCommand = new ControlClawsPosOnceCommand(clawSubsystem,Constants.ClawConstants.groundPos);
        moveArmCommand = new MoveArmCommand(armSubsystem,()->controller.getRightY());
        controlColorsCommand = new ControlColorsCommand(ledSubsystem,()->clawSubsystem.getLeftPixel(),()->clawSubsystem.getRightPixel());
        pullRobotCommand = new PullRobotCommand(hookSubsystem,()->0.0,()->driver.getButton(GamepadKeys.Button.DPAD_RIGHT),()->driver.getButton(GamepadKeys.Button.DPAD_LEFT));
        rotateClawsToAngleCommand = new RotateClawsToAngleCommand(jointSubsystem, ()->controller.getRightX()*360);
        closeClawsCommand = new ControlClawsCommand(clawSubsystem,false);
        openClawsCommand = new ControlClawsCommand(clawSubsystem,true);
        rotateClawsToPosCommand = new RotateClawsToPosCommand(jointSubsystem,()->controller.getLeftX());
        goTo90DegCommand = new ChangeClawsDefaultPos(jointSubsystem,Constants.JointConstants.deg90Pos);
        openOrCloseLeftClawCommand = new OpenOrCloseLeftClawCommand(clawSubsystem);
        openOrCloseRightClawCommand  =new OpenOrCloseRightClawCommand(clawSubsystem);
        moveLiftToIntakeCommand = new MoveLiftToPosCommand(slideSubsystem,540,()->getRuntime());
        changeOrangeForPixelsCommand = new ControlColorsCommand2(ledSubsystem,()->clawSubsystem.isDetectedPixelLeft(),()->clawSubsystem.isDetectedPixelRight());
    }
    private void assignCommands(){
        //default commands
        slideSubsystem.setDefaultCommand(moveLiftCommand);
        hookSubsystem.setDefaultCommand(pullRobotCommand);
        jointSubsystem.setDefaultCommand(controlClawsPosCommand);
        armSubsystem.setDefaultCommand(moveArmCommand);
        driveTrainSubsystem.setDefaultCommand(driveCommand);
        ledSubsystem.setDefaultCommand(changeOrangeForPixelsCommand);

        //drivers commands
        new Trigger(()-> (getRuntime()>=90&& driver.getButton(GamepadKeys.Button.Y))).whileActiveOnce(launchAirplaneCommand);
        new Trigger(()-> ( driver.getButton(GamepadKeys.Button.Y))).whileActiveOnce(launchAirplaneCommand);
        new GamepadButton(driver,GamepadKeys.Button.DPAD_UP).whenHeld(deployHookCommand);
        new GamepadButton(driver,GamepadKeys.Button.DPAD_DOWN).whenHeld(returnHookCommand);
        new Trigger(() -> driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)>0.05).whileActiveContinuous(strafeRightSlow);
        new Trigger(() -> driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)>0.05).whileActiveContinuous(strafeLeftSlow);
        new GamepadButton(driver,GamepadKeys.Button.LEFT_BUMPER).whileHeld(strafeLeft);
        new GamepadButton(driver,GamepadKeys.Button.RIGHT_BUMPER).whileHeld(strafeRight);
        new GamepadButton(driver, GamepadKeys.Button.X).whenPressed(speed6Command);
        new GamepadButton(driver, GamepadKeys.Button.B).whenPressed(speed7Command);
        new GamepadButton(driver, GamepadKeys.Button.A).whenPressed(speed1Command);


        //controllers command
        new GamepadButton(controller,GamepadKeys.Button.DPAD_LEFT).whileActiveOnce(deployHookCommand);
        new GamepadButton(controller,GamepadKeys.Button.DPAD_RIGHT).whileActiveOnce(returnHookCommand);
        new GamepadButton(controller, GamepadKeys.Button.DPAD_UP).whenPressed(new SetArmsTargetToNextPreSet(armSubsystem));
        new GamepadButton(controller, GamepadKeys.Button.DPAD_DOWN).whenPressed(new SetArmsTargetToPrevPreSet(armSubsystem));
        new Trigger(()->controller.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)>0.05).whileActiveOnce(openOrCloseRightClawCommand);
        new Trigger(()->controller.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)>0.05).whileActiveOnce(openOrCloseLeftClawCommand);
        new GamepadButton(controller, GamepadKeys.Button.B).whenPressed(closeClawsCommand);
        new GamepadButton(controller, GamepadKeys.Button.A).toggleWhenPressed(new SequentialCommandGroup(new ControlClawsCommand(clawSubsystem, false),goToGroundCommand,new ControlClawsCommand(clawSubsystem,true),new MoveLiftToPosCommand(slideSubsystem,140,()->getRuntime())),new SequentialCommandGroup(new ControlClawsCommand(clawSubsystem,false),goTo90DegCommand));
        new GamepadButton(controller, GamepadKeys.Button.LEFT_BUMPER).toggleWhenPressed(new SequentialCommandGroup(new ControlClawsCommand(clawSubsystem, false),new ChangeClawsDefaultPos(jointSubsystem,Constants.JointConstants.groundPos)),new SequentialCommandGroup(new ControlClawsCommand(clawSubsystem,false),new ChangeClawsDefaultPos(jointSubsystem,Constants.JointConstants.deg90Pos)));

        new Trigger(()->controller.getButton(GamepadKeys.Button.X)).toggleWhenActive(rotateClawsToPosCommand);
        new GamepadButton(controller,GamepadKeys.Button.Y).whenPressed(new SequentialCommandGroup( goToGroundOpenSlideCommand,moveLiftToIntakeCommand));
        new Trigger(()->controller.getButton(GamepadKeys.Button.Y)&& !clawSubsystem.isDetectedPixelLeft()).whenActive(new ControlLeftClawCommand(clawSubsystem,true));
        new Trigger(()->controller.getButton(GamepadKeys.Button.Y)&& !clawSubsystem.isDetectedPixelRight()).whenActive(new ControlRightClawCommand(clawSubsystem,true));
        new GamepadButton(controller,GamepadKeys.Button.RIGHT_BUMPER).whileActiveOnce(new MoveLiftToPosCommand(slideSubsystem,0,()->getRuntime())).whileActiveOnce(new SetArmsTarget(armSubsystem,0)).whileActiveOnce(new ControlClawsCommand(clawSubsystem, false)).whileActiveOnce(new ChangeClawsDefaultPos(jointSubsystem,Constants.JointConstants.deg90Pos));

        //automations
//        new Trigger(() -> slideSubsystem.isBottom()).whileActiveOnce(changeToGreenCommand);
//        new Trigger(() -> !slideSubsystem.isBottom()).whileActiveOnce(changeToNoneCommand);
//        if(slideSubsystem.isBottom())schedule(changeToGreenCommand);
//        else schedule(changeToNoneCommand);
        new Trigger(() -> clawSubsystem.isDetectedPixelRight()).whileActiveOnce(closeRightClawCommand).negate().whileActiveOnce(openRightClawCommand);
        new Trigger(() -> clawSubsystem.isDetectedPixelLeft()).whileActiveOnce(closeLeftClawCommand).negate().whileActiveOnce(openLeftClawCommand);
    }


    private void telemetry(Command command) {
        double[] leftARGB = clawSubsystem.getLeftARGB();
        double[] rightARGB = clawSubsystem.getRightARGB();
//        telemetry.addData("left rgb",leftARGB[1]+", "+leftARGB[2]+", "+leftARGB[3]);
        telemetry.addData("left distance",clawSubsystem.getLeftDistance());
//        telemetry.addData("right rgb",rightARGB[1]+", "+rightARGB[2]+", "+rightARGB[3]);
        telemetry.addData("right distance",clawSubsystem.getRightDistance());
        telemetry.addData("arm",armSubsystem.motor.getCurrentPosition());
        telemetry.addData("slide pos",slideSubsystem.getHeight());
//        telemetry.addData("preSet index",armSubsystem.getPreSetIndex());
        telemetry.addData("sumRight",clawSubsystem.sumRightColor());
        telemetry.addData("sumLeft",clawSubsystem.sumLeftColor());
        telemetry.update();
    }
    @Override
    public void run() {
        super.run();
        loopTime=getRuntime()-time;
        time= getRuntime();
        pose = odometrySubsystem.getPose();
        odometrySubsystem.update();
        sensorFrame++;
    }
}
