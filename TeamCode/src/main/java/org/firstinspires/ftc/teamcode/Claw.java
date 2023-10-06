package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Claw extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        Servo pixelDoorServo1 = hardwareMap.servo.get("pixelDoorServo1");
        Servo pixelDoorServo2 = hardwareMap.servo.get("pixelDoorServo1");

        Servo pixelOrientationServo1 = hardwareMap.servo.get("pixelDoorServo1"); //Horizontal?
        Servo pixelOrientationServo2 = hardwareMap.servo.get("pixelDoorServo1"); //70 Degrees?

        waitForStart();

        if (isStopRequested()) return;

        while(opModeIsActive()) {

            //write

        }
    }

}

