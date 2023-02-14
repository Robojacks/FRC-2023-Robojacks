// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Carriage;

public class MoveElevatorAndCarriage extends CommandBase {

  private final Elevator elevator;
  private final Carriage carriage;
  private final XboxController xbox;

  /** Creates a new MoveElevator. */
  public MoveElevatorAndCarriage(XboxController controller, Elevator elevatorSubsystem, Carriage carriageSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    xbox = controller;
    elevator = elevatorSubsystem;
    carriage = carriageSubsystem;
    addRequirements(elevator, carriage);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    elevator.move(xbox.getRawAxis(XboxController.Axis.kRightTrigger.value)-
    xbox.getRawAxis(XboxController.Axis.kLeftTrigger.value));  

    carriage.move(xbox.getRawAxis(XboxController.Axis.kRightTrigger.value)-
    xbox.getRawAxis(XboxController.Axis.kLeftTrigger.value));  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevator.move(0);  
    carriage.move(0); 
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
