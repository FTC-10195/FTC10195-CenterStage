package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.SubSys.Slides;

@TeleOp
public class SlideTest extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
       Slides slide = new Slides(hardwareMap);
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {
            slide.manualControl(gamepad1.dpad_up, gamepad1.dpad_down);
        }
        }
    }

