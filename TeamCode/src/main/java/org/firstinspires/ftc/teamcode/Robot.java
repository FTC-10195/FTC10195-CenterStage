package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.SubSys.Bucket;
import org.firstinspires.ftc.teamcode.SubSys.Chamber;
import org.firstinspires.ftc.teamcode.SubSys.DropDown;
import org.firstinspires.ftc.teamcode.SubSys.MecanumDrive;
import org.firstinspires.ftc.teamcode.SubSys.Slides;


public class Robot {

    private Bucket bucket;
    private Chamber chamber;
    private DropDown dropDown;
    private MecanumDrive mecanumDrive;
    private Slides slides;
    public Robot(HardwareMap hardwareMap) {
        bucket = new Bucket(hardwareMap);
        chamber = new Chamber(hardwareMap);
        dropDown = new DropDown(hardwareMap);
        slides = new Slides(hardwareMap);

    }
    public enum Color {
        BLUE,
        RED
    }

    Color color = Color.BLUE;

    public void setColor(int col) {
        switch(col) {
            case 0:
                color = Color.BLUE;
                break;
            case 1:
                color = Color.RED;
                break;
        }
    }

    public String getColor() {
        return color.toString();
    }




}
