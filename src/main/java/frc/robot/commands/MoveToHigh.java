// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Carriage;

// import constants
import static frc.robot.Constants.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

public class MoveToHigh extends SequentialCommandGroup {

  private final Elevator elevator;
  private final Carriage carriage;

  /** Creates a new MoveAndBalance. */
  public MoveToHigh(XboxController controller, Elevator elevatorSubsystem, Carriage carriageSubsystem, POVButton dPadSubsystem) {

    elevator = elevatorSubsystem;
    carriage = carriageSubsystem;
    addRequirements(elevator, carriage);

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(

      new InstantCommand(() -> elevator.getLeftEncoder().setPosition(0), elevator),
      new InstantCommand(() -> elevator.getRightEncoder().setPosition(0), elevator),
      
      new RunCommand(() -> elevator.move(.3), elevator)
      .until(() -> elevator.isEncoderAtPosition(elevatorHighRotations)),

      new RunCommand(() -> carriage.move(.3), carriage)
      .until(() -> elevator.isEncoderAtPosition(elevatorHighRotations)),

      new RunCommand(() -> elevator.move(0), elevator)
      .withTimeout(1),

      new RunCommand(() -> carriage.move(0), carriage)
      .withTimeout(1)
      
    );
  }
}
