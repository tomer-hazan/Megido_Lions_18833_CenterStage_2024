package org.firstinspires.ftc.teamcode.firstCompBot;

import java.util.HashMap;

public class Constants {
    public static final class DriveTrainConstants {
        public final static double ticks_per_revolution =
                MotorConstants.REVThroughBoreEncoder.ticks_per_revolution;

        public final static double WheelDiameter = 0.096;//toDo
        public final static double OdometryWheelDiameter = 0.05;//toDo

        public final static double GearRatio = 19.2;//toDo

        public final static double TrackWidth = 0.259;//toDo
        public final static double CenterWheelOffset = 0.2;//toDo
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
            INTAKE,
            DROP
        }
        public static final double dropRate = 0.8 ;
        public static final double insertRate = 0.2 ;
    }
    public static final class HookConstants {
        public static final double top_limit = 0;
        public static final double bottom_limit = 180;
        public static final double gear_ratio = 192;//toDo
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
        public static final double inVelocity = 1;
        public static final double outVelocity = 1;
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
        public static final int endGameTime = 120;//120 - 150
        public enum gamePeriod {
            autonomous,
            teleOp,
            endGame
        }
    }

}

