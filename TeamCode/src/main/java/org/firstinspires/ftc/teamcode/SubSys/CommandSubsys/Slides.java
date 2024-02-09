package org.firstinspires.ftc.teamcode.SubSys.CommandSubsys;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.PIDEx;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficientsEx;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.CustomHardware.OwlMotor;

public class Slides extends SubsystemBase {
    DcMotorEx leftSlide;
    DcMotorEx rightSlide;
    private static double Kp;
    private static double Ki;
    private static double Kd;
    private static double target;
    private  static double Kf;

    // usage of the PID
    PIDFController controller = new PIDFController(Kp, Ki, Kd, Kf);

    private final double MINIMUM_POS = 20;
    private final double INTAKEPOS = 30;
    private final double LINE1 = 2500;
    private final double LINE2 = 4000;
    private final double LINE3 = 6000;
    private final double MAXIMUM = 6500;
    public static double currentLeft;
    public static double currentRight;
    enum SlideStates {
        MINIMUM,
        INTAKEPOS,
        LINE1,
        LINE2,
        LINES,
        MAXIMUM
    }

    public Slides(HardwareMap hardwareMap, Telemetry telemetry) {
        controller.setTolerance(20);
        leftSlide = hardwareMap.get(DcMotorEx.class, "ls");
        rightSlide = hardwareMap.get(DcMotorEx.class, "ls");

    }

    @Override
    public void periodic() {
        currentLeft = leftSlide.getCurrentPosition();
        currentRight = leftSlide.getCurrentPosition();

        leftSlide.setPower((controller.calculate(currentLeft, target)));
        rightSlide.setPower((controller.calculate(currentRight, target)));
    }


    public void setLift(SlideStates state) {
        switch (state) {
            case MINIMUM:
                target = MINIMUM_POS;
                break;
            case INTAKEPOS:
                target = INTAKEPOS;
                break;
            case LINE1:
                target = INTAKEPOS;
                break;



        }
    }

  /*  public void manualControl(boolean up, boolean down) {
        if (up) {
            //   if (rightSlide.getCurrentPosition() < 2600) {
            rightSlide.setPower(1);
            //    } else if (rightSlide.getCurrentPosition() > 2600) {
            leftSlide.setPower(1);
            //   }
            //    if (leftSlide.getCurrentPosition() < 2150) {
            //  leftSlide.setPower(1);
        }
        //  else if (leftSlide.getCurrentPosition() > 2150) {

        else if (down) {
            rightSlide.setPower(-1);
            leftSlide.setPower(-1);

        } else {
            rightSlide.setPower(0);
            leftSlide.setPower(0);
        }

   */

/*

    public boolean isJammed() {
        return rightSlide.currentOverThreshold() || leftSlide.currentOverThreshold();
    }
*/
}

