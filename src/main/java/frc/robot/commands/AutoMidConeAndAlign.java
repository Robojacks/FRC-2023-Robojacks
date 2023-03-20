// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Carriage;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.RevDrivetrain;
import frc.robot.subsystems.Wrist;
import static frc.robot.Constants.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoMidConeAndAlign extends SequentialCommandGroup {

  private final RevDrivetrain rDrive;
  private final Carriage carriage;
  private final Elevator elevator;
  private final Wrist wrist;
  private final Claw claw;
  private final HighShootSetpoint shootSetpoint;
  private final StartConfigSetpoint configSetpoint;

  /** Creates a new AutoMidConeAndAlign. */
  public AutoMidConeAndAlign(RevDrivetrain drive, Carriage carriageSubsystem, Elevator elevatorSubsystem, Wrist wristSubsystem, Claw clawSubsystem, HighShootSetpoint shootSetpointCommand, StartConfigSetpoint configSetpointCommand) {
    rDrive = drive;
    carriage = carriageSubsystem;
    elevator = elevatorSubsystem;
    wrist = wristSubsystem;
    claw = clawSubsystem;
    shootSetpoint = shootSetpointCommand;
    configSetpoint = configSetpointCommand;

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
     
    
    
    
    
    
    
    
    
    
    
    //extend to mid goal
      //shootSetpoint//,
      // //drop cone
      // new InstantCommand(() -> claw.openClaw()),
      // //return to starting position
      // configSetpoint,
      // //back up
      // new RunCommand(() -> rDrive.getDifferentialDrive().tankDrive(-0.5, -0.5), rDrive)
      // .until(() -> rDrive.isEncoderAtPosition(autoMoveRotations)),
      // //spin to face cone
      // new RunCommand(() -> rDrive.getDifferentialDrive().tankDrive(-0.5, 0.5), rDrive).withTimeout(1)

    );
  }
}
