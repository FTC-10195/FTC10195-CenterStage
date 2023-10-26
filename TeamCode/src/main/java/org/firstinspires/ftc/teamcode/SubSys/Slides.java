package org.firstinspires.ftc.teamcode.SubSys;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.PIDEx;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficientsEx;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides implements Subsystem {
    DcMotorEx leftSlide;
    DcMotorEx rightSlide;
    private double Kp;
    private double Ki;
    private double Kd;
    private double integralSumMax;
    private double stability_thresh;
    private double lowPassGain;
    private  boolean pid=  false;
    PIDCoefficientsEx coefficients = new PIDCoefficientsEx(Kp,Ki,Kd,integralSumMax,
            stability_thresh,
            lowPassGain);
    // usage of the PID
    PIDEx controller = new PIDEx(coefficients);

    DcMotorEx[] motors = {leftSlide, rightSlide};
    public Slides(HardwareMap hardwareMap) {
        motors[0] = hardwareMap.get(DcMotorEx.class, "ls");
        motors[1] = hardwareMap.get(DcMotorEx.class, "rs");
        for(DcMotor motor: motors) {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void setHeight(int target) {
        if (pid) {
            for (DcMotor motor : motors) {
                motor.setPower(controller.calculate(target, motor.getCurrentPosition()));
            }

        }
    }

    public void manualControl(boolean up, boolean down) {

        if(!pid) {
            if (up) {
                for (DcMotor motor : motors) {
                    motor.setPower(1);
                }

            } else if (down) {

                for (DcMotor motor : motors) {
                    motor.setPower(-1);
                }

            } else {
                for (DcMotor motor : motors) {
                    motor.setPower(0);
                }

            }

        }
    }
    public void setPid(boolean set) {
        pid = set;
    }

}

