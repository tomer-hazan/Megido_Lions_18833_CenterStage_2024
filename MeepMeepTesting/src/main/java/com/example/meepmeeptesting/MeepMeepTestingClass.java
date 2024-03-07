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
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 13)
                .followTrajectorySequence(drive ->drive.trajectorySequenceBuilder(new Pose2d(14.98, -70.34, Math.toRadians(90.00)))
                                .splineTo(new Vector2d(38.97, -35.20), Math.toRadians(0))
                                .splineTo(new Vector2d(23.69, -11.80), Math.toRadians(0))
                                .lineTo(new Vector2d(-66.34, -12.36))
                                .lineTo(new Vector2d(23.50, -11.98))
                                .lineToConstantHeading(new Vector2d(39.00, -35.68))
                                .build());









        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}