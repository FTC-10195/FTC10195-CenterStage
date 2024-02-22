package org.firstinspires.ftc.teamcode.SubSys.CommandSubsys;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.CustomHardware.OwlMotor;

public class MecanumDrive extends SubsystemBase {

    DcMotorEx frontLeftMotor;
        DcMotorEx frontRightMotor;
        DcMotorEx backLeftMotor;
        DcMotorEx backRightMotor;
        Telemetry telemetry;
    
    public MecanumDrive(HardwareMap hwmap, Telemetry telemetry) {
        frontLeftMotor = hwmap.get(DcMotorEx.class, "fl");
        frontRightMotor = hwmap.get(DcMotorEx.class, "fr");
        backLeftMotor = hwmap.get(DcMotorEx.class, "bl");
        backRightMotor = hwmap.get(DcMotorEx.class, "br");
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

    public void robotDrive(double left_stick_y, double left_stick_x, double right_stick_x) {

        //denominator is largest motor power to ensure power ratio between motors is mantained
        double denominator = Math.max(Math.abs(left_stick_y) + Math.abs(left_stick_x) + Math.abs(right_stick_x), 1);
        double frontLeftPower = (-left_stick_y + left_stick_x + right_stick_x) / denominator;
        double backLeftPower = (-left_stick_y - left_stick_x + right_stick_x) / denominator;
        double frontRightPower = (-left_stick_y - left_stick_x - right_stick_x) / denominator;
        double backRightPower = (-left_stick_y + left_stick_x - right_stick_x) / denominator;

        //set motor power values
        frontLeftMotor.setPower(frontLeftPower); // This is oriented by looking straight on at the robot, so this would be the wheel closest to the slides on the control hub side
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);


    }

    public void rotate(int angle) {

    }
    /*
    public boolean isSystemJammed() {
        return (backLeftMotor.currentOverThreshold() || backRightMotor.currentOverThreshold() || frontLeftMotor.currentOverThreshold() || frontRightMotor.currentOverThreshold());
    }
    */









}




