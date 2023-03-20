// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Wrist;
import static frc.robot.Constants.*;

public class PIDWrist extends CommandBase {

  private double setpoint;
  private final Wrist m_wrist;

  private PIDController m_wristPIDController;

  /** Creates a new WristSet. */
  public PIDWrist(Wrist m_wrist, double setpoint) {
    this.setpoint = setpoint;
    this.m_wrist = m_wrist;

    m_wristPIDController = new PIDController(.8, .0001, 0);
    m_wristPIDController.setTolerance(.5);
    addRequirements(m_wrist);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double feedForward = -.05;
    double speed = m_wristPIDController.calculate(m_wrist.GetPosition(), setpoint);
    speed = (speed > 0) ? speed + feedForward : speed - feedForward;
    speed = (speed > 1) ? 1.0 : speed;
    speed = (speed < -1) ? -1 : speed;
    m_wrist.setSpeed(speed * wristSpeed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_wrist.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println(m_wristPIDController.atSetpoint());
    if (m_wristPIDController.atSetpoint()) {
      return true;

    }
    else {
      return false;
    }
  }
}
