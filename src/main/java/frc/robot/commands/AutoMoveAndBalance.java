// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.RevDrivetrain;

// import constants
import static frc.robot.Constants.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

public class AutoMoveAndBalance extends SequentialCommandGroup {

  // Drive Subsystem
  private final RevDrivetrain rDrive = new RevDrivetrain();

  /** Creates a new MoveAndBalance. */
  public AutoMoveAndBalance() {


    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new InstantCommand(() -> rDrive.getLeftEncoder().getPosition(), rDrive),
      new RunCommand(() -> rDrive.getDifferentialDrive().tankDrive(.1, .1), rDrive)
      .until(() -> rDrive.isEncoderAtPosition(goalEncoderRotations))
    );
  }
}
