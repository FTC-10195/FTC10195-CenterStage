package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MecanumTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //Declare motors
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("fl");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("fr");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("bl");
        DcMotor backRightMotor = hardwareMap.dcMotor.get(("br"));

        //Reverse right motors for proper Mecanum movement
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1; //counteracts imperfect strafing
            double rx = gamepad1.right_stick_x;

            //denominator is largest motor power to ensure power ratio between motors is mantained
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x -rx) / denominator;

            //set motor power values
            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);
        }
    }
}
