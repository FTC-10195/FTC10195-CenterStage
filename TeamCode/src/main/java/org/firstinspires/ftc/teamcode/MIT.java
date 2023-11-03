package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.SubSys.Blinkin;
import org.firstinspires.ftc.teamcode.SubSys.MecanumDrive;

@TeleOp
public class MIT extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //Declare motors
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            mecanumDrive.robotDrive(-gamepad1.left_stick_y, gamepad1.left_stick_x*1.1, gamepad1.right_stick_x);

        }
    }
}
