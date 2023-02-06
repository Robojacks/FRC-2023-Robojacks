// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    
    // Port Numbers

	// Xbox controller port
	public static final int kXboxPort = 0;

	// Drive Ports
	public static final int kLeftFrontPort = 1;
	public static final int kLeftRearPort = 2;
	public static final int kRightFrontPort = 3;
	public static final int kRightRearPort = 4;

	public static final double percentDeadband = 0.05;
	

	// Constants such as camera and target height stored. Change per robot and goal!
    public static final double cameraHeightMeters = Units.inchesToMeters(8.5);
    public static final double targetHeightMeters = Units.inchesToMeters(25);
    
	// Angle between horizontal and the camera.
    public static final double cameraPitchRadians = Units.degreesToRadians(47);

    // How far from the target we want to be
    public static final double goalRangeMeters = Units.inchesToMeters(24);

	// how many rotations the wheels move in autonomous
	// 10 input rotations for every 1 real rotation
	public static final double goalEncoderRotations = 1 * 10;

}
