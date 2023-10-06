package org.firstinspires.ftc.teamcode.AMDA;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;
import org.firstinspires.ftc.vision.VisionPortal;
@TeleOp
public class VisionProcessor extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
         VisionPortal visionPortal;
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        visionPortal = VisionPortal.easyCreateWithDefaults(webcamName);
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {

        }
    }
}
