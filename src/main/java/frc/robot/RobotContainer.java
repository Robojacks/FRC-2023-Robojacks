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
import frc.robot.commands.Drive;
import frc.robot.commands.GoToTarget;
import frc.robot.commands.MoveElevatorAndCarriage;
import frc.robot.commands.MoveWristIn;
import frc.robot.commands.MoveWristOut;
import frc.robot.subsystems.Carriage;
import frc.robot.subsystems.Elevator;
// import RevDrivetrain subsystem
import frc.robot.subsystems.RevDrivetrain;
import frc.robot.subsystems.Wrist;

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


  /** ------ SUBSYSTEMS ------ */

  // Drive Subsystem
  private final RevDrivetrain rDrive = new RevDrivetrain();

  // Wrist Subsystem
  private final Wrist wrist = new Wrist();

  // Wrist Subsystem
  private final Elevator elevator = new Elevator();

  // Wrist Subsystem
  private final Carriage carriage = new Carriage();


  /** ------ COMMANDS ------ */

  //Drive Command
  private final Drive drive = new Drive(xbox, rDrive);

  // AutoBalance Command
  private final AutoBalance autoBalance = new AutoBalance(rDrive);

  // MoveWrist Command
  private final MoveWristIn moveWristIn = new MoveWristIn(wrist);

  // MoveWrist Command
  private final MoveWristOut moveWristOut = new MoveWristOut(wrist);

  // MoveWrist Command
  private final MoveElevatorAndCarriage moveElevatorAndCarriage = new MoveElevatorAndCarriage(xbox, elevator, carriage);


  /* --- Default Commands --- */

  // drive with controller 
  /*private Command manualDrive = new RunCommand(
    
  // drive motors run in tank drive based on joystick inputs
    () -> rDrive.getDifferentialDrive().tankDrive (
      rDrive.deadband(-xbox.getRawAxis(kLeftY.value), percentDeadband), 
      rDrive.deadband(xbox.getRawAxis(kRightY.value), percentDeadband),
      true
      ),
    rDrive
  );*/

  /* --- Container for the robot --- contains subsystems, OI devices, and commands */
  public RobotContainer() {

    // configure the button bindings
    configureButtonBindings();

    // run manualDrive and moveElevatorAndCarriage as the default commands
    //rDrive.setDefaultCommand(manualDrive);
    rDrive.setDefaultCommand(drive);
    elevator.setDefaultCommand(moveElevatorAndCarriage);

  }

  private void configureButtonBindings() {

    // vision correction
    new JoystickButton(xbox, kA.value)
    .whileTrue(new GoToTarget(rDrive));

    //auto balance
    new JoystickButton(xbox, kY.value)
    .whileTrue(autoBalance);

    // wrist in
    new JoystickButton(xbox, kLeftBumper.value)
    .onTrue(moveWristIn);

    // wrist out
    new JoystickButton(xbox, kRightBumper.value)
    .onTrue(moveWristOut);

  }

  public void init() {
  }

  public void periodic() {
    rDrive.periodic();
  }

  public Command getAutonomousCommand() {
    
    return new AutoMoveAndBalance(rDrive, autoBalance);

  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
