package org.firstinspires.ftc.teamcode.Tests;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

@Config
@TeleOp(name = "thingy")
public class AxonSet extends LinearOpMode {
    Servo left;
    Servo right;

    public static double le;
    @Override
    public void runOpMode() throws InterruptedException {

        left = hardwareMap.get(Servo.class, "leftDropOrange");
        right = hardwareMap.get(Servo.class, "rightDrop");

        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()) {

            left.setPosition(le);
            right.setPosition(1-le);

        }
    }
}
