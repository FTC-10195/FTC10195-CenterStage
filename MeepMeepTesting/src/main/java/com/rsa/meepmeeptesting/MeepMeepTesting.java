package com.rsa.meepmeeptesting;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    static Vector2d blueStack = new Vector2d(-37.2, 58.2);

    public static Action farWall(RoadRunnerBotEntity bot) {
         return bot.getDrive().actionBuilder(new Pose2d(-36, -64, Math.toRadians(270)))
                 .lineToLinearHeading(new Pose2d(-42, -34, Math.toRadians(120)))
                 .lineToSplineHeading(new Pose2d(-48, -40, Math.toRadians(180)))
                 .splineToConstantHeading(new Vector2d(-60, -36), Math.toRadians(180))

                 .setReversed(true)
                 .waitSeconds(0.5)
                 .splineToConstantHeading(new Vector2d(-24, -60), Math.toRadians(0))
                 .lineTo(new Vector2d(12, -60))
                 .splineToConstantHeading(new Vector2d(48, -30), Math.toRadians(45))
                 .waitSeconds(0.5)
                 .lineTo(new Vector2d(47, -31))
                 .splineToConstantHeading(new Vector2d(0, -60), Math.toRadians(180))
                 .lineTo(new Vector2d(-24, -60))
                 .splineToConstantHeading(new Vector2d(-60, -36), Math.toRadians(180))

                 .setReversed(true)
                 .waitSeconds(0.5)
                 .splineToConstantHeading(new Vector2d(-24, -60), Math.toRadians(0))
                 .lineTo(new Vector2d(12, -60))
                 .splineToConstantHeading(new Vector2d(48, -42), Math.toRadians(45))
                 .waitSeconds(0.5)
                 .lineTo(new Vector2d(47, -43))
                 .splineToConstantHeading(new Vector2d(0, -60), Math.toRadians(180))
                 .lineTo(new Vector2d(-24, -60))
                 .splineToConstantHeading(new Vector2d(-60, -36), Math.toRadians(180))

                 .setReversed(true)
                 .waitSeconds(0.5)
                 .splineToConstantHeading(new Vector2d(-24, -60), Math.toRadians(0))
                 .lineTo(new Vector2d(12, -60))
                 .splineToConstantHeading(new Vector2d(48, -42), Math.toRadians(45))
                 .waitSeconds(0.5)

                 .strafeLeft(18)

                 .build();

    }
    public static void main(String[] args) {

        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(68, 30, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        RoadRunnerBotEntity bot2 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(68, 30, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(farWall(myBot));


      //  bot2.runAction(myBot.getDrive().actionBuilder(new Pose2d(59.6, 35.6, 0))
            //    .splineTo(new Vector2d(-60, -56), Math.PI/2)
            //    .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}