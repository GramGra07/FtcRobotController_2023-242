package org.firstinspires.ftc.teamcode.Trajectories.backdrop;

import static org.firstinspires.ftc.teamcode.Trajectories.backdrop.ShiftTrajectories.shiftOffset;
import static org.firstinspires.ftc.teamcode.opModes.HardwareConfig.flipServo;
import static org.firstinspires.ftc.teamcode.opModes.autoSoftware.autoHardware.START_POSE;
import static org.firstinspires.ftc.teamcode.opModes.autoSoftware.autoHardware.autoRandomReliable;
import static org.firstinspires.ftc.teamcode.opModes.autoSoftware.autoHardware.updatePose;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.Enums.PathLong;
import org.firstinspires.ftc.teamcode.UtilClass.ServoUtil;
import org.firstinspires.ftc.teamcode.UtilClass.varStorage.AutoServoPositions;
import org.firstinspires.ftc.teamcode.opModes.rr.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.opModes.rr.trajectorysequence.TrajectorySequence;

@Config
public class BackdropTrajectories {
    public static int endAngle = 0;
    public static int offset = 8;
    public static int startOffsetRed = 3;
    public static int startOffsetBlue = 2;
    public static int xOffset = 4;
    public static int backdropOffset = 6;
    public static int blueMidOff = 0;
    public static int redMidOff = 0;
    public static int forwardOffset = 0;
    public static Pose2d backRed = new Pose2d(58 + forwardOffset, -32 - redMidOff - shiftOffset, Math.toRadians(endAngle));
    public static Pose2d backBlue = new Pose2d(58 + forwardOffset, 38 + blueMidOff - shiftOffset, Math.toRadians(endAngle));

    public static TrajectorySequence redShort(MecanumDrive drive) {
        updatePose(drive);
        return drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .lineToLinearHeading(backRed)
                .build();
    }

    public static TrajectorySequence redLong(MecanumDrive drive, PathLong pathLong) {
        updatePose(drive);
        switch (pathLong) {
            case INSIDE:
                switch (autoRandomReliable) {
                    case mid:
                    case right:
                        return drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                                .addDisplacementMarker(() -> ServoUtil.calculateFlipPose(AutoServoPositions.flipUp, flipServo))
                                .lineToLinearHeading(new Pose2d(-52, -10, Math.toRadians(endAngle)))
                                .lineTo(new Vector2d(36, -12))
                                .lineTo(new Vector2d(36, -30))
                                .splineToLinearHeading(new Pose2d(backRed.getX() - (backdropOffset), backRed.getY(), backRed.getHeading()), Math.toRadians(endAngle))
                                .build();
                    case left:
                        return drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                                .addDisplacementMarker(() -> ServoUtil.calculateFlipPose(AutoServoPositions.flipUp, flipServo))
                                .lineTo(new Vector2d(drive.getPoseEstimate().getX() + 4, -10))
                                .turn(Math.toRadians(-90))
                                .lineTo(new Vector2d(36, -12))
                                .lineTo(new Vector2d(36, -30))
                                .splineToLinearHeading(new Pose2d(backRed.getX() - (backdropOffset), backRed.getY(), backRed.getHeading()), Math.toRadians(endAngle))
                                .build();
                }
            case OUTSIDE:
                return drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .addDisplacementMarker(() -> ServoUtil.calculateFlipPose(AutoServoPositions.flipUp, flipServo))
                        .lineToLinearHeading(new Pose2d(START_POSE.getX() - xOffset, START_POSE.getY() + startOffsetRed, Math.toRadians(endAngle)))
                        .lineToLinearHeading(new Pose2d(-START_POSE.getX() - offset, START_POSE.getY() + startOffsetRed, Math.toRadians(endAngle)))
                        .splineToLinearHeading(new Pose2d(backRed.getX() - (backdropOffset), backRed.getY() + 5, backRed.getHeading()), Math.toRadians(endAngle))
                        .build();
            default:
                return null;
        }
    }

    public static TrajectorySequence blueShort(MecanumDrive drive) {
        updatePose(drive);
        return drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .lineToLinearHeading(backBlue)
                .build();
    }

    public static TrajectorySequence blueLong(MecanumDrive drive, PathLong pathLong) {
        updatePose(drive);
        switch (pathLong) {
            case INSIDE:
                switch (autoRandomReliable) {
                    case left:
                    case mid:
                        return drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                                .addDisplacementMarker(() -> ServoUtil.calculateFlipPose(AutoServoPositions.flipUp, flipServo))
                                .lineToLinearHeading(new Pose2d(-50, 12, Math.toRadians(endAngle)))
                                .lineTo(new Vector2d(36, 10))
                                .lineTo(new Vector2d(36, 30))
                                .splineToLinearHeading(new Pose2d(backBlue.getX() - (backdropOffset), backBlue.getY(), backBlue.getHeading()), Math.toRadians(endAngle))
                                .build();
                    case right:
                        return drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                                .addDisplacementMarker(() -> ServoUtil.calculateFlipPose(AutoServoPositions.flipUp, flipServo))
                                .lineToLinearHeading(new Pose2d(drive.getPoseEstimate().getX(), 12, Math.toRadians(-90)))
                                .turn(Math.toRadians(90))
                                .lineTo(new Vector2d(36, 12))
                                .lineTo(new Vector2d(36, 30))
                                .splineToLinearHeading(new Pose2d(backBlue.getX() - (backdropOffset), backBlue.getY(), backBlue.getHeading()), Math.toRadians(endAngle))
                                .build();
                }
            case OUTSIDE:
                return drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .addDisplacementMarker(() -> ServoUtil.calculateFlipPose(AutoServoPositions.flipUp, flipServo))
                        .lineToLinearHeading(new Pose2d(START_POSE.getX(), START_POSE.getY() - startOffsetBlue, Math.toRadians(endAngle)))
                        .lineToLinearHeading(new Pose2d(-START_POSE.getX() - offset, START_POSE.getY() - startOffsetBlue, Math.toRadians(endAngle)))
                        .lineTo(new Vector2d(36, 30))
                        .splineToLinearHeading(new Pose2d(backBlue.getX() - (backdropOffset), backBlue.getY() - 4, backBlue.getHeading()), Math.toRadians(endAngle))
                        .build();
            default:
                return null;
        }
    }
}
