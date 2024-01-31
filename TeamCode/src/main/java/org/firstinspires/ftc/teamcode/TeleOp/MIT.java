package org.firstinspires.ftc.teamcode.TeleOp;

import android.bluetooth.BluetoothProfile;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.teamcode.SubSys.Arm;
import org.firstinspires.ftc.teamcode.SubSys.Blinkin;
import org.firstinspires.ftc.teamcode.SubSys.Bucket;
import org.firstinspires.ftc.teamcode.SubSys.Chamber;
import org.firstinspires.ftc.teamcode.SubSys.DropDown;
import org.firstinspires.ftc.teamcode.SubSys.MecanumDrive;
import org.firstinspires.ftc.teamcode.SubSys.SimpleBucket;
import org.firstinspires.ftc.teamcode.SubSys.Slides;

@TeleOp
@Config
public class MIT extends LinearOpMode {
    public static double bucketPos = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, telemetry);
        Slides slides = new Slides(hardwareMap);
        DropDown intake = new DropDown(hardwareMap);
        Chamber ber = new Chamber(hardwareMap);
       SimpleBucket bucket = new SimpleBucket(hardwareMap);
        Arm arm = new Arm(hardwareMap);
        //Declare motors

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {


            drive.robotDrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
           // slides.manualControl( gamepad1.right_trigger > .01, gamepad1.left_trigger > .01);

            slides.manualControl(gamepad1.dpad_up, gamepad1.dpad_down);
            intake.move(gamepad1.dpad_left, gamepad1.dpad_right);
            intake.spin(gamepad1.a);
            ber.motorsPIN(gamepad1.right_trigger);
            ber.servoRoller(gamepad1.right_trigger);
            telemetry.addData("Trigger", gamepad1.right_trigger);
            telemetry.update();
            arm.rotate(bucketPos);


           if(gamepad1.b) {

                bucket.intakeMode();
            }
            else if(gamepad1.left_bumper) {
                bucket.outtakeUpper();
            }
            else if(gamepad1.right_bumper) {
                bucket.outtakeLower();
            }





        }
    }
}
