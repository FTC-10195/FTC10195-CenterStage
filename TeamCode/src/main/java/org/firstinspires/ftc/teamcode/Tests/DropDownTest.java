package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.SubSys.DropDown;

@TeleOp
public class DropDownTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DropDown intake = new DropDown(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {
            intake.move(gamepad1.dpad_down, gamepad1.dpad_up);
            intake.spin(gamepad1.a);

        }
    }
}
