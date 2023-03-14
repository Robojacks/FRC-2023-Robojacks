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
import frc.robot.subsystems.Wrist;

// import constants
import static frc.robot.Constants.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

public class MidShootSetpoint extends SequentialCommandGroup {

  // subsystems
  private final Elevator elevator;
  private final Carriage carriage;
  private final MoveWristLevel moveWristLevel;


  /** Creates a new MoveAndBalance. */
  public MidShootSetpoint (Elevator elevatorSubsystem, Carriage carriageSubsystem, MoveWristLevel moveWristLevelCommand) {

    elevator = elevatorSubsystem;
    carriage = carriageSubsystem;
    moveWristLevel = moveWristLevelCommand;

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    addCommands(

      new ParallelCommandGroup(

        new RunCommand(() -> elevator.setSpeed(elevator.motorAutoSpeedSign(elevatorMidRotations) * elevatorSpeed))
        .until(() -> elevator.isEncoderInRange(elevatorMidRotations, elevatorRotationsTolerance)),
        
        /*new RunCommand(() -> carriage.setSpeed(carriage.motorAutoSpeedSign(-carriageMidRotations) * -carriageSpeed))
        .until(() -> carriage.isEncoderInRange(-carriageMidRotations, carriageRotationsTolerance))*/

        new RunCommand(() -> carriage.setSpeed(-carriageSpeed))
        .until(() -> carriage.isEncoderAtLowerBound(-carriageMidRotations))

        //moveWristLevel
      ),
      
      new RunCommand(() -> elevator.setSpeed(0)).withTimeout(.1),
      new RunCommand(() -> carriage.setSpeed(0)).withTimeout(.1)


    );
  }
}
