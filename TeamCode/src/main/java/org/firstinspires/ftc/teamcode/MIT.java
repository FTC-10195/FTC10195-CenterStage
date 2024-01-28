package org.firstinspires.ftc.teamcode;

import android.bluetooth.BluetoothProfile;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.SubSys.Blinkin;
import org.firstinspires.ftc.teamcode.SubSys.MecanumDrive;
import org.firstinspires.ftc.teamcode.SubSys.Slides;

@TeleOp
public class MIT extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, telemetry);
        Slides slides = new Slides(hardwareMap);
        //Declare motors

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            drive.robotDrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            slides.manualControl(gamepad1.dpad_up, gamepad1.dpad_down);

        }
    }
}
