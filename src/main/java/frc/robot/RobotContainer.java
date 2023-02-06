/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

// import Xbox Controller and related buttons and axes
import edu.wpi.first.wpilibj.XboxController;

import static edu.wpi.first.wpilibj.XboxController.Axis.*;
import static edu.wpi.first.wpilibj.XboxController.Button.*;

// import commands
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoBalance;
import frc.robot.commands.AutoMoveAndBalance;
import frc.robot.commands.GoToTarget;

// import RevDrivetrain subsystem
import frc.robot.subsystems.RevDrivetrain;

// import constants
import static frc.robot.Constants.*;

import com.kauailabs.navx.frc.AHRS;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {

  // Drive Controller
  private XboxController xbox = new XboxController(kXboxPort);

  // Drive Subsystem
  private final RevDrivetrain rDrive = new RevDrivetrain();

  /* --- Default Commands --- */

  // drive with controller 
  private Command manualDrive = new RunCommand(
    
  // drive motors run in tank drive based on joystick inputs
    () -> rDrive.getDifferentialDrive().tankDrive (
      rDrive.deadband(-xbox.getRawAxis(kLeftY.value), percentDeadband), 
      rDrive.deadband(xbox.getRawAxis(kRightY.value), percentDeadband),
      true
      ),
    rDrive
  );

  /* --- Container for the robot --- contains subsystems, OI devices, and commands */
  public RobotContainer() {

    // configure the button bindings
    configureButtonBindings();

    // run manualDrive and moveArm as the default commands
    rDrive.setDefaultCommand(manualDrive);
  }

  private void configureButtonBindings() {

    // vision correction
    new JoystickButton(xbox, kA.value)
    .whileTrue(new GoToTarget(rDrive));

    //auto balance, only used when on slanted surface
    new JoystickButton(xbox, kY.value)
    .whileTrue(new AutoBalance(rDrive));

  }

  public void init() {
  }

  public void periodic() {
  }

  public Command getAutonomousCommand() {
    return new AutoMoveAndBalance(rDrive);
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
