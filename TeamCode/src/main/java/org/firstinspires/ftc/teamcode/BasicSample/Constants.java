package org.firstinspires.ftc.teamcode.BasicSample;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Constants {
    public static final class DriveTrainConstants {
        public final static boolean RUN_USING_ENCODER = false;
        public final static double ticks_per_revolution =
                MotorConstants.REVThroughBoreEncoder.ticks_per_revolution;

        public final static double WheelDiameter = 0.096;
        public final static double HorizontalOdometryWheelDiameter = 0.05;
        public final static double VerticalOdometryWheelDiameter = 0.06;

        public final static double GearRatio = 19.2;

        public final static double TrackWidth = 0.259;
        public static double MaxVelocity = 1.867873;//1.764102,1.867873//,// Its recommended to put max velocity to 90% of the true value
        public static double MaxAccel = 1.7;
        public static double MaxAnglerVelocity = Math.toRadians(360);//Double.MAX_VALUE;
        public static double MaxAnglerAccel = Math.toRadians(360);

        public final static double Horizontal_odometry_wheel_ticks_to_meters = HorizontalOdometryWheelDiameter * Math.PI / ticks_per_revolution;
        public final static double Vertical_odometry_wheel_ticks_to_meters = VerticalOdometryWheelDiameter * Math.PI / ticks_per_revolution;

        public static double kV = 0.478 ;//0.478//0.45961666666667,0.45058,0.46329,0.46022,0.45780,0.45374 *0.457126
        public static double kStatic = 0.0688 ;//0.0768//0.13219857142857,0.14049,0.12647,0.13603,0.13891,0.14391 *0.137162
        public static double kA = 0.16 ;//0.16//0.034650476190476,0.01453,0.01814,0.01626,0.01100,0.01056 *0.014098
    }
    public static final class LiftConstants {
        public static final int number_of_motors = 3;
        public static final double inverse_motor_gear = 2.89 * 2.89;
        public static final double ticks_per_motor_revolution = MotorConstants.RevHDHexMotor.ticks_per_revolution * inverse_motor_gear;
        public static final double gear = 1.0f;//external gear
        public static final int ticks_per_revolution = (int)(ticks_per_motor_revolution / gear);
        public static final double gear_radios = 0.017;
        public static final double distance_per_revolution = 2 * Math.PI * gear_radios;
        public static final double distance_per_tick = distance_per_revolution / ticks_per_revolution;

        public static double openClaw = 0.9;
        public static double closeClaw = -1;
        public static final double ToLow = 0.6;
        public static final double ToMid = 0.9;
        public static final double ToHigh = 1.2;
        public static final double ThresholdHeight = 0.4;
        public static final double lower_plate_height = 0.135;
        public static final double top_height = 1.26;//0.45;//1.18

        public static final double ClawThreshHold = 0.031;

        public static final DcMotor.RunMode LiftRunMode = DcMotor.RunMode.RUN_WITHOUT_ENCODER; // lift joystick control mode
        static public double meters2ticks(double height) {
            return (height/ Constants.LiftConstants.distance_per_tick);
        }
        static public double ticks2meters(double ticks) {
            return (ticks* Constants.LiftConstants.distance_per_tick);
        }
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


}
