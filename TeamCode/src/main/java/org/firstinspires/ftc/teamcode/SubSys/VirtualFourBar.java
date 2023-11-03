package org.firstinspires.ftc.teamcode.SubSys;

import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class VirtualFourBar implements Subsystem {

    //TODO motion profile
    private ServoEx leftServo;
    private ServoEx rightServo;
    ServoEx[] servos = { leftServo,  rightServo};

    public VirtualFourBar(HardwareMap hardwareMap) {
        servos[0] = hardwareMap.get(ServoEx.class, "ls");
        servos[1] = hardwareMap.get(ServoEx.class, "rs");
    }
    public void move(int target) {
            leftServo.turnToAngle(target);
            rightServo.turnToAngle(target);
    }
}
