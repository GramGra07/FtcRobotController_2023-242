package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.AutoRandom;
import org.firstinspires.ftc.teamcode.Blink;
import org.firstinspires.ftc.teamcode.StartPose;
import org.firstinspires.ftc.teamcode.Vision;

public class autoHardware extends HardwareConfig {//auto version of hardware config

    public static Pose2d startPose = new Pose2d(12, -63, Math.toRadians(90));
//    public static Pose2d startPose = getStartPose(StartPose.redRight);
    HardwareMap hardwareMap = null;

    private LinearOpMode myOpMode = null;   // gain access to methods in the calling OpMode.

    public autoHardware(LinearOpMode opMode) {
        super(opMode);
        myOpMode = opMode;
    }
    public static AutoRandom autonomousRandom = AutoRandom.mid;

    public void initAuto(HardwareMap ahwMap, boolean vision) {
        hardwareMap = ahwMap;
        init(ahwMap);
        Vision.initVision(ahwMap);
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
        myOpMode.waitForStart();
        timer.reset();
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.valueOf(Blink.getColor()));
    }
//    public Pose2d getStartPose(StartPose spot){
//        switch (spot){
//            todo: add start positions
//            case redRight:
//                return new Pose2d(-60, -35, Math.toRadians(0));
//            case redLeft:
//                return new Pose2d(12, -63, Math.toRadians(-90));
//            case blueRight:
//                return new Pose2d(12, -63, Math.toRadians(90));
//            case blueLeft:
//                return new Pose2d(12, -35, Math.toRadians(90));
//        }
//    }
}
