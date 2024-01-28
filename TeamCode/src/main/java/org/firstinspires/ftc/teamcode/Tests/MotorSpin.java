package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MotorSpin extends LinearOpMode  {

    public static String config = "in";
    @Override
    public void runOpMode() throws InterruptedException {


        DcMotor spin = hardwareMap.get(DcMotor.class, config);
        spin.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()) {

            spin.setPower(1);


        }
    }
}
