package org.firstinspires.ftc.teamcode.secondCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SensorColor;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.secondCompBot.Constants;
import org.firstinspires.ftc.teamcode.secondCompBot.opModes.RobotOpMode;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.Positions;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.normalBlue;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.normalGreen;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.normalRed;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.rotation_limit;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.rotation_max;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.rotation_min;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.rotation_start;

public class ClawSubsystem extends SubsystemBase {
    public final SensorColor colorSensorLeft;
    public final SensorColor colorSensorRight;
    public final DistanceSensor distanceSensorLeft;
    public final DistanceSensor distanceSensorRight;
    public final ServoEx rotationServo1;
    public final ServoEx rotationServo2;
    public final Servo leftClaw;//continuous
    public final Servo rightClaw;
    Supplier<Double> seconds;
    double[] normalArgbLeft;
    double[] normalArgbRight;
    double disLeft;
    double disRight;
    public ClawSubsystem(HardwareMap hardwareMap, Supplier<Double> seconds){
        this.rightClaw = hardwareMap.get(Servo.class,"right claw");
        this.leftClaw = hardwareMap.get(Servo.class,"left claw");
        rotationServo1 = new SimpleServo(hardwareMap,"flip servo 1",rotation_start,rotation_limit);
        rotationServo2 = new SimpleServo(hardwareMap,"flip servo 2",rotation_start,rotation_limit);
        colorSensorLeft = new SensorColor(hardwareMap,"color sensor left");
        colorSensorRight = new SensorColor(hardwareMap,"color sensor right");
        distanceSensorLeft = hardwareMap.get(DistanceSensor.class,"color sensor left");
        distanceSensorRight = hardwareMap.get(DistanceSensor.class,"color sensor right");
        this.seconds = seconds;
        rotationServo2.setInverted(false);
        rightClaw.setDirection(Servo.Direction.REVERSE);
        int[] argbLeft = colorSensorLeft.getARGB();
        int[] argbRight = colorSensorRight.getARGB();
        normalArgbLeft = new double[]{argbLeft[0],argbLeft[1]*normalRed,argbLeft[2]*normalGreen,argbLeft[3]*normalBlue};
        normalArgbRight = new double[]{argbRight[0],argbRight[1]*normalRed,argbRight[2]*normalGreen,argbRight[3]*normalBlue};
        disLeft = distanceSensorLeft.getDistance(DistanceUnit.MM);
        disRight = distanceSensorRight.getDistance(DistanceUnit.MM);
        openOrCloseLeft(Positions.OPEN);
        openOrCloseRight(Positions.OPEN);
    }

    @Override
    public void periodic() {
        if(RobotOpMode.sensorFrame%RobotOpMode.frameMod==0){
            int[] argbRight = colorSensorRight.getARGB();
            normalArgbRight = new double[]{argbRight[0],argbRight[1]*normalRed,argbRight[2]*normalGreen,argbRight[3]*normalBlue};
            disRight = distanceSensorRight.getDistance(DistanceUnit.MM);
        }
        if(RobotOpMode.sensorFrame%RobotOpMode.frameMod==1){
            int[] argbLeft = colorSensorLeft.getARGB();
            normalArgbLeft = new double[]{argbLeft[0],argbLeft[1]*normalRed,argbLeft[2]*normalGreen,argbLeft[3]*normalBlue};
            disLeft = distanceSensorLeft.getDistance(DistanceUnit.MM);
        }
    }

    public void openOrCloseLeft(Positions position){
        switch (position){
            case CLOSE:
                leftClaw.setPosition(0.65);
                break;
            case OPEN:
                leftClaw.setPosition(0.95);
                break;
        }
    }
    public void openOrCloseRight(Positions position){
        switch (position){
            case CLOSE:
                rightClaw.setPosition(0.45);
                break;
            case OPEN:
                rightClaw.setPosition(0.8);
                break;
        }
    }
    public void turnToAngle(double angle){
        rotationServo1.turnToAngle(angle);
        rotationServo2.turnToAngle(angle);
    }
    public void setPos(double pos){
        if(pos<rotation_min)pos=rotation_min;
        if(pos>rotation_max)pos=rotation_max;
        rotationServo1.setPosition(pos);
        rotationServo2.setPosition(pos);
    }
    public  double[] getLeftARGB(){return normalArgbLeft;}
    public  double[] getRightARGB(){return normalArgbRight;}
    public double getRightDistance(){return disRight;}
    public double getLeftDistance(){return disLeft;}
    public boolean isDetectedPixelLeft(){
        return getLeftDistance()<60&&detectPixelColorLeft()!=null;
    }
    public boolean isDetectedPixelRight(){
        return getRightDistance()<60&&detectPixelColorRight()!=null;
    }
    public Constants.GameElements.Pixals detectPixelColorLeft(){return detectPixelColor(getLeftARGB());}
    public Constants.GameElements.Pixals detectPixelColorRight(){return detectPixelColor(getRightARGB());}
    public Constants.GameElements.Pixals detectPixelColor(double[] argb){//toDO finish this
        double red = argb[1];
        double green = argb[2];
        double blue = argb[3];
        if(red<10&&green<10&&blue<10)return Constants.GameElements.Pixals.NULL;
        else if(green/red>1.5&&green/blue>1.5)return Constants.GameElements.Pixals.GREEN;

        return Constants.GameElements.Pixals.NULL;
    }
    public double getTime(){return seconds.get();}
}
