package org.firstinspires.ftc.teamcode.Auto.ActualAuto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.SubSys.Chamber;
import org.firstinspires.ftc.teamcode.SubSys.DropDown;
import org.firstinspires.ftc.teamcode.SubSys.MecanumDrive;
import org.firstinspires.ftc.teamcode.Vision.LocationID;
import org.firstinspires.ftc.vision.VisionPortal;

@TeleOp
public class PoorMansAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive drive = new MecanumDrive(hardwareMap, telemetry);
        DropDown drop = new DropDown(hardwareMap);
        LocationID id = new LocationID();

        VisionPortal portal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), id);



        telemetry.clear();

        while(opModeInInit()) {
            telemetry.addData("Zone", id.getSelection());
            telemetry.update();

        }

        LocationID.Selected select = id.getSelection();
        waitForStart();
        if(isStopRequested()) return;
        drive.forward(inchToTick(12));
        drive.strafeLeft(-250);
      //  sleep(300);
       // drive.strafeRight(250);
        drop.move(true, false);
        sleep(200);
        drop.move(false, true);
    }

    public int inchToTick(int inches) {
        Double inpertick = .0325;
        Double ticks = ((inches * 10000) / (inpertick * 10000));
        return(-ticks.intValue());
    }
}
