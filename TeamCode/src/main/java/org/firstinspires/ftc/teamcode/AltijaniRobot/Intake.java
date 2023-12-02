package org.firstinspires.ftc.teamcode.AltijaniRobot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Intake extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor intakeMotor = hardwareMap.dcMotor.get("intakeMotor");

        DcMotor outtakeLinearSlideMotor1 = hardwareMap.dcMotor.get("outtakeLinearSlideMotor1");
        DcMotor outtakeLinearSlideMotor2 = hardwareMap.dcMotor.get("outtakeLinearSlideMotor2");

        Servo pixelHolderServo1 = hardwareMap.servo.get("pixelHolderServo1");
        Servo pixelHolderServo2 = hardwareMap.servo.get("pixelHolderServo2");

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            //write code here
        }
    }
}
