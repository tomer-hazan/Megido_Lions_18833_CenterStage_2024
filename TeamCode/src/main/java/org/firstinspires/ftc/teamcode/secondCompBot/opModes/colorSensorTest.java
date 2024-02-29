package org.firstinspires.ftc.teamcode.secondCompBot.opModes;

import com.arcrobotics.ftclib.hardware.SensorColor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class colorSensorTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        double redCal = 255/3700.0;
        double greenCal = 255/6000.0;
        double blueCal = 255/4800.0;
        SensorColor colorSensor2 = new SensorColor(hardwareMap,"color2");
        SensorColor colorSensor3 = new SensorColor(hardwareMap,"color3");
        DistanceSensor dist2 = hardwareMap.get(DistanceSensor.class,"color2");
        DistanceSensor dist3 = hardwareMap.get(DistanceSensor.class,"color3");
        Servo left = hardwareMap.get(Servo.class,"left");
        Servo right = hardwareMap.get(Servo.class,"right");
        left.setDirection(Servo.Direction.REVERSE);
        right.setPosition(1);
        left.setPosition(0.5);
        if (isStopRequested())return;
        waitForStart();
        while (!isStopRequested()){
            telemetry.addData("color2 calibrated (A,R,G,B) : ","("+colorSensor2.alpha()+","+(int)(colorSensor2.red()*redCal)+","+(int)(colorSensor2.green()*greenCal)+","+(int)(colorSensor2.blue()*blueCal)+")");
            telemetry.addData("color2 (A,R,G,B) : ","("+colorSensor2.alpha()+","+colorSensor2.red()+","+colorSensor2.green()+","+colorSensor2.blue()+")");
            telemetry.addData("color3 (A,R,G,B) : ","("+colorSensor3.alpha()+","+colorSensor3.red()+","+colorSensor3.green()+","+colorSensor3.blue()+")");
            telemetry.addData("dis2",dist2.getDistance(DistanceUnit.MM));
            telemetry.addData("dis3",dist3.getDistance(DistanceUnit.MM));
            telemetry.update();
            //3-left
            //2-right
//            if(dist2.getDistance(DistanceUnit.MM)<60&&(int)(colorSensor2.red()*redCal)>3&&(int)(colorSensor2.green()*greenCal)>3&&(int)(colorSensor2.blue()*blueCal)>3&&right.getAngle()==15)right.setPosition(0);
//            else if(right.getAngle()==45)right.setPosition(1);
//            if(dist3.getDistance(DistanceUnit.MM)<60&&(int)(colorSensor3.red()*redCal)>10&&(int)(colorSensor3.green()*greenCal)>10&&(int)(colorSensor3.blue()*blueCal)>10)left.turnToAngle(-45);
            //else left.turnToAngle(gamepad1.left_stick_y*360);
            if(gamepad1.a){
                right.setPosition(1);
                left.setPosition(0.5);
            }
            if(gamepad1.b){
                right.setPosition(0);
                left.setPosition(0);
            }
            telemetry.addData("left",left.getPosition());
            telemetry.addData("right",right.getPosition());
        }
        return;

    }
}
