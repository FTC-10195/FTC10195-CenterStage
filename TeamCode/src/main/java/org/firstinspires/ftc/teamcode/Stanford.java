package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.SubSys.Blinkin;
import org.firstinspires.ftc.teamcode.SubSys.MecanumDrive;
import org.firstinspires.ftc.teamcode.SubSys.PaperAirplane;
import org.firstinspires.ftc.teamcode.Tests.PaperAirplaneTest;
@TeleOp
public class Stanford extends LinearOpMode {

    MecanumDrive drive;
  //  PaperAirplane railgun;

    @Override
    public void runOpMode() throws InterruptedException {

        // rightBack = hardwareMap.get(DcMotorEx.class, "rb");
        /// rightFront = hardwareMap.get(DcMotorEx.class, "rf");
        // leftFront = hardwareMap.get(DcMotorEx.class, "lf");
       //  leftBack = hardwareMap.get(DcMotorEx.class, "lb");

      //  DcMotorEx intake = hardwareMap.get(DcMotorEx.class, "in");
      //  DcMotorEx rs = hardwareMap.get(DcMotorEx.class, "rs");
      //  DcMotorEx ls = hardwareMap.get(DcMotorEx.class, "ls");
      //  DcMotorEx chain = hardwareMap.get(DcMotorEx.class, "ch");

      //  rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      //  rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      //  leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       // leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      //  intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      ///  rs.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      //  ls.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      //  chain.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

       // rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
       // leftFront.setDirection(DcMotorSimple.Direction.REVERSE);

         drive = new MecanumDrive(hardwareMap, telemetry);

      //   railgun = new PaperAirplane(hardwareMap);
        // Blinkin leds = new Blinkin(hardwareMap);
         //timer = new ElapsedTime();
        CRServo servo = hardwareMap.get(CRServo.class, "roll");
        DcMotor spin = hardwareMap.get(DcMotor.class, "in");
        DcMotor motor = hardwareMap.get(DcMotorEx.class, "sp");
        motor.setDirection(DcMotorSimple.Direction.REVERSE);

        Servo left;
        Servo right;
        left = hardwareMap.get(Servo.class, "ld");
        right = hardwareMap.get(Servo.class, "rd");
        double le =.5;
        spin.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()) {
           // double leftY = gamepad1.left_stick_y;
          //  double leftX = gamepad1.left_stick_x;
          //  double rightX = gamepad1.right_stick_x;
            //TODO fix strafe
            drive.robotDrive(-gamepad1.left_stick_y, gamepad1.left_stick_x, -gamepad1.right_stick_x);
            servo.setPower(1);
            spin.setPower(1);
            left.setPosition(le);
            right.setPosition(1-le);
            motor.setPower(1);

            //   telemetry.addData("Left Y", leftY);
           // telemetry.addData("Left X", leftX);
          //  telemetry.addData("Right x", rightX);
          //  telemetry.update();
        }
    }

}
