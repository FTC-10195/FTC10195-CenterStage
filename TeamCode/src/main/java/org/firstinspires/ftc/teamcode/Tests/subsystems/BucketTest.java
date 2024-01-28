package org.firstinspires.ftc.teamcode.Tests.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.SubSys.Blinkin;
import org.firstinspires.ftc.teamcode.SubSys.Bucket;

@Config
@TeleOp
public class BucketTest extends LinearOpMode {
    public static double pos1 = .1;
    public static double pos2 = .1;
    enum COLOR {
        NOTHING,
        WHITE,
        PURPLE,
        GREEN,
        YELLOW
    }

    @Override
    public void runOpMode() throws InterruptedException {
        Blinkin lights = new Blinkin(hardwareMap);
        RevColorSensorV3 lowerSensor = hardwareMap.get(RevColorSensorV3.class, "lsens");
        RevColorSensorV3 upperSensor = hardwareMap.get(RevColorSensorV3.class, "usens");

        Servo lowerServo = hardwareMap.get(Servo.class, "lserv");
        Servo upperServo = hardwareMap.get(Servo.class, "userv");

        double lowerIn = 0;
        double lowerOut = .3;

        double upperIn = 0;
        double upperOut = .2;

        COLOR color = COLOR.NOTHING;
        waitForStart();

        if (isStopRequested()) return;
        while (opModeIsActive()) {

            if (gamepad1.dpad_right) {
                lowerServo.setPosition(lowerOut);
            }
            if (gamepad1.dpad_left) {
                lowerServo.setPosition(lowerIn);
            }
            if (gamepad1.dpad_up) {
                upperServo.setPosition(upperOut);
            }
            if (gamepad1.dpad_down) {
                upperServo.setPosition(upperIn);
            }

            // Read color and distance values for lower sensor
            int lowerRed = lowerSensor.red();
            int lowerBlue = lowerSensor.blue();
            int lowerGreen = lowerSensor.green();
            int lowerAlpha = lowerSensor.alpha();

            double lowerDist = lowerSensor.getDistance(DistanceUnit.MM);

            // Read color and distance values for upper sensor
            int upperRed = upperSensor.red();
            int upperBlue = upperSensor.blue();
            int upperGreen = upperSensor.green();
            int upperAlpha = upperSensor.alpha();
            double upperDist = upperSensor.getDistance(DistanceUnit.MM);


            if(upperSensor.getDistance(DistanceUnit.MM) > 30) {
                color = COLOR.NOTHING;
            }
            else {
                upperServo.setPosition(upperIn);
                if (upperAlpha > 2000 && upperBlue > 2000 && upperRed > 2000 && upperGreen > 2000 && upperAlpha > 2000) {
                    color = COLOR.WHITE;
                } else if (upperBlue > 1000 && upperGreen < 3000) {
                    color = COLOR.PURPLE;
                } else if (upperBlue < 1000 && upperRed < 1000 && upperGreen > 1000) {
                    color = COLOR.GREEN;
                } else if (upperBlue < 1000 && upperRed > 1000 && upperGreen > 1000 && upperAlpha < 2900) {
                    color = COLOR.YELLOW;
                }
            }



            // Display telemetry for lower sensor
            telemetry.addData("Lower Sensor - Red", lowerRed);
            telemetry.addData("Lower Sensor - Blue", lowerBlue);
            telemetry.addData("Lower Sensor - Green", lowerGreen);
            telemetry.addData("Lower Sensor - Alpha", lowerAlpha);
            telemetry.addData("Lower Sensor - Distance (mm)", lowerDist);

            // Display telemetry for upper sensor
            telemetry.addData("Upper Sensor - Red", upperRed);
            telemetry.addData("Upper Sensor - Blue", upperBlue);
            telemetry.addData("Upper Sensor - Green", upperGreen);
            telemetry.addData("Upper Sensor - Alpha", upperAlpha);
            telemetry.addData("Upper Sensor - Distance (mm)", upperDist);

            telemetry.addData("Current color", color);


            telemetry.update();

        }
    }
}
