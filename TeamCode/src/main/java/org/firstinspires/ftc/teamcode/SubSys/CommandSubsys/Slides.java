package org.firstinspires.ftc.teamcode.SubSys.CommandSubsys;

import android.transition.Slide;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.PIDEx;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficientsEx;
import com.acmerobotics.dashboard.config.Config;
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

@Config
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

    Telemetry telemetry;
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
        MAXIMUM,
        MANUAL,
        IDLE
    }

    SlideStates state=  SlideStates.MINIMUM;

    public Slides(HardwareMap hardwareMap, Telemetry telemetry) {
        controller.setTolerance(20);
        this.telemetry = telemetry;
        leftSlide = hardwareMap.get(DcMotorEx.class, "ls");
        rightSlide = hardwareMap.get(DcMotorEx.class, "rs");
    leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);
    }

 /*   @Override
    public void periodic() {
        currentLeft = leftSlide.getCurrentPosition();
        currentRight = leftSlide.getCurrentPosition();

        if (state != SlideStates.MANUAL) {
            leftSlide.setPower((controller.calculate(currentLeft, target)));
            rightSlide.setPower((controller.calculate(currentRight, target)));
        }

    }

*/
    /***
     *
     * @param state
     * sets the target of the system, inputting a state
     */
    public void setTarget(SlideStates state) {
        switch (state) {
            case MINIMUM:
                state = SlideStates.MANUAL;
                target = MINIMUM_POS;
                break;
            case INTAKEPOS:
                state = SlideStates.INTAKEPOS;

                target = INTAKEPOS;
                break;
            case LINE1:
                target = LINE1;
                break;
            case LINE2:
                target = LINE2;
                break;
            case MAXIMUM:
                target= MAXIMUM;
                break;


        }
    }

    public double setManualTarget(boolean up, boolean down) {

        return target - 20;
    }
   public void goDown() {
        leftSlide.setPower(-1);
        rightSlide.setPower(-1);
   }

   public void goUp() {
        leftSlide.setPower(1);
        rightSlide.setPower(1);
   }

   public void stopMoving() {
        leftSlide.setPower(0);
        rightSlide.setPower(0);
   }


   public void manualControl(boolean up, boolean down) {
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

       telemetry.addData("Left", leftSlide.getCurrentPosition());
       telemetry.addData("Right", rightSlide.getCurrentPosition());
   }

/*

    public boolean isJammed() {
        return rightSlide.currentOverThreshold() || leftSlide.currentOverThreshold();
    }
*/
}

