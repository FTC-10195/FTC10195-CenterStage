package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp
public class thing extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotorEx main = (DcMotorEx) hardwareMap.get("test");
        boolean previousA = gamepad1.a;
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {
            if (gamepad1.a && !previousA) {
                main.setPower(1);
                sleep(2000);
                main.setPower(0);

            }
            previousA = gamepad1.a;

        }

    }
}
