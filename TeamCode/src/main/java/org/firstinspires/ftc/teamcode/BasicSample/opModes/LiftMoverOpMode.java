package org.firstinspires.ftc.teamcode.BasicSample.opModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.MecanumControllerCommand;
import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.BasicSample.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.BasicSample.commands.MoveLiftCommand;
import org.firstinspires.ftc.teamcode.BasicSample.commands.OdometryCommand;
import org.firstinspires.ftc.teamcode.BasicSample.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.BasicSample.subsystems.LiftSubsystem;
@TeleOp
public class LiftMoverOpMode extends CommandOpMode {
    private LiftSubsystem liftSubsystem;
    private DriveTrainSubsystem driveTrainSubsystem;
    private GamepadEx driver;
    private GamepadEx player;
    MoveLiftCommand moveLiftCommand;
    OdometryCommand odometryCommand;
    DriveCommand driveCommand;


    @Override
    public void initialize() {
        driver = new GamepadEx(gamepad1);
        player = new GamepadEx(gamepad2);
        liftSubsystem = new LiftSubsystem(hardwareMap);
        driveTrainSubsystem = new DriveTrainSubsystem(hardwareMap);
        moveLiftCommand = new MoveLiftCommand(liftSubsystem,() -> player.getLeftY());
        driveCommand= new DriveCommand(driveTrainSubsystem,() -> driver.getLeftX(),() -> driver.getLeftY(),() -> driver.getRightX());
        //odometryCommand = new OdometryCommand(() -> .getLeftY());

        liftSubsystem.setDefaultCommand(moveLiftCommand);
        CommandScheduler.getInstance().onCommandExecute(this::telemetry);

    }


    private void telemetry(Command command) {
        telemetry.addData("lift height: ",liftSubsystem.getHeight());
        telemetry.addData("off set: ",String.valueOf(liftSubsystem.getencoderOffSet()));
        telemetry.addData("buttom ",String.valueOf(liftSubsystem.isBottom()));
        telemetry.addData("top ",String.valueOf(liftSubsystem.isTop()));
        telemetry.update();
    }


}
