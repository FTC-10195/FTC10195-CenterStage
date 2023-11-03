package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.SubSys.Slides;

public class SlideTest extends LinearOpMode {
    public void runOpMode() throws InterruptedException {

        Slides slide = new Slides(hardwareMap);
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
        slide.setPid(gamepad1.b);
        slide.manualControl(gamepad1.y, gamepad1.a);
        if(gamepad1.dpad_down) {
            slide.setHeight(100);
        }
        }
        }
    }

