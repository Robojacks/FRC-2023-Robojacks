// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Shooter;

public class Intake extends CommandBase {
  /** Creates a new CloseClaw. */

  private final Shooter shooter; 

  public Intake(Shooter shooterSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    shooter = shooterSubsystem;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // run the shooter 
    shooter.move(-.5);
    
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // stop the shooter 
    shooter.move(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
