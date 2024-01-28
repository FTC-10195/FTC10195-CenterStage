package org.firstinspires.ftc.teamcode.SubSys;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MecanumDrive implements Subsystem {
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    Telemetry telemetry;


    public MecanumDrive(HardwareMap hwmap, Telemetry telemetry) {
        frontLeftMotor = hwmap.dcMotor.get("fl");
        frontRightMotor = hwmap.dcMotor.get("fr");
        backLeftMotor = hwmap.dcMotor.get("bl");
        backRightMotor = hwmap.dcMotor.get("br");
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.telemetry = telemetry;

    }
    public void robotDrive(double y, double x, double rx) {

        //denominator is largest motor power to ensure power ratio between motors is mantained
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x -rx) / denominator;

        //set motor power values
        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);



        telemetry.addData("FL", frontLeftMotor.getCurrentPosition());
        telemetry.addData("BL", backLeftMotor.getCurrentPosition());
        telemetry.addData("BR", backRightMotor.getCurrentPosition());
        telemetry.addData("FR", frontRightMotor.getCurrentPosition());
        telemetry.update();



    }

}
