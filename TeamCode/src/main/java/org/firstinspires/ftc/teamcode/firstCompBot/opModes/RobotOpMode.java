package org.firstinspires.ftc.teamcode.firstCompBot.opModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.firstCompBot.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.LaunchAirplaneCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.commands.MoveLiftCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.AirplaneSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CartridgeSubsystam;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.HookSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.InTakeSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.MyOdometrySubsystem;

@TeleOp
public class RobotOpMode extends CommandOpMode {
    LiftSubsystem liftSubsystem;
    DriveTrainSubsystem driveTrainSubsystem;
    AirplaneSubsystem airplaneSubsystem;
    CartridgeSubsystam cartridgeSubsystam;
    HookSubsystem hookSubsystem;
    InTakeSubsystem inTakeSubsystem;
    GamepadEx driver;
    GamepadEx operator;
    MoveLiftCommand moveLiftCommand;
    DriveCommand driveCommand;
    MyOdometrySubsystem odometrySubsystem;


    @Override
    public void initialize() {
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        initSubsystems();
        initCommands();
        CommandScheduler.getInstance().onCommandExecute(this::telemetry);

    }
    private void initSubsystems(){
        liftSubsystem = new LiftSubsystem(hardwareMap);
        driveTrainSubsystem = new DriveTrainSubsystem(hardwareMap);
        airplaneSubsystem = new AirplaneSubsystem(hardwareMap);
        cartridgeSubsystam = new  CartridgeSubsystam(hardwareMap);
        hookSubsystem = new HookSubsystem(hardwareMap);
        inTakeSubsystem = new InTakeSubsystem(hardwareMap);
        odometrySubsystem = new MyOdometrySubsystem(hardwareMap);

    }
    private void initCommands(){
        moveLiftCommand = new MoveLiftCommand(liftSubsystem,() -> operator.getLeftY());
        driveCommand= new DriveCommand(driveTrainSubsystem,() -> driver.getLeftX(),() -> driver.getLeftY(),() -> driver.getRightX());
        new GamepadButton(operator, GamepadKeys.Button.X).whenActive(new LaunchAirplaneCommand(airplaneSubsystem));
        liftSubsystem.setDefaultCommand(moveLiftCommand);
    }


    private void telemetry(Command command) {
        telemetry.addData("lift height: ",liftSubsystem.getHeight());
        telemetry.addData("off set: ",String.valueOf(liftSubsystem.getEncoderOffset()));
        telemetry.addData("buttom ",String.valueOf(liftSubsystem.isBottom()));
        telemetry.addData("top ",String.valueOf(liftSubsystem.isTop()));
        telemetry.update();
    }


}
