// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Wrist;

// import constants
import static frc.robot.Constants.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

public class MoveWristIn extends SequentialCommandGroup {

  // Drive Subsystem
  private final Wrist wrist;


  /** Creates a new MoveAndBalance. */
  public MoveWristIn(Wrist wristSubsystem) {

    wrist = wristSubsystem;

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new InstantCommand(() -> wrist.getWristEncoder().setPosition(0), wrist),
      new RunCommand(() -> wrist.move(.3))
      .until(() -> wrist.isEncoderAtPosition(wristRotations)),
      new RunCommand(() -> wrist.move(0)).withTimeout(1)
    );
  }
}
