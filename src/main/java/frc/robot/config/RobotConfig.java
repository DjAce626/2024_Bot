package frc.robot.config;

import com.pathplanner.lib.util.PIDConstants;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;

public class RobotConfig {

  public DrivetrainConfig Drivetrain = new DrivetrainConfig(
    0.55,
    0.55,
    Math.PI * 0.7778174593052,
    1,
    Units.feetToMeters(19.685),
    Units.feetToMeters(10),
    Math.PI,
    0.3,
    false,
    new PIDConstants(1, 0, 0)
  );

  public PIDConstants kDrivePidConstants = new PIDConstants(0.2, 0, 0);

  public PIDConstants kSteeringPidConstants = new PIDConstants(2, 0, 0);

  public SwerveModuleConfig FrontLeftSwerveModuleConfig = new SwerveModuleConfig(
    "Front-Left",
    2,
    3,
    4,
    0.164551,
    true,
    true,
    kDrivePidConstants,
    kSteeringPidConstants,
    new Translation2d(
      -Drivetrain.TrackWidthMeters / 2,
      Drivetrain.WheelBaseMeters / 2
    )
  );

  public SwerveModuleConfig FrontRightSwerveModuleConfig = new SwerveModuleConfig(
    "Front-Right",
    5,
    6,
    7,
    0.350098,
    false,
    true,
    kDrivePidConstants,
    kSteeringPidConstants,
    new Translation2d(
      Drivetrain.TrackWidthMeters / 2,
      Drivetrain.WheelBaseMeters / 2
    )
  );

  public SwerveModuleConfig RearRightSwerveModuleConfig = new SwerveModuleConfig(
    "Rear-Right",
    8,
    9,
    10,
    0.717773,
    false,
    true,
    kDrivePidConstants,
    kSteeringPidConstants,
    new Translation2d(
      Drivetrain.TrackWidthMeters / 2,
      -Drivetrain.WheelBaseMeters / 2
    )
  );

  public SwerveModuleConfig RearLeftSwerveModuleConfig = new SwerveModuleConfig(
    "Rear-Left",
    11,
    12,
    13,
    0.181152,
    true,
    true,
    kDrivePidConstants,
    kSteeringPidConstants,
    new Translation2d(
      -Drivetrain.TrackWidthMeters / 2,
      -Drivetrain.WheelBaseMeters / 2
    )
  );
}
