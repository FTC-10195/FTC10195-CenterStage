package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SubSys.Arm;
import org.firstinspires.ftc.teamcode.SubSys.Chamber;
import org.firstinspires.ftc.teamcode.SubSys.DropDown;
import org.firstinspires.ftc.teamcode.SubSys.MecanumDrive;
import org.firstinspires.ftc.teamcode.SubSys.SimpleBucket;
import org.firstinspires.ftc.teamcode.SubSys.Slides;

import java.util.ArrayList;

@TeleOp(group = "Match")
@Config
public class MIT extends LinearOpMode {

    public  static  double pos = 0;
    public static double frCurrent = 0;
    public static double brCurrent = 0;
    public static double flCurrent = 0;
    public static double blCurrent = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, telemetry);
        Slides slides = new Slides(hardwareMap);
        DropDown intake = new DropDown(hardwareMap);
        Chamber ber = new Chamber(hardwareMap);
        SimpleBucket bucket = new SimpleBucket(hardwareMap);
        Arm arm = new Arm(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
             frCurrent = drive.;
              brCurrent = 0;
              flCurrent = 0;
              blCurrent = 0;

            drive.robotDrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            slides.manualControl(gamepad2.dpad_up, gamepad2.dpad_down);
            intake.move(gamepad1.dpad_left, gamepad1.dpad_right);
            intake.spin(gamepad1.a, gamepad1.b, gamepad1.y);


            //intake.manualMove(dropPos);
            if (gamepad1.right_trigger > .01) {
                ber.motorsPIN(gamepad1.right_trigger);
                ber.servoRoller(gamepad1.right_trigger);

            } else if (gamepad1.left_trigger > .01) {
                ber.motorsPIN(-gamepad1.left_trigger);
                ber.servoRoller(-gamepad1.left_trigger);
            }
            else {

                ber.motorsPIN(0);
                ber.servoRoller(0);

            }

           // arm.rotate(pos);
          if (gamepad2.dpad_left) {
                arm.rotate(0.05);
           } else if (gamepad2.dpad_right) {
               arm.rotate(.50);
            }

            if (gamepad2.a) {
                bucket.manualMove(0.35);

            } else if (gamepad2.b) {
                bucket.manualMove(.7);

            }

           telemetry.addData("Chamber jam", ber.isJammed());

            telemetry.addData("Drivetrain jam", drive.isJammed());
            telemetry.addData("DropDrown jam", intake.isJammed());
            telemetry.addData("Slides  jam", slides.isJammed());
            telemetry.update();


        }
    }
}
