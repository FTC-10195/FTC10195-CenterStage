package org.firstinspires.ftc.teamcode.Tests.Utilities;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@TeleOp
public class MotorSpin extends LinearOpMode {

    public static String config = "spinn";
    double current = 0;

    @Override
    public void runOpMode() throws InterruptedException {


        DcMotorEx spin = hardwareMap.get(DcMotorEx.class, config);
        spin.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            spin.setPower(1);
            telemetry.addData("Current", spin.getCurrent(CurrentUnit.AMPS));
            telemetry.update();

        }
    }
}
