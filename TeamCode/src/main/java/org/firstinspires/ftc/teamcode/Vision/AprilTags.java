package org.firstinspires.ftc.teamcode.Vision;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class AprilTags extends OpMode {
    private AprilTagProcessor aprilTagProcessor;
    private VisionPortal visionPortal;

    @Override
    public void init() {
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "USB Composite Device");
        aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        visionPortal = VisionPortal.easyCreateWithDefaults(webcamName, aprilTagProcessor);
    }

    @Override
    public void init_loop() {
        List <AprilTagDetection> currentDetections = aprilTagProcessor.getDetections();
        StringBuilder idsFound = new StringBuilder();
        for (AprilTagDetection detection : currentDetections) {
            idsFound.append(detection.id);

            idsFound.append(detection.ftcPose.x);
            idsFound.append(detection.ftcPose.y);
            idsFound.append(detection.ftcPose.z);

            idsFound.append(detection.ftcPose.pitch);
            idsFound.append(detection.ftcPose.roll);
            idsFound.append(detection.ftcPose.yaw);

            idsFound.append(detection.ftcPose.range);
            idsFound.append(detection.ftcPose.bearing);
            idsFound.append(detection.ftcPose.elevation);
            
            idsFound.append(' ');
        }
        telemetry.addData("April Tags", idsFound);
    }

    @Override
    public void start() {
        visionPortal.stopStreaming();
    }

    @Override
    public void loop() {

    }
}
