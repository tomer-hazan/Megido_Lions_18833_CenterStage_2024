package org.firstinspires.ftc.teamcode.firstCompBot.opModes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands.DropPixelCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands.GetToWallCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.autonomous.commands.MoveLiftToHeightCommand;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.CartridgeSubsystam;
import org.firstinspires.ftc.teamcode.firstCompBot.subsystems.LiftSubsystem;
@Autonomous
@Disabled
public class Auto extends CommandOpMode {
    MoveLiftToHeightCommand up;
    MoveLiftToHeightCommand down;
    LiftSubsystem  liftSubsystem;
     GetToWallCommand getToWallCommand;
    @Override
    public void initialize(){
        liftSubsystem = new LiftSubsystem(hardwareMap);
        CartridgeSubsystam cartridge= new CartridgeSubsystam(hardwareMap);
        up = new MoveLiftToHeightCommand(liftSubsystem,telemetry,8600);
        down = new MoveLiftToHeightCommand(liftSubsystem,telemetry,0);
        DropPixelCommand dropPixel = new DropPixelCommand(cartridge,telemetry);
        SequentialCommandGroup commandGroup = new SequentialCommandGroup(up,dropPixel,down);
        commandGroup.schedule();
    }
}
