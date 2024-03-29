// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Claw;

public class MoveClaw extends CommandBase {
  /** Creates a new CloseClaw. */

  private final Claw claw; 

  public MoveClaw(Claw clawSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    claw = clawSubsystem;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // move the pistons into the closed position 
    DriverStation.reportError("****************TOGGLE***********", false);
    claw.toggleSol();
    //claw.openClaw();

  }
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
