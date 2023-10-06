package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


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

        waitForStart();

        if (isStopRequested()) return;

        while(opModeIsActive()) {

            float leftStickYPosition = gamepad1.left_stick_y;
            float leftStickXPosition = gamepad1.left_stick_x;
            float rightStickXPosition = gamepad1.right_stick_x;

            frontLeftMotor.setPower(leftStickXPosition + rightStickXPosition - leftStickYPosition);
            frontRightMotor.setPower(leftStickXPosition - rightStickXPosition - leftStickYPosition);
            backLeftMotor.setPower(leftStickXPosition + rightStickXPosition - leftStickYPosition);
            backRightMotor.setPower(leftStickXPosition - rightStickXPosition - leftStickYPosition);

            }


        }











    }