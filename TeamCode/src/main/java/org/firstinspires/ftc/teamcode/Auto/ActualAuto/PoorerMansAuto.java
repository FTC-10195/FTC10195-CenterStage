package org.firstinspires.ftc.teamcode.Auto.ActualAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.SubSys.MecanumDrive;

@Autonomous(name = "Blue Backboard Park To Left")
public class PoorerMansAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, telemetry);
        waitForStart();
        if(isStopRequested()) return;

        drive.strafeLeft(500);
        drive.forward(drive.inchToTick(6));

        if(drive.isJammed()) {
            return;
        }




    }
}
