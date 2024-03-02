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

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.Positions;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.rotation_limit;
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
    public ClawSubsystem(HardwareMap hardwareMap, Supplier<Double> seconds){
        this.rightClaw = hardwareMap.get(Servo.class,"right claw");
        this.leftClaw = hardwareMap.get(Servo.class,"left claw");
        rotationServo1 = new SimpleServo(hardwareMap,"claw rotation servo 1",rotation_start,rotation_limit);
        rotationServo2 = new SimpleServo(hardwareMap,"claw rotation servo 2",rotation_start,rotation_limit);
        colorSensorLeft = new SensorColor(hardwareMap,"color sensor left");
        colorSensorRight = new SensorColor(hardwareMap,"color sensor right");
        distanceSensorLeft = hardwareMap.get(DistanceSensor.class,"color sensor left");
        distanceSensorRight = hardwareMap.get(DistanceSensor.class,"color sensor right");
        colorSensorLeft.getARGB();
        this.seconds = seconds;
    }

    public void openOrCloseLeft(Positions position){
        switch (position){
            case CLOSE:
                leftClaw.setPosition(0);
                break;
            case OPEN:
                leftClaw.setPosition(0.5);
                break;
        }
    }
    public void openOrCloseRight(Positions position){
        switch (position){
            case CLOSE:
                rightClaw.setPosition(0);
                break;
            case OPEN:
                rightClaw.setPosition(1);
                break;
        }
    }
    public void turnToAngle(double angle){
        rotationServo1.turnToAngle(angle);
        rotationServo2.turnToAngle(angle);
    }
    public void setPos(double pos){
        rotationServo1.setPosition(pos);
        rotationServo2.setPosition(pos);
    }
    public  int[] getLeftARGB(){return colorSensorLeft.getARGB();}
    public  int[] getRightARGB(){return colorSensorRight.getARGB();}
    public double getRightDistance(){return distanceSensorRight.getDistance(DistanceUnit.MM);}
    public double getLeftDistance(){return distanceSensorLeft.getDistance(DistanceUnit.MM);}
    public boolean isDetectedPixelLeft(){
        return getLeftDistance()<60&&detectPixelColorLeft()!=null;
    }
    public boolean isDetectedPixelRight(){
        return getRightDistance()<60&&detectPixelColorRight()!=null;
    }
    public Constants.GameElements.Pixals detectPixelColorLeft(){return detectPixelColor(colorSensorLeft.getARGB());}
    public Constants.GameElements.Pixals detectPixelColorRight(){return detectPixelColor(colorSensorRight.getARGB());}
    public Constants.GameElements.Pixals detectPixelColor(int[] argb){
        int red = argb[1];
        int green = argb[2];
        int blue = argb[3];
        if(red>5000&&green>5000&&blue>5000)return Constants.GameElements.Pixals.WHITE;
        if(red>5000&&blue>5000)return Constants.GameElements.Pixals.PURPLE;
        if(red>5000&&green>5000)return Constants.GameElements.Pixals.YELLOW;
        if(green>5000)return Constants.GameElements.Pixals.GREEN;
        return Constants.GameElements.Pixals.NULL;
    }
//    @Override
//    public void periodic(){
//        if(isDetectedPixelLeft())openOrCloseLeft(Constants.ClawConstants.Positions.CLOSE);
//        else openOrCloseLeft(Constants.ClawConstants.Positions.OPEN);
//        if(isDetectedPixelRight())openOrCloseRight(Constants.ClawConstants.Positions.CLOSE);
//        else openOrCloseRight(Constants.ClawConstants.Positions.OPEN);
//    }
    public double getTime(){return seconds.get();}
}
