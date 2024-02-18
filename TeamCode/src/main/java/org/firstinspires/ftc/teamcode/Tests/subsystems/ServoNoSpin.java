package org.firstinspires.ftc.teamcode.Tests.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Config
public class ServoNoSpin extends LinearOpMode {
    public static double pos = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        Servo outtake = hardwareMap.get(Servo.class,"lserv" );
        Servo outtake2 = hardwareMap.get(Servo.class, "userv");
        waitForStart();
        if(isStopRequested()) return;
        while (opModeIsActive()) {
            outtake.setPosition(pos);
            outtake2.setPosition(pos);
        }
    }
}
