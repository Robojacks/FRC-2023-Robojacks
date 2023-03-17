// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Carriage;
import frc.robot.subsystems.Elevator;

// import constants
import static frc.robot.Constants.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

public class StartConfigSetpoint extends SequentialCommandGroup {

  // subsystems
  private final Elevator elevator;
  private final Carriage carriage;
  private final MoveWristIn moveWristIn;

  
  /** Creates a new MoveAndBalance. */
  public StartConfigSetpoint(Elevator elevatorSubsystem, Carriage carriageSubsystem, MoveWristIn moveWristInCommand) {

    elevator = elevatorSubsystem;
    carriage = carriageSubsystem;
    moveWristIn = moveWristInCommand;

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ParallelCommandGroup(

        new RunCommand(() -> elevator.setSpeed(-elevatorSpeed))
        .until(() -> elevator.isEncoderAtLowPosition(elevatorLowRotations)),
        
        new RunCommand(() -> carriage.setSpeed(carriageSpeed))
        .until(() -> carriage.isEncoderAtUpperBound(-carriageInRotations)),

        moveWristIn

      ),
      
      new RunCommand(() -> elevator.setSpeed(0)).withTimeout(.1),
      new RunCommand(() -> carriage.setSpeed(0)).withTimeout(.1)
      
      

    );
  }
}
