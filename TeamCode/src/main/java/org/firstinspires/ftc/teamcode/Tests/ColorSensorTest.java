package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;


@TeleOp
public class ColorSensorTest extends LinearOpMode {
    RevColorSensorV3 colorSensorV3;

    @Override
    public void runOpMode() throws InterruptedException {
        colorSensorV3 = hardwareMap.get(RevColorSensorV3.class, "colorSensor");
        int red = colorSensorV3.red();
        int blue = colorSensorV3.blue();
        int green = colorSensorV3.green();

        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {
            red = colorSensorV3.red();
            blue = colorSensorV3.blue();
            green = colorSensorV3.green();

            telemetry.update();

            if (red == 0 && blue == 0 && green == 0) {
                telemetry.addData("isPurple", "Yes");
            }

            if (red == 1 && blue == 1 && green == 1) {
                telemetry.addData("isGreen", "Yes");
            }

            if (red == 2 && blue == 2 && green == 2) {
                telemetry.addData("isYellow", "Yes");
            }

        }
    }
}