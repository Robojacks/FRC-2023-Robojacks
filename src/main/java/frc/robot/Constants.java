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

	// xbox controller port
	public static final int kXboxPort = 0;

	// drive Ports
	public static final int kLeftFrontPort = 1;
	public static final int kLeftRearPort = 2;
	public static final int kRightFrontPort = 3;
	public static final int kRightRearPort = 4;

	// elevator ports
	public static final int kLeftElevatorPort = 5;
	public static final int kRightElevatorPort = 6;

	// carriage port
	public static final int carriagePort = 7;

	// wrist port
	public static final int wristPort = 8;

	// set the deadband
	public static final double percentDeadband = 0.05;

	// how much the wrist shiuld rotate to extend and retract
	public static final double wristRotations = 20;


	/** ------ VISION TRACKING CONSTANTS ------ */

	// Constants such as camera and target height stored. Change per robot and goal!
    public static final double cameraHeightMeters = Units.inchesToMeters(8.5);
    public static final double targetHeightMeters = Units.inchesToMeters(33.7);
    
	// Angle between horizontal and the camera.
    public static final double cameraPitchRadians = Units.degreesToRadians(47);

    // How far from the target we want to be
	// found experimentally that this goal range has a tolerance of plus or minus 4 inches (tested with range of 24 inches)
    public static final double goalTargetRangeMeters = Units.inchesToMeters(24);

	// PID values for vision correction 
	public static class visionCorrection {
		public static double linearP = 1;
		public static double linearD = 0;
		
		public static double angularP = .01;
		public static double angularD = 0;
	}


	/** ------ AUTO BALANCE CONSTANTS ------ */

	// user inputs the number of feet they want the robot to move in AutoMoveAndBalance in the parentheses
	public static final double goalAutoMoveInches = (2) * 12;

	// the circumferene of the wheel in inches
	// user inputs the radius of the wheel in inches into the parentheses
	public static final double wheelCircumferenceInches = (3) * 2 * Math.PI;

	// 10 input rotations for every 1 real rotation
	public static final double driveMotorGearRatio = 10;

	// how many rotations the wheels move in autonomous
	public static final double autoMoveRotations = (goalAutoMoveInches * driveMotorGearRatio) / (wheelCircumferenceInches);

	//thresholds for auto balancing
    public static final double kOffBalanceAngleThresholdDegrees = 10;
    public static final double kOnBalanceAngleThresholdDegrees = 5;	

	// PID values for auto balancing
	public static class balanceCorrection {
		public static double kP = 0.005;
		public static double kI = 0;
		public static double kD = 0;

	}



}
