package org.firstinspires.ftc.teamcode.SubSys;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.PIDEx;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficientsEx;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DropDown implements Subsystem {
    DcMotorEx drop;
    private double Kp;
    private double Ki;
    private  boolean pid=  false;

    private double Kd;
    private double integralSumMax;
    private double stability_thresh;
    private double lowPassGain;
    PIDCoefficientsEx coefficients = new PIDCoefficientsEx(Kp,Ki,Kd,integralSumMax,
            stability_thresh,
            lowPassGain);
    // usage of the PID
    PIDEx controller = new PIDEx(coefficients);

    public DropDown (HardwareMap hwmap) {
        drop = hwmap.get(DcMotorEx.class, "drop");
        drop.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        drop.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setHeight(int target) {
        if (pid) {
                drop.setPower(controller.calculate(target, drop.getCurrentPosition()));


        }
    }

    public void manualControl(boolean up, boolean down) {

        if(!pid) {
            if (up) {
                    drop.setPower(1);

            } else if (down) {

                    drop.setPower(-1);


            } else {
                    drop.setPower(0);


            }

        }
    }
    public void setPid(boolean set) {
        pid = set;
    }
}
