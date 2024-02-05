package org.firstinspires.ftc.teamcode.SubSys;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class MecanumDrive implements Subsystem {
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
        double frontLeftPower = (left_stick_y + left_stick_x + right_stick_x) / denominator;
        double backLeftPower = (left_stick_y - left_stick_x + right_stick_x) / denominator;
        double frontRightPower = (left_stick_y - left_stick_x - right_stick_x) / denominator;
        double backRightPower = (left_stick_y + left_stick_x - right_stick_x) / denominator;

        //set motor power values
        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);


     //   telemetry.addData("FL", frontLeftMotor.getCurrentPosition());
     //   telemetry.addData("BL", backLeftMotor.getCurrentPosition());
      //  telemetry.addData("BR", backRightMotor.getCurrentPosition());
      //  telemetry.addData("FR", frontRightMotor.getCurrentPosition());
     //   telemetry.update();
    }

    public void forward(int distance) {
//reset encoder, go forward x amount of ticks at .1 speed
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        backRightMotor.setTargetPosition(distance);
        backLeftMotor.setTargetPosition(distance);
        frontRightMotor.setTargetPosition(distance);
        frontLeftMotor.setTargetPosition(distance);

        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        backRightMotor.setPower(.1);
        backLeftMotor.setPower(.1);
        frontRightMotor.setPower(.1);
        frontLeftMotor.setPower(.1);

        //while loop exists so that the program does not stop the motors while the motors are still running, blocking code
        while (backLeftMotor.isBusy() || backRightMotor.isBusy() || frontLeftMotor.isBusy() || frontRightMotor.isBusy()) {
        }
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);


    }
    public boolean isJammed() {
        return (backLeftMotor.getCurrent(CurrentUnit.AMPS) > 10 || backRightMotor.getCurrent(CurrentUnit.AMPS)  > 10 || frontRightMotor.getCurrent(CurrentUnit.AMPS)  > 10|| frontLeftMotor.getCurrent(CurrentUnit.AMPS)  > 10|| backLeftMotor.getCurrent(CurrentUnit.AMPS)  > 10);
    }
    public int inchToTick(int inches) {
        Double inpertick = .0325;
        Double ticks = ((inches * 10000) / (inpertick * 10000));
        return (-ticks.intValue());
    }

    //literally same as before, just add some negatives bc of mecanum equations
    public void strafeRight(int distance) {

        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backRightMotor.setTargetPosition(-distance);
        backLeftMotor.setTargetPosition(distance);
        frontRightMotor.setTargetPosition(-distance);
        frontLeftMotor.setTargetPosition(distance);

        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//        All of these values are set to .1, but all of these values in strafeLeft() are set to 1
        backRightMotor.setPower(.1);
        backLeftMotor.setPower(.1);
        frontRightMotor.setPower(.1);
        frontLeftMotor.setPower(.1);
        while (backLeftMotor.isBusy() && backRightMotor.isBusy() && frontLeftMotor.isBusy() && frontRightMotor.isBusy()) {
        }
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
    }

    public void strafeLeft(int distance) {
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backRightMotor.setTargetPosition(-distance);
        backLeftMotor.setTargetPosition(distance);
        frontRightMotor.setTargetPosition(-distance);
        frontLeftMotor.setTargetPosition(distance);

//        For refrence, the following code is found in strafeRight();
//        theoretically, the above should be equal and opposite to the
//        below...I think
//        backRightMotor.setTargetPosition(-distance);
//        backLeftMotor.setTargetPosition(distance);
//        frontRightMotor.setTargetPosition(-distance);
//        frontLeftMotor.setTargetPosition(distance);

        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // All of these values are set to 1, but all of the values in strafeRight() are .1
        backRightMotor.setPower(1);
        backLeftMotor.setPower(1);
        frontRightMotor.setPower(1);
        frontLeftMotor.setPower(1);

        while (backLeftMotor.isBusy() && backRightMotor.isBusy() && frontLeftMotor.isBusy() && frontRightMotor.isBusy()) {
//        There is nothing in this loop; I'm pretty sure this means that this loop will
//        simply run until the motors reach their desired position, and then the code
//        following the loop will run
        }
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);

    }

}




