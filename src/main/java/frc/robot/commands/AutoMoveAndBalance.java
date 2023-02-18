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
  private final RevDrivetrain rDrive;

  // AutoBalance Command
  private final AutoBalance autoBalance;

  /** Creates a new MoveAndBalance. */
  public AutoMoveAndBalance(RevDrivetrain drive, AutoBalance balance) {

    rDrive = drive;
    autoBalance = balance;

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(

      new InstantCommand(() -> rDrive.getLeftEncoder().setPosition(0), rDrive),
      new InstantCommand(() -> rDrive.getRightEncoder().setPosition(0), rDrive),
      new RunCommand(() -> rDrive.getDifferentialDrive().tankDrive(.3, -.3), rDrive)
      .until(() -> rDrive.isEncoderAtPosition(autoMoveRotations)),
      new RunCommand(() -> rDrive.getDifferentialDrive().tankDrive(0, 0), rDrive).withTimeout(1),
      autoBalance
      
    );
  }
}
