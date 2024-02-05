package org.firstinspires.ftc.teamcode.CustomHardware;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class OwlHardware {
    Class hardwareType;

    Object device;

    Telemetry telemetry;

    String displayName;

    String config;

    public OwlHardware(HardwareMap hardwareMap, Telemetry telemetry, String config, String displayName, Class hardwareType) {
        this.hardwareType = hardwareType;
        device = hardwareMap.get(hardwareType, config);
        this.telemetry = telemetry;
        this.config = config;
        this.displayName = displayName;

    }

    public Class returnInnerClass(){
        return device.getClass();
    }

    public void telemetry() {

    }

}
