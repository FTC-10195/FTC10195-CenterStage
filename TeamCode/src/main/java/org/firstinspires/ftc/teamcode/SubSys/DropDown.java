package org.firstinspires.ftc.teamcode.SubSys;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.PIDEx;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficientsEx;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class DropDown implements Subsystem {
    DcMotorEx drop;
    Servo left;
    Servo right;

    double down = .075;
    double up = .5;


    public DropDown(HardwareMap hwmap) {
        drop = hwmap.get(DcMotorEx.class, "spin");
        left = hwmap.get(Servo.class, "ld");
        right = hwmap.get(Servo.class, "rd");
        left.setDirection(Servo.Direction.REVERSE);
        drop.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        drop.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drop.setDirection(DcMotorSimple.Direction.REVERSE);
        move(down);
    }

    private void move(double pos) {
        left.setPosition(pos);
        right.setPosition(pos);
    }

    public void move(boolean goDown, boolean goUp) {
        if(goDown) {
            move(down);
        }
        if(goUp) {
            move(up);
        }
        }

    public void spin(boolean spin) {

        if(spin) {
            drop.setPower(1);
        }
        else {
            drop.setPower(0);
        }
    }
    }



