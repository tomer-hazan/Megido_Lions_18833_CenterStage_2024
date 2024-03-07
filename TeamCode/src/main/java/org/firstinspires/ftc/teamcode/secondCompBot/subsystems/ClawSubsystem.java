package org.firstinspires.ftc.teamcode.secondCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SensorColor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.secondCompBot.Constants;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.Positions;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.correctedBlueLeft;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.correctedBlueRight;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.correctedGreenLeft;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.correctedGreenRight;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.correctedRedLeft;
import static org.firstinspires.ftc.teamcode.secondCompBot.Constants.ClawConstants.correctedRedRight;

public class ClawSubsystem extends SubsystemBase {
    public final SensorColor colorSensorLeft;
    public final SensorColor colorSensorRight;
    public final DistanceSensor distanceSensorLeft;
    public final DistanceSensor distanceSensorRight;
    Positions leftClawPos;
    Positions rightClawPos;
    public final Servo leftClaw;//continuous
    public final Servo rightClaw;
    Supplier<Double> seconds;
    double[] correctedArgbLeft;
    double[] correctedArgbRight;
    double disLeft;
    double disRight;
    public ClawSubsystem(HardwareMap hardwareMap, Supplier<Double> seconds){
        this.rightClaw = hardwareMap.get(Servo.class,"right claw");
        this.leftClaw = hardwareMap.get(Servo.class,"left claw");
        colorSensorLeft = new SensorColor(hardwareMap,"color sensor left");
        colorSensorRight = new SensorColor(hardwareMap,"color sensor right");
        distanceSensorLeft = hardwareMap.get(DistanceSensor.class,"color sensor left");
        distanceSensorRight = hardwareMap.get(DistanceSensor.class,"color sensor right");
        this.seconds = seconds;
        rightClaw.setDirection(Servo.Direction.REVERSE);
        int[] argbLeft = colorSensorLeft.getARGB();
        int[] argbRight = colorSensorRight.getARGB();
        correctedArgbLeft = new double[]{argbLeft[0],argbLeft[1]* correctedRedLeft,argbLeft[2]* correctedGreenLeft,argbLeft[3]* correctedBlueLeft};
        correctedArgbRight = new double[]{argbRight[0],argbRight[1]* correctedRedRight,argbRight[2]* correctedGreenRight,argbRight[3]* correctedBlueRight};
        disLeft = distanceSensorLeft.getDistance(DistanceUnit.MM);
        disRight = distanceSensorRight.getDistance(DistanceUnit.MM);
        openOrCloseLeft(Positions.OPEN);
        openOrCloseRight(Positions.OPEN);
    }

    @Override
    public void periodic() {
//        if(RobotOpMode.sensorFrame%RobotOpMode.frameMod==0){
//            int[] argbRight = colorSensorRight.getARGB();
//            correctedArgbRight = new double[]{argbRight[0],argbRight[1]* correctedRedRight,argbRight[2]* correctedGreenRight,argbRight[3]* correctedBlueRight};
//            disRight = distanceSensorRight.getDistance(DistanceUnit.MM);
//        }
//        if(RobotOpMode.sensorFrame%RobotOpMode.frameMod==1){
//            int[] argbLeft = colorSensorLeft.getARGB();
//            correctedArgbLeft = new double[]{argbLeft[0],argbLeft[1]* correctedRedLeft,argbLeft[2]* correctedGreenLeft,argbLeft[3]* correctedBlueLeft};
//            disLeft = distanceSensorLeft.getDistance(DistanceUnit.MM);
//        }
        if(rightClawPos==Positions.OPEN){
            int[] argbRight = colorSensorRight.getARGB();
            correctedArgbRight = new double[]{argbRight[0],argbRight[1]* correctedRedRight,argbRight[2]* correctedGreenRight,argbRight[3]* correctedBlueRight};
            disRight = distanceSensorRight.getDistance(DistanceUnit.MM);
        }else{
            colorSensorRight.disable();
        }
        if(leftClawPos==Positions.OPEN){
            int[] argbLeft = colorSensorLeft.getARGB();
            correctedArgbLeft = new double[]{argbLeft[0],argbLeft[1]* correctedRedLeft,argbLeft[2]* correctedGreenLeft,argbLeft[3]* correctedBlueLeft};
            disLeft = distanceSensorLeft.getDistance(DistanceUnit.MM);
        }else{
            colorSensorLeft.disable();
        }
    }

    public void openOrCloseLeft(Positions position){
        switch (position){
            case CLOSE:
                leftClaw.setPosition(0.7);
                leftClawPos=Positions.CLOSE;
                break;
            case OPEN:
                leftClaw.setPosition(0.95);
                leftClawPos=Positions.OPEN;
                break;
        }
    }
    public void openOrCloseRight(Positions position){
        switch (position){
            case CLOSE:
                rightClaw.setPosition(0.52);
                rightClawPos=Positions.CLOSE;
                break;
            case OPEN:
                rightClaw.setPosition(0.8);
                rightClawPos=Positions.OPEN;
                break;
        }
    }
    public  double[] getLeftARGB(){return correctedArgbLeft;}
    public  double[] getRightARGB(){return correctedArgbRight;}
    public double getRightDistance(){return disRight;}
    public double getLeftDistance(){return disLeft;}
    public boolean isDetectedPixelLeft(){
        return getLeftDistance()<60&&detectPixelColorLeft()!=null;//toDo check if works well with far pixels
    }
    public boolean isDetectedPixelRight(){
        return getRightDistance()<60&&detectPixelColorRight()!=null;//toDo check if works well with far pixels
    }
    public Constants.GameElements.Pixals detectPixelColorLeft(){return detectPixelColorLeft(getLeftARGB());}
    public Constants.GameElements.Pixals detectPixelColorRight(){return detectPixelColorRight(getRightARGB());}
    public Constants.GameElements.Pixals detectPixelColorRight(double[] argb){
        double red = argb[1];
        double green = argb[2];
        double blue = argb[3];
        if(red<10&&green<10&&blue<10)return Constants.GameElements.Pixals.NULL;
        else if (red>170&&green>170&&blue>170) return Constants.GameElements.Pixals.WHITE;
        else if(green/red>1.5&&green/blue>1.5)return Constants.GameElements.Pixals.GREEN;
        else if (blue/green>1.3) return Constants.GameElements.Pixals.PURPLE;
        else return Constants.GameElements.Pixals.YELLOW;
    }
    public Constants.GameElements.Pixals detectPixelColorLeft(double[] argb){
        double red = argb[1];
        double green = argb[2];
        double blue = argb[3];
        if(red<10&&green<10&&blue<10)return Constants.GameElements.Pixals.NULL;
        else if (red>270&&green>270&&blue>270) return Constants.GameElements.Pixals.WHITE;
        else if(green/red>1.5&&green/blue>1.5)return Constants.GameElements.Pixals.GREEN;
        else if (blue/green>1.3) return Constants.GameElements.Pixals.PURPLE;
        else return Constants.GameElements.Pixals.YELLOW;
    }
    public double getTime(){return seconds.get();}
}
