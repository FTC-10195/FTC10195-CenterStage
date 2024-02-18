package org.firstinspires.ftc.teamcode.Tests.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.SubSys.Arm;

@TeleOp
@Config
public class SlideRTP extends LinearOpMode {
    DcMotorEx leftSlide;
    DcMotorEx rightSlide;


    public static int target =  0;
    @Override
    public void runOpMode() throws InterruptedException {

        leftSlide = hardwareMap.get(DcMotorEx.class, "ls");
        rightSlide = hardwareMap.get(DcMotorEx.class, "rs");

        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftSlide.setTargetPosition(target);
        rightSlide.setTargetPosition(target);

        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FtcDashboard dashboard=  FtcDashboard.getInstance();
        TelemetryPacket packet = new TelemetryPacket();
    Arm arm = new Arm(hardwareMap);

        waitForStart();
        if(isStopRequested()) return;

        while(opModeIsActive()) {

            arm.rotate(0);
            leftSlide.setTargetPosition(target);
            rightSlide.setTargetPosition(target);

            packet.put("Left pos", leftSlide.getCurrentPosition());
            packet.put("right pos", rightSlide.getCurrentPosition());

            dashboard.sendTelemetryPacket(packet);
            leftSlide.setPower(1);
            rightSlide.setPower(1);
        }
        }
    }

