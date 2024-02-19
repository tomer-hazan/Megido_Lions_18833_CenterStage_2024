package org.firstinspires.ftc.teamcode.firstCompBot;

import com.acmerobotics.dashboard.config.Config;

import org.opencv.core.Rect;

import java.util.HashMap;

public class Constants {
    public static final class DriveTrainConstants {
        public final static double ticks_per_revolution =
                MotorConstants.REVThroughBoreEncoder.ticks_per_revolution;

        public final static double WheelDiameter = 0.096;//toDo
        public final static double OdometryWheelDiameter = 0.0508;

        public final static double GearRatio = 19.2;//toDo

        public final static double TrackWidth = 0.254;
        public final static double CenterWheelOffset = 0.1016;//toDo

        public static double MaxVelocity = 1.867873;//1.764102,1.867873//,// Its recommended to put max velocity to 90% of the true value
        public static double MaxAccel = 1.7;

        public final static double odometry_wheel_ticks_to_meters = OdometryWheelDiameter * Math.PI / ticks_per_revolution;

        public static double kV = 0.478 ;
        public static double kStatic = 0.0688 ;
        public static double kA = 0.16 ;
    }
    public static final class CartridgeConstants {
        public static final double top_limit = 0;
        public static final double bottom_limit = 180;
        public enum CartridgePositions {
            COLLECTION,
            DROP
        }
        public static final boolean inDirection = false;
        public static final boolean outDirection = true;
        public static final double dropRate = 1 ;
        public static final double insertRate = 1 ;
        public static final double autoRotateCartridgeHeight = 1.4;//toDO test
    }
    public static final class HookConstants {
        public static final double top_limit = 0;
        public static final double bottom_limit = 180;
        public static final double gear_ratio = 192;//toDo
        public static final double[] levels = new double[]{};//toDo
        public static final double levelError = 50;//toDo
        public static final double wheel_diameter = 0.01;
        public static final boolean up_direction_inverted=false;
        public static final boolean down_direction_inverted=true;
        public static final double power=1;
        public static final double ticks_per_revolution = MotorConstants.RevHDHexMotor.ticks_per_revolution/gear_ratio;
        public static double ticksToMeters(double ticks){
            return 0;
        }
    }
    public static final class AirplaneConstants {
        public static final double top_limit = 0;
        public static final double bottom_limit = 180;
    }
    public static final class LiftConstants {
        public static final double number_of_motors = 2;
        public static final double min_cartridge_hight =0.3;//toDo
        public static final double inverse_motor_gear = 2.89 * 2.89;//toDo
        public static final double ticks_per_motor_revolution = MotorConstants.RevHDHexMotor.ticks_per_revolution * inverse_motor_gear;
        public static final double gear = 1.0f;//external gear//toDo
        public static final int ticks_per_revolution = (int)(ticks_per_motor_revolution / gear);
        public static final double gear_radios = 0.017;//toDo
        public static final double distance_per_revolution = 2 * Math.PI * gear_radios;
        public static final double distance_per_tick = distance_per_revolution / ticks_per_revolution;

        public static final double top_height = 1.26;//toDo

        static public double meters2ticks(double height) {
            return (height/ LiftConstants.distance_per_tick);
        }
        static public double ticks2meters(double ticks) {
            return (ticks* LiftConstants.distance_per_tick);
        }
    }
    public static final class InTakeConstants{
        public static final double colorError = 0.1;
        public static final double inMaxPower = 1;
        public static final double outMaxPower = 1;
        public static final boolean inDirection = true;
        public static final boolean outDirection = false;

    }
    public static final class GameElaments{
        public enum Pixals {
            GREEN,
            WHITE,
            PURPLE,
            YELLOW,
            NULL
        }
        public static final HashMap<Pixals, int[]> PixalColors= new HashMap<Pixals, int[]>() {{
            put(Pixals.GREEN, new int[]{0,255,0});
            put(Pixals.WHITE, new int[]{255,255,255});
            put(Pixals.PURPLE, new int[]{255,0,255});
            put(Pixals.YELLOW, new int[]{255,255,0});
            put(Pixals.NULL, new int[]{116,116,116});
        }};
    }
    public static final class MotorConstants {
        public static final class RevHDHexMotor {
            public static final int ticks_per_revolution = 28;
            public static final int freeSpeedRPM = 6000;
            public static final int revolutions_per_second = freeSpeedRPM / 60;
        }

        public static final class REVThroughBoreEncoder {
            public static final int ticks_per_revolution = 8192;
        }
    }
    public static final class GameConstants{
        public static final int gameTime = 150;//0-150
        public static final int autoTime = 30;//0-30
        public static final int endGameTime = 90;//120 - 150
        public enum gamePeriod {
            autonomous,
            teleOp,
            endGame
        }
        public enum GameType {
            LEFT,
            CENTER,
            RIGHT
        }
        public enum StartingPosition {
            RED,
            BLUE
        }
    }
    public static class util{
        public static double meterToInch(double m){
            return  39.37*m;
        }
    }
    @Config
    public static final class VisionConstants {
        public final static int camera_width = 1280;
        public final static int camera_height = 720;
        public final static int R = 0;
        public final static int G = 1;
        public final static int B = 2;

        public static Rect Red = new Rect(505, 400, 120, 180);//not the real numbers (temporary)
        public static Rect Blue = new Rect(600, 400, 120, 180);//not the real numbers (temporary)

        public static double RedHueThresholdLow = 0;
        public static double RedHueThresholdHigh = 180;
        public static double RedSaturationThresholdLow = 67;
        public static double RedSaturationThresholdHigh = 255;
        public static double RedValueThresholdLow = 5;
        public static double RedValueThresholdHigh = 163;

        public static double BlueHueThresholdLow = 39;
        public static double BlueHueThresholdHigh = 180;
        public static double BlueSaturationThresholdLow = 46;
        public static double BlueSaturationThresholdHigh = 255;
        public static double BlueValueThresholdLow = 0;
        public static double BlueValueThresholdHigh = 219;

    }

}

