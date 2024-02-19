package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
@Disabled
public class PlaneLauncher extends OpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;

    @Override
    public void init() {
        leftMotor = hardwareMap.get(DcMotor.class, "LeftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "LeftMotor");
        
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        
    }

    @Override
    public void loop() {
        double x = 0.1;
        
        if(gamepad1.dpad_up){
            SetPowerUp(x);
            x += 0.1;
        }
        else if(gamepad1.dpad_down){
            SetPowerDown(x);
            x -= 0.1;
        }
    }
    
    public void SetPowerUp(double x) {
        leftMotor.setPower(x + 0.1);
        rightMotor.setPower(x + 0.1);
    }
    public void SetPowerDown(double x) {
        leftMotor.setPower(x - 0.1);
        rightMotor.setPower(x - 0.1);
    }
    
    
    
    
    
    
    
    
    
    
}










