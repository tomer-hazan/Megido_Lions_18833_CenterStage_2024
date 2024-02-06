package org.firstinspires.ftc.teamcode.firstCompBot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants.CartridgePositions;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants.bottom_limit;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants.dropRate;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants.insertRate;
import static org.firstinspires.ftc.teamcode.firstCompBot.Constants.CartridgeConstants.top_limit;

public class CartridgeSubsystam extends SubsystemBase {
    //toDo check if needs to have isActive function that returns false after given time
    final CRServo droppingServo;//continuous
    final ServoEx rotationServo;
    public CartridgeSubsystam(HardwareMap hardwareMap){
        this.droppingServo = new CRServo(hardwareMap,"dropping cartridge servo");
        this.rotationServo = new SimpleServo(hardwareMap,"rotation cartridge servo",bottom_limit,top_limit);
        droppingServo.setInverted(true);
    }

    public void rotateToPosition(CartridgePositions position){
        switch (position){
            case DROP:
                rotationServo.setPosition(1);
                break;
            case COLLECTION:
                rotationServo.setPosition(0);
                break;
        }
    }
    public void setDirection(boolean val){
        droppingServo.setInverted(val);
    }
    public void drop(){
        droppingServo.set(dropRate);
    }
    public void insert(){
        droppingServo.set(insertRate);
    }
    public void stop(){
        droppingServo.set(0);
    }



}
