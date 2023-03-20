// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;
import static frc.robot.Constants.*;

public class PIDElevatorSet extends CommandBase {

  private double setpoint;
  private final Elevator m_elevator;

  private PIDController m_elevatorPIDController;

  /** Creates a new ElevatorSet. */
  public PIDElevatorSet(Elevator m_elevator, double setpoint) {
    this.setpoint = setpoint;
    this.m_elevator = m_elevator;

    m_elevatorPIDController = new PIDController(1, .0001, 0);
    m_elevatorPIDController.setTolerance(.25);
    addRequirements(m_elevator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double feedForward = -.05;
    double speed = m_elevatorPIDController.calculate(m_elevator.GetPosition(), setpoint);
    speed = (speed > 0) ? speed + feedForward : speed - feedForward;
    speed = (speed > 1) ? 1.0 : speed;
    speed = (speed < -1) ? -1 : speed;
    m_elevator.setSpeed(speed * elevatorSpeed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_elevator.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println(m_elevatorPIDController.atSetpoint());
    if (m_elevatorPIDController.atSetpoint()) {
      return true;

    }
    else {
      return false;
    }
  }
}
