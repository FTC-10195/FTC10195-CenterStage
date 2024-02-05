package org.firstinspires.ftc.teamcode.Auto.ActualAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.SubSys.Chamber;
import org.firstinspires.ftc.teamcode.SubSys.DropDown;
import org.firstinspires.ftc.teamcode.SubSys.MecanumDrive;
import org.firstinspires.ftc.teamcode.Vision.LocationID;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous
public class PoorMansAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive drive = new MecanumDrive(hardwareMap, telemetry);
        DropDown drop = new DropDown(hardwareMap);
        LocationID id = new LocationID();

        VisionPortal portal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), id);
        Chamber ber = new Chamber(hardwareMap);
        ElapsedTime time = new ElapsedTime();
        telemetry.clear();

        while (opModeInInit()) {
            telemetry.addData("Zone", id.getSelection());
            telemetry.update();

        }

        LocationID.Selected select = id.getSelection();
        waitForStart();
        if (isStopRequested()) return;
        time.startTime();
        if(select == LocationID.Selected.LEFT) {
            drive.forward(inchToTick(24));
            drive.strafeLeft(-250);
            time.reset();

            while(time.seconds() < 6) {
                ber.motorsPIN(-.2);
                ber.servoRoller(-1);
            }



        }
        else if(select == LocationID.Selected.RIGHT) {
            drive.forward(inchToTick(24));
            drive.strafeLeft(250);
            time.reset();
            while(time.seconds() < 6) {
                ber.motorsPIN(-.2);
                ber.servoRoller(-1);
            }

        }
        else if(select == LocationID.Selected.MIDDLE) {
            drive.forward(inchToTick(24));
            time.reset();
            while(time.seconds() < 6) {
                ber.motorsPIN(-.2);
                ber.servoRoller(-1);
            }

        }


        //  sleep(300);
        // drive.strafeRight(250);

    }

    public int inchToTick(int inches) {
        Double inpertick = .0325;
        Double ticks = ((inches * 10000) / (inpertick * 10000));
        return (-ticks.intValue());
    }
}
