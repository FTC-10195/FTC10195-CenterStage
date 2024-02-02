package org.firstinspires.ftc.teamcode.SubSys;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Chamber implements Subsystem {

    DcMotorEx spinny;
    private final CRServo bottomRoller;


    public Chamber(HardwareMap hardwareMap) {
        spinny = hardwareMap.get(DcMotorEx.class, "in");
        spinny.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spinny.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bottomRoller = hardwareMap.get(CRServo.class, "roll");
    }

    public void servoRoller(float right_trigger) {
        bottomRoller.setPower(right_trigger);
    }

    public void motorsPIN(double power) {
        spinny.setPower(power);
    }

}
