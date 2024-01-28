package org.firstinspires.ftc.teamcode.Tests.Utilities;

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

    public static double le =.5;
    @Override
    public void runOpMode() throws InterruptedException {

        left = hardwareMap.get(Servo.class, "la");
        right = hardwareMap.get(Servo.class, "ra");
        left.setDirection(Servo.Direction.REVERSE);
        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()) {

            left.setPosition(le);
            right.setPosition(le);

        }
    }
}
