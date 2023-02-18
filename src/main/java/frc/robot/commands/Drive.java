// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.RevDrivetrain;
import static edu.wpi.first.wpilibj.XboxController.Axis.*;


import static frc.robot.Constants.*;

public class Drive extends CommandBase {

  private final RevDrivetrain revDrivetrain;
  private final XboxController xbox;

  /** Creates a new MoveElevator. */
  public Drive(XboxController controller, RevDrivetrain revDrivetrainSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    xbox = controller;
    revDrivetrain = revDrivetrainSubsystem;

    addRequirements(revDrivetrain);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    revDrivetrain.drive(revDrivetrain.deadband(xbox.getRawAxis(kLeftY.value), percentDeadband), 
    revDrivetrain.deadband(xbox.getRawAxis(kRightY.value), percentDeadband));
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    revDrivetrain.drive(0,0);
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
