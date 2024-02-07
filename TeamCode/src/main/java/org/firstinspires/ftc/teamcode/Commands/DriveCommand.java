package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.robocol.Command;

import org.firstinspires.ftc.teamcode.SubSys.MecanumDrive;

import java.util.function.DoubleSupplier;


public class DriveCommand extends CommandBase {

    private final MecanumDrive mecanumDrive;
    private DoubleSupplier forward;
    private  DoubleSupplier rotate;
    private  DoubleSupplier strafe;

    public DriveCommand(MecanumDrive drive, DoubleSupplier forward, DoubleSupplier rotate, DoubleSupplier strafe) {
        mecanumDrive = drive;
        this.forward = forward;
        this.rotate = rotate;
        this.strafe = strafe;
    }

    @Override
    public void execute() {
        mecanumDrive.robotDrive(
                forward.getAsDouble(),
                strafe.getAsDouble(),
                rotate.getAsDouble()
        );
    }
}
