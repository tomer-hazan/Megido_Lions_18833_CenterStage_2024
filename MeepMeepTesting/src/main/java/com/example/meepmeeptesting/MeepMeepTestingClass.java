package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class MeepMeepTestingClass {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);


        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->drive.trajectorySequenceBuilder(new Pose2d(-36.60, -64.81, Math.toRadians(90.00)))
                        .splineTo(new Vector2d(-37.89, -44.53), Math.toRadians(88.73))
                        .splineTo(new Vector2d(-12.26, -34.94), Math.toRadians(-9.53))
                        .splineTo(new Vector2d(4.15, -35.68), Math.toRadians(2.46))
                        .splineTo(new Vector2d(21.30, -34.94), Math.toRadians(3.20))
                        .splineTo(new Vector2d(53.19, -34.02), Math.toRadians(-1.54))
                        .splineTo(new Vector2d(-36.05, -34.57), Math.toRadians(177.59))
                        .lineTo(new Vector2d(-62.60, 55.04))
                        .lineTo(new Vector2d(-55.22, -32.73))
                        .splineTo(new Vector2d(61.86, -44.71), Math.toRadians(-3.67))
                        .splineTo(new Vector2d(-9.50, -64.26), Math.toRadians(195.32))
                        .build()

                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}