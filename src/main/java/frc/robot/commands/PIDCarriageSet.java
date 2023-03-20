// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Carriage;
import static frc.robot.Constants.*;

public class PIDCarriageSet extends CommandBase {

  private double setpoint;
  private final Carriage m_carriage;

  private PIDController m_carriagePIDController;

  /** Creates a new CarriageSet. */
  public PIDCarriageSet(Carriage m_carriage, double setpoint) {
    this.setpoint = setpoint;
    this.m_carriage = m_carriage;

    m_carriagePIDController = new PIDController(1, .0001, 0);
    m_carriagePIDController.setTolerance(.25);
    addRequirements(m_carriage);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double feedForward = -.05;
    double speed = m_carriagePIDController.calculate(m_carriage.GetPosition(), setpoint);
    speed = (speed > 0) ? speed + feedForward : speed - feedForward;
    speed = (speed > 1) ? 1.0 : speed;
    speed = (speed < -1) ? -1 : speed;
    m_carriage.setSpeed(speed * carriageSpeed);
    System.out.println(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_carriage.setSpeed(0);
    System.out.println("I was interrupted");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println(m_carriagePIDController.atSetpoint());
    if (m_carriagePIDController.atSetpoint()) {
      return true;

    }
    else {
      return false;
    }
  }
}
