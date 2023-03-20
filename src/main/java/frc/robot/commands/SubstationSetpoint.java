// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Carriage;
import frc.robot.subsystems.Elevator;
import static frc.robot.Constants.*;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SubstationSetpoint extends SequentialCommandGroup {

  private final Elevator elevator;
  private final Carriage carriage;
  private final MoveWristLevel4 moveWristLevel;

  public SubstationSetpoint(Elevator elevatorSubsystem, Carriage carriageSubsystem, MoveWristLevel4 moveWristLevelCommand) {
    
    elevator = elevatorSubsystem;
    carriage = carriageSubsystem;
    moveWristLevel = moveWristLevelCommand;

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ParallelCommandGroup(

      new RunCommand(() -> elevator.setSpeed(elevatorSpeed))
      .until(() -> elevator.isEncoderAtHighPosition(elevatorSubstationRotations)),
      
      new RunCommand(() -> carriage.setSpeed(carriageSpeed))
      .until(() -> carriage.isEncoderAtLowerBound(-carriageInRotations)),

      moveWristLevel

    ),
    
    new RunCommand(() -> elevator.setSpeed(0)).withTimeout(.1),
    new RunCommand(() -> carriage.setSpeed(0)).withTimeout(.1)


    );
  }
}
