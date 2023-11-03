package org.firstinspires.ftc.teamcode.AMDA;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@Autonomous
public class SimpleApril extends LinearOpMode {
    private AprilTagProcessor aprilTagProcessor;
    private VisionPortal visionPortal;

    WebcamName webcamName;

    private enum Position {
        ONE,
        TWO,
        THREE
    }

    @Override
    public void runOpMode() throws InterruptedException {
        webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();

        visionPortal = VisionPortal.easyCreateWithDefaults(webcamName,
                aprilTagProcessor);


        while (opModeInInit()) {
                List<AprilTagDetection> currentDetections = aprilTagProcessor.getDetections();
                StringBuilder idsFound = new StringBuilder();
                for (AprilTagDetection detection: currentDetections) {
                    idsFound.append(detection.id);
                    idsFound.append(' ');
        }
                telemetry.addData("April Tags", idsFound);
                telemetry.update();



        waitForStart();
            visionPortal.stopStreaming();
            if (isStopRequested()) return;
        while (opModeIsActive()) {
            visionPortal.stopStreaming();
        }

        }
    }
}
