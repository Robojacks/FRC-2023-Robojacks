// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShootAndIntake extends CommandBase {

  private final Shooter shooter;
  private final XboxController xbox;

  /** Creates a new MoveElevator. */
  public ShootAndIntake(XboxController controller, Shooter shooterSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    xbox = controller;
    shooter = shooterSubsystem;
    addRequirements(shooter);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    shooter.setSpeed(xbox.getRawAxis(XboxController.Axis.kRightTrigger.value) - 
      xbox.getRawAxis(XboxController.Axis.kLeftTrigger.value));    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {  
    shooter.setSpeed(0); 
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
