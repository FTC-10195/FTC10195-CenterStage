package org.firstinspires.ftc.teamcode.SubSys;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.ftccommon.internal.manualcontrol.parameters.ImuParameters;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.xyzOrientation;

@Autonomous
public class IMU extends LinearOpMode {
    IMU imu;
    
    @Override
    public void runOpMode() throws InterruptedException {
        imu = hardwareMap.get(IMU.class, "imu");
        
        double xRotation = 18;
        double yRotation = 90;
        double zRotation = 0;
        
        Orientation hubRotation = xyzOrientation(xRotation,yRotation,zRotation);

        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(hubRotation);
    }
}
