package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.SubSys.CommandSubsys.MecanumDrive;

@TeleOp(group = "Match")
@Config
public class MIT extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive  drive = new MecanumDrive(hardwareMap, telemetry);
        GamepadEx controller1 = new GamepadEx(gamepad1);
        GamepadEx controller2 = new GamepadEx(gamepad2);
        drive.setDefaultCommand( new DriveCommand(drive,
               controller1::getLeftY,
                controller1::getRightX,
                controller1::getLeftX));


        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {



            CommandScheduler.getInstance().run();




            telemetry.update();


        }
    }
}
