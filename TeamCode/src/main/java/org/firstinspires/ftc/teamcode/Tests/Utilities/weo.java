package org.firstinspires.ftc.teamcode.Tests.Utilities;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class weo extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo lowerServo = hardwareMap.get(Servo.class, "lserv");
        Servo upperServo = hardwareMap.get(Servo.class, "userv");
        Servo left = hardwareMap.get(Servo.class, "la");
        Servo right = hardwareMap.get(Servo.class, "ra");

        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {

            if (gamepad1.dpad_down) {
                lowerServo.setPosition(1);
            } else if (gamepad1.dpad_down) {
                upperServo.setPosition(1);

            } else if (gamepad1.dpad_down) {
                left.setPosition(1);

            } else if (gamepad1.dpad_down) {
                right.setPosition(1);

            }

        }

    }
}

