package org.firstinspires.ftc.teamcode.Tests.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.CustomHardware.OwlColorSensor;

@Config
@TeleOp
public class BucketTest extends LinearOpMode {
    public static double pos1 = .1;
    public static double pos2 = .1;


    @Override
    public void runOpMode() throws InterruptedException {
        OwlColorSensor sensor = new OwlColorSensor(hardwareMap, telemetry, "usens", "Upper Sensor");

        Servo lowerServo = hardwareMap.get(Servo.class, "lserv");
        Servo upperServo = hardwareMap.get(Servo.class, "userv");

        double lowerIn = 0;
        double lowerOut = .3;

        double upperIn = 0;
        double upperOut = .2;

        waitForStart();

        if (isStopRequested()) return;
        while (opModeIsActive()) {
            if (gamepad1.dpad_right) {
                lowerServo.setPosition(lowerOut);
            } else if (gamepad1.dpad_left) {
                lowerServo.setPosition(lowerIn);
            } else if (gamepad1.dpad_up) {
                upperServo.setPosition(upperOut);
            } else if (gamepad1.dpad_down) {
                upperServo.setPosition(upperIn);
            }





        }
    }
}
