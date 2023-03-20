// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    
    /*** ------ Port Numbers ------ */

	// xbox 1 controller port
	public static final int kXbox1Port = 0;

	// xbox 2 controller port
	public static final int kXbox2Port = 1;


	// drive motor ports
	public static final int kLeftFrontPort = 24;
	public static final int kLeftRearPort = 1;
	public static final int kRightFrontPort = 9;
	public static final int kRightRearPort = 8;

	// set the deadband
	public static final double percentDeadband = 0.05;

    // assign port number to compressor
    public static final PneumaticsModuleType compressorModule = PneumaticsModuleType.REVPH;
	public static final int compressorCANID = 62;

	// elevator motor ports
	public static final int kLeftElevatorPort = 7;
	public static final int kRightElevatorPort = 2;

	// carriage motor port
	public static final int carriagePort = 12;

	// wrist motor port
	public static final int wristPort = 13;

	// shooter motor ports
	public static final int kLShooterPort = 10;
	public static final int kRShooterPort = 11;

	// claw piston ports
	public static final int kClawPistonPort = 15;


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


	/*** ------ AUTO BALANCE CONSTANTS ------ */

	// user inputs the number of feet they want the robot to move in AutoMoveAndBalance in the parentheses
	public static final double goalAutoMoveInches = (4) * 12;

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

	/*** ------ SHOOTER CONSTANTS ------ */

	// how many times the shooter should rotate at its initial speed
	public static final double shooterRotationsInitial = 5;

	// how many times the shooter should rotate at its final speed
	public static final double shooterRotationsFinal = 10;


	// initial and final speeds of the shooter in percentage of battery
		// should change this to a set voltage to ensure consistency
		// test these values and change as needed
	public static final double shooterSpeedInitialPercent = 0.5;
	public static final double shooterSpeedFinalPercent = 1;


	/*** ------ SETPOINT CONSTANTS ------ */

	// values for wrist bang-bang controller

	public static final double wristLevelRotations = 100;
	public static final double wristInRotations = 8;
	public static final double wristOutRotations = 130;
	
	public static final double wristRotationsTolerance = 2;

	public static final double wristSpeed = 0.825;

	
	// values for elevator bang-bang controller

	public static final double elevatorLowRotations = 1;
	
	// elevator mid is good at around 28.5 rotations
	public static final double elevatorMidRotations = 28.5;
	
	// aim for high elevator at 33 rotations
	public static final double elevatorHighRotations = 33;

	public static final double elevatorSubstationRotations = 33;
	
	public static final double elevatorRotationsTolerance = 2;

	public static final double elevatorSpeed = 0.3;

	
	// values for carriage bang-bang controller

	public static final double carriageInRotations = 1;
	
	// carriage mid is good at around -23 rotations
	public static final double carriageMidRotations = 23;
	
	// carriage is fully extended at -23 rotations
	public static final double carriageOutRotations = 23;
	
	public static final double carriageRotationsTolerance = 2;

	public static final double carriageSpeed = 0.3;






}
