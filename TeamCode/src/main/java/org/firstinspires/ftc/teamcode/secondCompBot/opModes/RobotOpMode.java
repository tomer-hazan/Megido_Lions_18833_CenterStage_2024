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
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.AirplaneSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.HookSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.LEDSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.MyOdometrySubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.subsystems.SlideSubsystem;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ChangeColorsCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ChangeSpeedCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlClawsAngleCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlClawsAngleOnceCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlClawsCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlColorsCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlLeftClawCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ControlRightClawCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.DeployHookCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.DriveHorizontalCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.LaunchAirplaneCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.MecanumMovmentCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.MoveArmCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.MoveLiftCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.PullRobotCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.ReturnHookCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.RotateClawsToAngleCommand;
import org.firstinspires.ftc.teamcode.secondCompBot.teleOP.commands.SwitchColorsCommand;

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
    public static int speed;
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
    ChangeSpeedCommand changeSpeedCommand;
    ControlClawsAngleCommand controlClawsAngleCommand;
    ControlClawsAngleCommand controlClawsAngleCommand90;
    MoveArmCommand moveArmCommand;
    ControlColorsCommand controlColorsCommand;
    RotateClawsToAngleCommand rotateClawsToAngleCommand;
    ControlClawsCommand closeClawsCommand;
    ControlClawsCommand openClawsCommand;
    ControlClawsAngleCommand goTo130Command;
    ControlClawsAngleOnceCommand goToGroundCommand;
//    ControlClawsPosOnceCommand goToGroundCommand;



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
        aToggle = new ToggleButtonReader(controller, GamepadKeys.Button.A);

    }
    private void initSubsystems(){
        slideSubsystem = new SlideSubsystem(hardwareMap);
        driveTrainSubsystem = new DriveTrainSubsystem(hardwareMap);
        airplaneSubsystem = new AirplaneSubsystem(hardwareMap);
        clawSubsystem = new ClawSubsystem(hardwareMap,()->getRuntime());
        hookSubsystem = new HookSubsystem(hardwareMap);
        odometrySubsystem = new MyOdometrySubsystem(hardwareMap);
        ledSubsystem = new LEDSubsystem(hardwareMap);
        armSubsystem = new ArmSubsystem(hardwareMap);

        //visionSubsystem = new VisionSubsystem(hardwareMap,telemetry);


    }
    private void initCommands(){
        constructCommands();
        assignCommands();
    }
    private void constructCommands(){
        deployHookCommand = new DeployHookCommand(hookSubsystem);
        driveCommand= new DriveCommand(driveTrainSubsystem,() -> driver.getLeftX(),() -> driver.getLeftY(),() -> driver.getRightX());
        launchAirplaneCommand = new LaunchAirplaneCommand(airplaneSubsystem);
        moveLiftCommand = new MoveLiftCommand(slideSubsystem,() -> controller.getLeftY());
        returnHookCommand = new ReturnHookCommand(hookSubsystem);
        driveHorizontalCommandLeft = new DriveHorizontalCommand(driveTrainSubsystem,()-> driver.getLeftX(),() -> 1.0);
        driveHorizontalCommandRight = new DriveHorizontalCommand(driveTrainSubsystem,()-> driver.getLeftX(),() -> -1.0);
        openRightClawCommand = new ControlRightClawCommand(clawSubsystem,true);
        closeRightClawCommand = new ControlRightClawCommand(clawSubsystem,false);
        openLeftClawCommand = new ControlLeftClawCommand(clawSubsystem,true);
        closeLeftClawCommand = new ControlLeftClawCommand(clawSubsystem,false);
        switchColorsCommand = new SwitchColorsCommand(ledSubsystem);
        changeToGreenCommand = new ChangeColorsCommand(ledSubsystem,0);
        changeToNoneCommand = new ChangeColorsCommand(ledSubsystem,3);
        strafeLeft = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(-1));
        strafeRight = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(1));
        strafeLeftSlow = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(-0.5));
        strafeRightSlow = new MecanumMovmentCommand(driveTrainSubsystem,() -> Double.valueOf(0.5));
        moveLiftSlowCommand = new MoveLiftCommand(slideSubsystem,() -> -controller.getLeftY()*0.65);
        changeSpeedCommand = new ChangeSpeedCommand();
        controlClawsAngleCommand = new ControlClawsAngleCommand(clawSubsystem,() ->armSubsystem.getAngle());
        goTo130Command = new ControlClawsAngleCommand(clawSubsystem,() ->armSubsystem.getAngle(),130);
        goToGroundCommand = new ControlClawsAngleOnceCommand(clawSubsystem,Constants.ClawConstants.groundAngle);
