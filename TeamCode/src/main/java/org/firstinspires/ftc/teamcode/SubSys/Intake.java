package org.firstinspires.ftc.teamcode.SubSys;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.PIDEx;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficientsEx;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake implements Subsystem {

    VirtualFourBar bar;
    DcMotorEx spinny;
    private double Kp;
    private double Ki;
    private double Kd;
    private double integralSumMax;
    private double stability_thresh;
    private double lowPassGain;
    PIDCoefficientsEx coefficients = new PIDCoefficientsEx(Kp,Ki,Kd,integralSumMax,
            stability_thresh,
            lowPassGain);
    // usage of the PID
    PIDEx controller = new PIDEx(coefficients);


    public Intake(HardwareMap hardwareMap) {
        bar = new VirtualFourBar(hardwareMap);
        spinny = hardwareMap.get(DcMotorEx.class, "sp");
        spinny.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spinny.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void intake(int target) {
        spinny.setPower(controller.calculate(target, spinny.getCurrentPosition()));
    }

}
