// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;

// import RevDrivetrain subsystem
import frc.robot.subsystems.RevDrivetrain;

// import constants
import static frc.robot.Constants.*;



public class GoToTarget extends CommandBase {
  
  private RevDrivetrain drive;

  PhotonCamera camera = new PhotonCamera("Microsoft_LifeCam_HD-3000");

  // PID constants should be tuned per robot
  final double linearP = 1.0;
  final double linearD = 0.0;
  PIDController forwardController = new PIDController(linearP, 0, linearD);

  final double angularP = 0.01;
  final double angularD = 0.0;
  PIDController turnController = new PIDController(angularP, 0, angularD);

  /** Creates a new GoToTarget. */
  public GoToTarget(RevDrivetrain rDrive) {
  // Use addRequirements() here to declare subsystem dependencies.
  drive = rDrive;
  
  // use addRequirements() here to declare subsystem dependencies
  addRequirements(drive);
  
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    double forwardSpeed;
    double rotationSpeed;

    double range = 0;
        // Vision-alignment mode
        // Query the latest result from PhotonVision
    var result = camera.getLatestResult();

    if (result.hasTargets()) {
      // First calculate range*/
      range =
        PhotonUtils.calculateDistanceToTargetMeters(
          cameraHeightMeters,
          targetHeightMeters,
          cameraPitchRadians,
          Units.degreesToRadians(result.getBestTarget().getPitch()));

      // Use this range as the measurement we give to the PID controller.
      // -1.0 required to ensure positive PID controller effort _increases_ range
      forwardSpeed = forwardController.calculate(range, goalRangeMeters);

      // Also calculate angular power
      // -1.0 required to ensure positive PID controller effort _increases_ yaw
      rotationSpeed = -turnController.calculate(result.getBestTarget().getYaw(), 0);

        } else {
            // If we have no targets, stay still.
            forwardSpeed = 0;
            rotationSpeed = 0;
        }
    

    // Use our forward/turn speeds to control the drivetrain
    drive.getDifferentialDrive().arcadeDrive(rotationSpeed, forwardSpeed);

    System.out.println(range);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
