package org.firstinspires.ftc.teamcode.CustomHardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class OwlMotor extends OwlHardware {

    DcMotorEx motor;

    public OwlMotor(HardwareMap hardwareMap, Telemetry telemetry, String config, String displayName, DcMotor.RunMode mode, DcMotorSimple.Direction direction) {
        super(hardwareMap, telemetry, config, displayName, DcMotorEx.class);
        motor.setMode(mode);
        motor.setDirection(direction);

    }




    public DcMotorEx getInnerMotor() {
        return motor;
    }
    public boolean currentOverThreshold() {
        return getInnerMotor().getCurrent(CurrentUnit.AMPS) > 5;
    }

    @Override
    public void telemetry() {
        telemetry.addData("Current current", getInnerMotor().getCurrent(CurrentUnit.AMPS));

    }
}
