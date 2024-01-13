package org.firstinspires.ftc.teamcode.SubSys;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.PIDEx;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficientsEx;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DropDown implements Subsystem {
    DcMotorEx drop;
    private static double Kp;
    private static double Ki;
    private  boolean pid=  false;

    private static double Kd;
    private static double integralSumMax;
    private static double stability_thresh;
    private static double lowPassGain;
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
