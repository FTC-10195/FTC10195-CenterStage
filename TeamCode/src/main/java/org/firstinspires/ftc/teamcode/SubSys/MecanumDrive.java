package org.firstinspires.ftc.teamcode.SubSys;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.CustomHardware.OwlMotor;

public class MecanumDrive extends SubsystemBase {
    OwlMotor frontLeftMotor;
    OwlMotor frontRightMotor;
    OwlMotor backLeftMotor;
    OwlMotor backRightMotor;


    public MecanumDrive(HardwareMap hwmap, Telemetry telemetry) {
        frontLeftMotor = new OwlMotor(hwmap, telemetry, "fl", "Front left motor",
                DcMotor.RunMode.RUN_WITHOUT_ENCODER,
                DcMotorSimple.Direction.REVERSE);
        frontRightMotor = new OwlMotor(hwmap, telemetry, "fr", "Front right motor",
                DcMotor.RunMode.RUN_WITHOUT_ENCODER,
                DcMotorSimple.Direction.FORWARD);
        backLeftMotor = new OwlMotor(hwmap, telemetry, "bl", "Back left motor",
                DcMotor.RunMode.RUN_WITHOUT_ENCODER,
                DcMotorSimple.Direction.REVERSE);
        backRightMotor = new OwlMotor(hwmap, telemetry, "br", "Back right motor",
                DcMotor.RunMode.RUN_WITHOUT_ENCODER,
                DcMotorSimple.Direction.FORWARD);

    }

    public void robotDrive(double left_stick_y, double left_stick_x, double right_stick_x) {

        //denominator is largest motor power to ensure power ratio between motors is mantained
        double denominator = Math.max(Math.abs(left_stick_y) + Math.abs(left_stick_x) + Math.abs(right_stick_x), 1);
        double frontLeftPower = (left_stick_y + left_stick_x + right_stick_x) / denominator;
        double backLeftPower = (left_stick_y - left_stick_x + right_stick_x) / denominator;
        double frontRightPower = (left_stick_y - left_stick_x - right_stick_x) / denominator;
        double backRightPower = (left_stick_y + left_stick_x - right_stick_x) / denominator;

        //set motor power values
        frontLeftMotor.returnDevice().setPower(frontLeftPower);
        backLeftMotor.returnDevice().setPower(backLeftPower);
        frontRightMotor.returnDevice().setPower(frontRightPower);
        backRightMotor.returnDevice().setPower(backRightPower);


    }
    public boolean isSystemJammed() {
        return (backLeftMotor.currentOverThreshold() || backRightMotor.currentOverThreshold() || frontLeftMotor.currentOverThreshold() || frontRightMotor.currentOverThreshold());
    }








}




