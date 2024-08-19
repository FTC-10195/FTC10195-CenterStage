package org.firstinspires.ftc.teamcode.Vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name = "TestingVisionTest")
public class TestingVisionTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        LocationID id = new LocationID();

        VisionPortal portal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), id);

        telemetry.clear();

        while (opModeInInit()) {
            telemetry.addData("Zone", id.getSelection());
            telemetry.update();

        }
    }
}
