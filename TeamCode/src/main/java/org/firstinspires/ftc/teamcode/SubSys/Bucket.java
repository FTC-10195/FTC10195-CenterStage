package org.firstinspires.ftc.teamcode.SubSys;

import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Bucket implements Subsystem {
    ServoEx lowerServo;
    ServoEx upperServo;
    RevColorSensorV3 lowerSensor;
    RevColorSensorV3 upperSensor;
    ServoEx armServo;
    int switchPosition;
    double bucketDepth;

    public Bucket(HardwareMap hardwareMap) {
        lowerSensor = hardwareMap.get(RevColorSensorV3.class, "lsens");
        upperSensor = hardwareMap.get(RevColorSensorV3.class, "usens");

        lowerServo = hardwareMap.get(ServoEx.class, "lserv");
        upperServo = hardwareMap.get(ServoEx.class, "userv");
        armServo = hardwareMap.get(ServoEx.class, "aserv");
    }

    public boolean detectSens(RevColorSensorV3 sensor) {
        if (sensor.getDistance(DistanceUnit.MM) < bucketDepth) {
            return true;
        }
        return false;
    }

    public void rotateHook(ServoEx servo) {
        servo.rotateBy(switchPosition);
    }

    public void rotateArm(double angle) {
        armServo.turnToAngle(angle);
    }
}
