package org.firstinspires.ftc.teamcode.Tests.Utilities;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp
@Config
public class DirectionTest extends LinearOpMode {
    public static double motorPower = .2;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("fl");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("fr");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("bl");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("br");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {
            telemetry.addData("Front Left", frontLeftMotor.getCurrentPosition());
            telemetry.addData("Front Right", frontRightMotor.getCurrentPosition());
            telemetry.addData("Back Left", backLeftMotor.getCurrentPosition());
            telemetry.addData("Back Right", backRightMotor.getCurrentPosition());


            if (gamepad1.x) {
                frontLeftMotor.setPower(.3);
                telemetry.addLine("Running Motor: Front Left");
            } else if (gamepad1.y) {
                frontRightMotor.setPower(.3);

                telemetry.addLine("Running Motor: Front Right");
            } else if (gamepad1.b) {
                backRightMotor.setPower(.3);

                telemetry.addLine("Running Motor: Rear Right");

            } else if (gamepad1.a) {
                backLeftMotor.setPower(.3);
                telemetry.addLine("Running Motor: Rear Left");
            } else {
                frontLeftMotor.setPower(motorPower);
                frontRightMotor.setPower(motorPower);
                backLeftMotor.setPower(motorPower);
                backRightMotor.setPower(motorPower);
                telemetry.addLine("All");
            }

        }

    }
}
