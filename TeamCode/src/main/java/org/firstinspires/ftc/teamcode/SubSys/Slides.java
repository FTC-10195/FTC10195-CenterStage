package org.firstinspires.ftc.teamcode.SubSys;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.PIDEx;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficientsEx;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class Slides implements Subsystem {
    DcMotorEx leftSlide;
    DcMotorEx rightSlide;
    private static double Kp;
    private static double Ki;
    private static double Kd;
    private static double integralSumMax;
    private static double stability_thresh;
    private static double lowPassGain;
    private boolean pid = false;
    PIDCoefficientsEx coefficients = new PIDCoefficientsEx(Kp, Ki, Kd, integralSumMax,
            stability_thresh,
            lowPassGain);
    // usage of the PID
    PIDEx controller = new PIDEx(coefficients);

    public Slides(HardwareMap hardwareMap) {
        leftSlide = hardwareMap.get(DcMotorEx.class, "ls");
        rightSlide = hardwareMap.get(DcMotorEx.class, "rs");
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void setHeight(int target) {
        if (pid) {
            leftSlide.setPower(controller.calculate(target, leftSlide.getCurrentPosition()));
            rightSlide.setPower(controller.calculate(target, rightSlide.getCurrentPosition()));
        }
    }


    public void manualControl(boolean up, boolean down) {
        if (up) {
            //   if (rightSlide.getCurrentPosition() < 2600) {
            rightSlide.setPower(1);
            //    } else if (rightSlide.getCurrentPosition() > 2600) {
            leftSlide.setPower(1);
            //   }
            //    if (leftSlide.getCurrentPosition() < 2150) {
            //  leftSlide.setPower(1);
        }
        //  else if (leftSlide.getCurrentPosition() > 2150) {

        else if (down) {
            rightSlide.setPower(-1);
            leftSlide.setPower(-1);

        } else {
            rightSlide.setPower(0);
            leftSlide.setPower(0);
        }
    }

    public void setPid(boolean set) {
        pid = set;
    }

    public boolean isJammed() {
        return rightSlide.getCurrent(CurrentUnit.AMPS) > 10 || leftSlide.getCurrent(CurrentUnit.AMPS) > 10;
    }

}

