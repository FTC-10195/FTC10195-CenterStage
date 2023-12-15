package org.firstinspires.ftc.teamcode.Vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@Autonomous
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
        double fieldLength = 144; //inches, change if necessary
        double lowestRange = 1000; //finds apriltag closest to camera


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

            if (detection.ftcPose.range < lowestRange) {
                lowestRange = detection.ftcPose.range;

            }
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