//        goToGroundCommand = new ControlClawsPosOnceCommand(clawSubsystem,Constants.ClawConstants.groundPos);
        moveArmCommand = new MoveArmCommand(armSubsystem,()->controller.getRightY());
        controlColorsCommand = new ControlColorsCommand(ledSubsystem,()->clawSubsystem.detectPixelColorLeft(),()->clawSubsystem.detectPixelColorRight());
        pullRobotCommand = new PullRobotCommand(hookSubsystem,()->0.0,()->controller.getButton(GamepadKeys.Button.DPAD_UP),()->controller.getButton(GamepadKeys.Button.DPAD_DOWN));
        rotateClawsToAngleCommand = new RotateClawsToAngleCommand(clawSubsystem, ()->controller.getRightX()*360);
        closeClawsCommand = new ControlClawsCommand(clawSubsystem,false);
        openClawsCommand = new ControlClawsCommand(clawSubsystem,true);

    }
    private void assignCommands(){
        //default commands
        slideSubsystem.setDefaultCommand(moveLiftCommand);
        hookSubsystem.setDefaultCommand(pullRobotCommand);
        clawSubsystem.setDefaultCommand(new ControlClawsAngleCommand(clawSubsystem,()->armSubsystem.getAngle()));
//        clawSubsystem.setDefaultCommand(rotateClawsToAngleCommand);
        armSubsystem.setDefaultCommand(moveArmCommand);

        ledSubsystem.setDefaultCommand(controlColorsCommand);

        //drivers commands
        new Trigger(()-> (getRuntime()>=90&& driver.getButton(GamepadKeys.Button.Y))).whileActiveOnce(launchAirplaneCommand);
        new GamepadButton(driver,GamepadKeys.Button.DPAD_UP).whenHeld(deployHookCommand);
        new GamepadButton(driver,GamepadKeys.Button.DPAD_DOWN).whenHeld(returnHookCommand);
        new Trigger(() -> driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)>0.05).whileActiveContinuous(strafeRightSlow);
        new Trigger(() -> driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)>0.05).whileActiveContinuous(strafeLeftSlow);
        new GamepadButton(driver,GamepadKeys.Button.LEFT_BUMPER).whileHeld(strafeLeft);
        new GamepadButton(driver,GamepadKeys.Button.RIGHT_BUMPER).whileHeld(strafeRight);
        new GamepadButton(driver, GamepadKeys.Button.X).whenPressed(changeSpeedCommand);


        //controllers command
        new GamepadButton(controller,GamepadKeys.Button.DPAD_LEFT).whileActiveOnce(deployHookCommand);
        new GamepadButton(controller,GamepadKeys.Button.DPAD_RIGHT).whileActiveOnce(returnHookCommand);
        //new GamepadButton(controller, GamepadKeys.Button.B).whenPressed(switchColorsCommand);
        new Trigger(() -> slideSubsystem.isBottom()).whileActiveOnce(changeToGreenCommand);
        new Trigger(() -> !slideSubsystem.isBottom()).whileActiveOnce(changeToNoneCommand);
        if(slideSubsystem.isBottom())schedule(changeToGreenCommand);
        else schedule(changeToNoneCommand);
        driveTrainSubsystem.setDefaultCommand(driveCommand);
        new Trigger(() -> clawSubsystem.isDetectedPixelRight()).whileActiveOnce(closeRightClawCommand).negate().whileActiveOnce(openRightClawCommand);
        new Trigger(() -> clawSubsystem.isDetectedPixelLeft()).whileActiveOnce(closeLeftClawCommand).negate().whileActiveOnce(openLeftClawCommand);
        new GamepadButton(controller, GamepadKeys.Button.LEFT_BUMPER).whileHeld(openLeftClawCommand);
        new GamepadButton(controller, GamepadKeys.Button.RIGHT_BUMPER).whileHeld(openRightClawCommand);
        new Trigger(()->controller.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)>0.05).whileActiveContinuous(closeLeftClawCommand);
        new Trigger(()->controller.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)>0.05).whileActiveContinuous(closeRightClawCommand);
        new GamepadButton(controller, GamepadKeys.Button.B).toggleWhenPressed(closeClawsCommand,openClawsCommand);
        new GamepadButton(controller, GamepadKeys.Button.A).toggleWhenPressed(new SequentialCommandGroup(new ControlClawsCommand(clawSubsystem,false),goToGroundCommand,new ControlClawsCommand(clawSubsystem,true),controlClawsAngleCommand),new SequentialCommandGroup(new ControlClawsCommand(clawSubsystem,false),goTo130Command));
    }


    private void telemetry(Command command) {
//        telemetry.addData("lift height: ", slideSubsystem.getHeight());
//        telemetry.addData("time ",time);
//        telemetry.addData("pos","("+pose.getX()+", "+pose.getY()+")");
//        telemetry.addData("heading",pose.getHeading());
//        telemetry.addData("is bottom", slideSubsystem.isBottom());
//        telemetry.addData("arm",armSubsystem.motor.getCurrentPosition());
//        telemetry.addData("arm deg",armSubsystem.getAngle());
//        telemetry.addData("claw deg",clawSubsystem.rotationServo1.getAngle());
//        telemetry.addData("claw deg2",clawSubsystem.rotationServo2.getAngle());
//        telemetry.addData("color ",switchColorsCommand.getColor());
//        telemetry.addData("speed",speed);
//        telemetry.addData("left pixel",clawSubsystem.detectPixelColorLeft());
//        telemetry.addData("right pixel",clawSubsystem.detectPixelColorRight());
//        telemetry.addData("left argb",clawSubsystem.getLeftARGB()[0]+", "+clawSubsystem.getLeftARGB()[1]+", "+clawSubsystem.getLeftARGB()[2]+", "+clawSubsystem.getLeftARGB()[3]);
//        telemetry.addData("was just pressed",controller.wasJustPressed(GamepadKeys.Button.B));
//        telemetry.addData("button",controller.getButton(GamepadKeys.Button.B));
        double[] leftARGB = clawSubsystem.getLeftARGB();
        telemetry.addData("left corrected argb",(int)leftARGB[0]+", "+(int)leftARGB[1]+", "+(int)leftARGB[2]+", "+(int)leftARGB[3]);
        double[] rightARGB = clawSubsystem.getRightARGB();
        telemetry.addData("right corrected argb",(int)rightARGB[0]+", "+(int)rightARGB[1]+", "+(int)rightARGB[2]+", "+(int)rightARGB[3]);
//        telemetry.addData("left argb",clawSubsystem.colorSensorLeft.red()+", "+clawSubsystem.colorSensorLeft.green()+", "+clawSubsystem.colorSensorLeft.blue());
//        telemetry.addData("right argb",clawSubsystem.colorSensorRight.red()+", "+clawSubsystem.colorSensorRight.green()+", "+clawSubsystem.colorSensorRight.blue());
//        telemetry.addData("flip pos",clawSubsystem.rotationServo1.getPosition());
//        telemetry.addData("flip deg",clawSubsystem.rotationServo1.getAngle());
        telemetry.update();
    }
    @Override
    public void run() {
        super.run();
        time= getRuntime();
        pose = odometrySubsystem.getPose();
        odometrySubsystem.update();
        sensorFrame++;
    }
}
