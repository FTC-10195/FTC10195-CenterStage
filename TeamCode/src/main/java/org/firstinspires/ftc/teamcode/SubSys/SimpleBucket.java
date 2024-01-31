package org.firstinspires.ftc.teamcode.SubSys;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class SimpleBucket implements Subsystem {

    Servo lowerServo;
    Servo upperServo;

    double lowerIn = 0;
    double lowerOut = .3;

    double upperIn = 0;
    double upperOut = .2;

    public SimpleBucket(HardwareMap hardwareMap) {
        lowerServo =   hardwareMap.get(Servo.class, "lserv");
        upperServo = hardwareMap.get(Servo.class, "userv");
    }


    public void intakeMode() {
        lowerServo.setPosition(lowerOut);
        upperServo.setPosition(upperOut);
    }

    public void outtakeUpper() {
        upperServo.setPosition(upperIn);
    }
    public void outtakeLower() {lowerServo.setPosition(lowerIn);}



}
