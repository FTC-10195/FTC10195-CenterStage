package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Drive_Train extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        DcMotor intakeMotor = hardwareMap.dcMotor.get("intakeMotor");

        DcMotor outtakeLinearSlideMotor1 = hardwareMap.dcMotor.get("outtakeLinearSlideMotor1");
        DcMotor outtakeLinearSlideMotor2 = hardwareMap.dcMotor.get("outtakeLinearSlideMotor2");

        Servo pixelHolderServo1 = hardwareMap.servo.get("pixelHolderServo1");
        Servo pixelHolderServo2 = hardwareMap.servo.get("pixelHolderServo2");

        Servo pixelDoorServo1 = hardwareMap.servo.get("pixelDoorServo1");
        Servo pixelDoorServo2 = hardwareMap.servo.get("pixelDoorServo1");

        Servo pixelOrientationServo1 = hardwareMap.servo.get("pixelDoorServo1"); //Horizontal?
        Servo pixelOrientationServo2 = hardwareMap.servo.get("pixelDoorServo1"); //70 Degrees?

        waitForStart();

        if (isStopRequested()) return;

        while(opModeIsActive()) {

            float leftStickYPosition = gamepad1.left_stick_y;

            if (leftStickYPosition == 1) {
                frontLeftMotor.setPower(1);
                frontRightMotor.setPower(1);
                backLeftMotor.setPower(1);
                backRightMotor.setPower(1);
            }

            if (leftStickYPosition == -1) {
                frontLeftMotor.setPower(-1);
                frontRightMotor.setPower(-1);
                backLeftMotor.setPower(-1);
                backRightMotor.setPower(-1);
            }


        }











    }
}
