package org.firstinspires.ftc.teamcode.Auto.ActualAuto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Vision.LocationID;

@Autonomous(name = "Autonomous")
public class Auto extends LinearOpMode {
    private LocationID locationID;

    Robot bot = new Robot(hardwareMap);

    private enum Side {
        STACK,
        BACKBOARD
    }

    Side side = Side.STACK;


    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        while (opModeInInit()) {

            telemetry.addLine("D-pad up for blue, d-pad down for red");

            if (gamepad1.dpad_up) {
                bot.setColor(0);
            } else if (gamepad1.dpad_down) {
                bot.setColor(1);
            }
            telemetry.addData("Current Config", bot.getColor());

            telemetry.addLine("D-pad left for stack, d-pad right for backboard");
            if (gamepad1.dpad_left) {
                side = Side.STACK;
            } else if (gamepad1.dpad_right) {
                side = Side.BACKBOARD;
            }
            telemetry.addData("Current Config", side);


            telemetry.update();
        }
        if (isStopRequested()) return;

        while (opModeIsActive()) {


        }
    }
}
