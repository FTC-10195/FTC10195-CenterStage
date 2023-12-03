package org.firstinspires.ftc.teamcode.Mock_Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Practice_Claw extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

    Servo clawServo = hardwareMap.servo.get("clawServo");
    boolean previoiusX = gamepad1.x;
    int xButtonCounter = 0;

        waitForStart();

        if (isStopRequested()) return;

        while(opModeIsActive()) {

            if (gamepad1.x && !previoiusX) {

                xButtonCounter++;

            }

            if (gamepad1.x && !previoiusX && (xButtonCounter % 2) == 0) {

                clawServo.setPosition(1); //I'm pretty sure servos use -1 to 1

            }

            if (gamepad1.x && !previoiusX && (xButtonCounter % 2) == 1) {

                clawServo.setPosition(-1);

            }

        }
    }
}

