package org.firstinspires.ftc.teamcode.Mock_Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Practice_Claw extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

    Servo clawServo = hardwareMap.servo.get("clawServo");
    int xButtonCounter = 0;
    boolean previousX = false;

        waitForStart();

        if (isStopRequested()) return;

        while(opModeIsActive()) {

            if (gamepad1.x && !previousX) {

                xButtonCounter++;

            }

            if (gamepad1.x && !previousX && (xButtonCounter % 2) == 1) {

                clawServo.setPosition(1);

            }

            if (gamepad1.x && !previousX && (xButtonCounter % 2) == 0) {

                clawServo.setPosition(0);

            }

            previousX = gamepad1.x;

        }
    }
}

