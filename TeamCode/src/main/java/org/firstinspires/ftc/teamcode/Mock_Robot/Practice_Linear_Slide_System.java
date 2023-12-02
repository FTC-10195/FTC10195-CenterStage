package org.firstinspires.ftc.teamcode.Mock_Robot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Practice_Linear_Slide_System extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor lowerLinearSlideMotor = hardwareMap.dcMotor.get("lowerLinearSlideMotor");
        DcMotor upperLinearSlideMotor = hardwareMap.dcMotor.get("upperLinearSlideMotor");
        boolean previousLeft = gamepad1.dpad_left;
        boolean previousUp = gamepad1.dpad_up;
        boolean previousRight = gamepad1.dpad_right;
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            if (gamepad1.dpad_left & !previousLeft) {

                lowerLinearSlideMotor.setPower(1); //Changeable
                upperLinearSlideMotor.setPower(1); //Changeable

            }

            if (gamepad1.dpad_up & !previousUp) {

                lowerLinearSlideMotor.setPower(2); //Changeable
                upperLinearSlideMotor.setPower(2); //Changeable

            }

            if (gamepad1.dpad_right & !previousRight) {

                lowerLinearSlideMotor.setPower(3); //Changeable
                upperLinearSlideMotor.setPower(3); //Changeable

            }
        }
    }
}

