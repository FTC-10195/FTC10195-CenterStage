package org.firstinspires.ftc.teamcode.Vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@Autonomous(name = "Ateg")
public class AprilTags extends OpMode {
    private AprilTagProcessor aprilTagProcessor;
    private VisionPortal visionPortal;

    @Override
    public void init() {
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "0");
        aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        visionPortal = VisionPortal.easyCreateWithDefaults(webcamName, aprilTagProcessor);
        visionPortal.resumeStreaming();
    }

    @Override
    public void init_loop() {
        List <AprilTagDetection> currentDetections = aprilTagProcessor.getDetections();
        StringBuilder idsFound = new StringBuilder();
        double fieldLength = 144; //inches, change if necessary
        double lowestRange = 1000; //finds apriltag closest to camera


        for (AprilTagDetection detection : currentDetections) {
            idsFound.append("ID: " + detection.id + ";\n");

            idsFound.append("X: " + detection.ftcPose.x + ";\n");
            idsFound.append("Y: " + detection.ftcPose.y + ";\n");
            idsFound.append("Z: " + detection.ftcPose.z + ";\n");

            idsFound.append("Pitch: " + detection.ftcPose.pitch + ";\n");
            idsFound.append("Roll: " + detection.ftcPose.roll + ";\n");
            idsFound.append("Yaw: " + detection.ftcPose.yaw + ";\n");

            idsFound.append("Range: " + detection.ftcPose.range + ";\n");
            idsFound.append("Bearing: " + detection.ftcPose.bearing + ";\n");
            idsFound.append("Elevation: " + detection.ftcPose.elevation + ";\n");
            
            idsFound.append(' ');

            if (detection.id == 5) {
                double robotPositionX = 60.25 - detection.ftcPose.x;
                double robotPositionY = -35.41 - detection.ftcPose.y;
                double robotPositionZ = 4 - detection.ftcPose.z;
                idsFound.append(robotPositionX);
                idsFound.append(robotPositionY);
                idsFound.append(robotPositionZ);
                idsFound.append(' ');
            }
        }

        telemetry.addData("April Tags", idsFound);
    }

    @Override
    public void start() {
        //visionPortal.stopStreaming();
    }

    @Override
    public void loop() {

    }
}
