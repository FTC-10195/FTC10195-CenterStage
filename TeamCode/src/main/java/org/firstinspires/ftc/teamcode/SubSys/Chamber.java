package org.firstinspires.ftc.teamcode.SubSys;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.PIDEx;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficientsEx;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Chamber implements Subsystem {

    DcMotorEx spinny;
    private double Kp;
    private double Ki;
    private double Kd;
    private double integralSumMax;
    private double stability_thresh;
    private double lowPassGain;
    private CRServo bottomRoller;
    PIDCoefficientsEx coefficients = new PIDCoefficientsEx(Kp,Ki,Kd,integralSumMax,
            stability_thresh,
            lowPassGain);
    // usage of the PID
    PIDEx controller = new PIDEx(coefficients);


    public Chamber(HardwareMap hardwareMap) {
        spinny = hardwareMap.get(DcMotorEx.class, "sp");
        spinny.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spinny.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bottomRoller = hardwareMap.get(CRServo.class, "roll");
    }

    public void motorMove(int target) {
        spinny.setPower(controller.calculate(target, spinny.getCurrentPosition()));
    }

    public void servoRoller(double power) {
        bottomRoller.setPower(power);
    }


}
