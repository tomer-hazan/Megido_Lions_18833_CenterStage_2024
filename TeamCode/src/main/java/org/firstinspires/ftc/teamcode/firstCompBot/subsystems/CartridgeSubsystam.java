package org.firstinspires.ftc.teamcode.firstCompBot.subsystems;

import android.widget.Switch;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SensorColor;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants;

import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants.insertRate;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants.bottom_limit;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants.dropRate;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants.top_limit;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants.CartridgePositions;

public class CartridgeSubsystam extends SubsystemBase {
    final CRServo droppingServo;//continuous
    final ServoEx rotationServo;
    final SensorColor colorSensor;
    public CartridgeSubsystam(HardwareMap hardwareMap){
        this.droppingServo = new CRServo(hardwareMap,"droppingServo");
        this.rotationServo = new SimpleServo(hardwareMap,"droppingServo",bottom_limit,top_limit);
        this.colorSensor = new SensorColor(hardwareMap,"InTakeColorSensor");
    }

    public void rotateToPosition(CartridgePositions position){
        switch (position){
            case DROP:
                rotationServo.setPosition(1);
                break;
            case INTAKE:
                rotationServo.setPosition(0);
                break;
        }
    }

    public void drop(){
        droppingServo.set(dropRate);
    }
    public void insert(){
        droppingServo.set(insertRate);
    }


}
