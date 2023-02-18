// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class POVButton extends SubsystemBase {

  private final XboxController xbox;
  private final Elevator elevator;


  /** Creates a new MoveElevator. */
  public POVButton(XboxController controller, Elevator elevatorSubsystem) {

    xbox = controller;
    elevator = elevatorSubsystem;

  }

  public Boolean topPOVPressed() {
    int dPadValue = xbox.getPOV();
    return (dPadValue < 90 | dPadValue > 270);
  }

  public Boolean bottomPOVPressed() {
    int dPadValue = xbox.getPOV();
    return (dPadValue > 90 && dPadValue < 270);
  }

  /*public void moveToPosition() {

    if (topPOVPressed()) {
      new SequentialCommandGroup(
        new InstantCommand(() -> elevator.getLeftEncoder().setPosition(0), elevator),
        new InstantCommand(() -> elevator.getRightEncoder().setPosition(0), elevator),
        new RunCommand(() -> rDrive.getDifferentialDrive().tankDrive(.3, -.3), rDrive)
        .until(() -> rDrive.isEncoderAtPosition(autoMoveRotations)),
        new RunCommand(() -> rDrive.getDifferentialDrive().tankDrive(0, 0), rDrive).withTimeout(1),
        autoBalance
      )

    } else if (bottomPOVPressed()) {

    } else {

    }

  }*/

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
