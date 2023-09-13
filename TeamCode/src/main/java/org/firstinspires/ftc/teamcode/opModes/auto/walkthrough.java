package org.firstinspires.ftc.teamcode.opModes.auto;

import static org.firstinspires.ftc.teamcode.opModes.autoHardware.autonomousRandom;
import static org.firstinspires.ftc.teamcode.opModes.autoHardware.delayUntilTagFound;
import static org.firstinspires.ftc.teamcode.opModes.autoHardware.getStartPose;
import static org.firstinspires.ftc.teamcode.opModes.autoHardware.navToBackdrop;
import static org.firstinspires.ftc.teamcode.opModes.autoHardware.targetTag;
import static org.firstinspires.ftc.teamcode.opModes.autoHardware.runSpikeNav;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Enums.Alliance;
import org.firstinspires.ftc.teamcode.Enums.StartSide;
import org.firstinspires.ftc.teamcode.Trajectories.ShiftTrajectories;
import org.firstinspires.ftc.teamcode.opModes.autoHardware;
import org.firstinspires.ftc.teamcode.opModes.rr.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.opModes.rr.drive.advanced.PoseStorage;

@Autonomous
//@Disabled
public class walkthrough extends LinearOpMode {
    public Pose2d startPose = autoHardware.startPose;
    autoHardware robot = new autoHardware(this);

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(getStartPose(Alliance.BLUE, StartSide.LEFT));
        robot.initAuto(hardwareMap);
        if (isStopRequested()) return;
        runSpikeNav(drive, this);
        // do claw stuff to move & drop pixel
        navToBackdrop(drive);
        // drop pixel
        delayUntilTagFound(this, targetTag);
        // do we need new pose?
        // move claw up
        switch (autonomousRandom) {
            case left:
                ShiftTrajectories.shiftLeft(drive).start();
                break;
            case mid:
                // should already be lined up
                break;
            case right:
                ShiftTrajectories.shiftRight(drive).start();
                break;
        }
        // drop pixel
        // start repeat
        // nav back to cycle
        // move arm to position
        // grab pixel
        // nav back to drop
        // move arm up
        // drop pixel
        // end repeat
        PoseStorage.currentPose = drive.getPoseEstimate();
    }
}