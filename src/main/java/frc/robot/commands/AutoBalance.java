// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RevDrivetrain;

import static frc.robot.Constants.*;

import com.kauailabs.navx.frc.AHRS;

public class AutoBalance extends CommandBase {
  private RevDrivetrain rDrive;
  private AHRS ahrs = new AHRS();
  
  boolean balanceMode;

  private PIDController balanceCorrector = 
  new PIDController(balanceCorrection.kP, balanceCorrection.kI, balanceCorrection.kD);

  /** Creates a new AutoBalance. */
  public AutoBalance(RevDrivetrain drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    rDrive = drive;
    balanceCorrector.setTolerance(.1);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    balanceCorrector.setSetpoint(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double pitchAngleDegrees = ahrs.getPitch();
    System.out.println(pitchAngleDegrees);
    //rDrive.getDifferentialDrive().arcadeDrive(balanceCorrector.calculate(pitchAngleDegrees, 0), 0, false);
    rDrive.getDifferentialDrive().tankDrive(-balanceCorrector.calculate(pitchAngleDegrees, 0), 
                                            balanceCorrector.calculate(pitchAngleDegrees, 0), false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    rDrive.getDifferentialDrive().tankDrive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return balanceCorrector.atSetpoint();
  }
}
