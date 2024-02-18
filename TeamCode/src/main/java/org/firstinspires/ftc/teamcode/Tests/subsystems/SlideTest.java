package org.firstinspires.ftc.teamcode.Tests.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SubSys.CommandSubsys.Slides;

@TeleOp
@Config
public class SlideTest extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        Slides slide = new Slides(hardwareMap, telemetry);
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {
            slide.manualControl(gamepad1.dpad_up, gamepad1.dpad_down);
            telemetry.update();
        }
    }
}

