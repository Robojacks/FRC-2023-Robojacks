/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Timer;
// import Xbox Controller and related buttons and axes
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import static edu.wpi.first.wpilibj.XboxController.Button.*;

// import commands
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Drive;
import frc.robot.commands.PIDElevatorSet;
import frc.robot.commands.AutoBalance;
import frc.robot.commands.AutoMidConeAndAlign;
import frc.robot.commands.AutoMoveAndBalance;
import frc.robot.commands.PIDCarriageSet;
import frc.robot.commands.CollectingSetpoint;
import frc.robot.commands.GoToTarget;
import frc.robot.commands.HighShootSetpoint;
import frc.robot.commands.IntakeSetSpeed;
import frc.robot.commands.MMoveWristIn;
import frc.robot.commands.MMoveWristLevel;
import frc.robot.commands.MMoveWristOut;
import frc.robot.commands.MidShootSetpoint;
import frc.robot.commands.MoveClaw;
import frc.robot.commands.MoveElevatorAndCarriage;
import frc.robot.commands.MoveWristOut;
import frc.robot.commands.ShootAndIntake;
import frc.robot.commands.MoveWristIn;
import frc.robot.commands.MoveWristLevel1;
import frc.robot.commands.MoveWristLevel2;
import frc.robot.commands.MoveWristLevel3;
import frc.robot.commands.MoveWristLevel4;
import frc.robot.commands.ShootAuto;
import frc.robot.commands.StartConfigSetpoint;
import frc.robot.commands.SubstationSetpoint;
import frc.robot.commands.PIDWrist;
// import subsystems
import frc.robot.subsystems.RevDrivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Wrist;
import frc.robot.subsystems.Carriage;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Elevator;

// import constants
import static frc.robot.Constants.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {

  SendableChooser<Command> autoChooser = new SendableChooser<>();
  private Command m_autoSelected;

  // Drive Controller 1
  private XboxController xbox1 = new XboxController(kXbox1Port);

  // Drive Controller 2
  private XboxController xbox2 = new XboxController(kXbox2Port);

  //Compressor phCompressor = new Compressor(1, PneumaticsModuleType.REVPH);


  /** ------ SUBSYSTEMS ------ */

  // Drive Subsystem
  private final RevDrivetrain rDrive = new RevDrivetrain();

  // Wrist Subsystem
  private final Wrist wrist = new Wrist();

  // Wrist Subsystem
  private final Elevator elevator = new Elevator();

  // Wrist Subsystem
  private final Carriage carriage = new Carriage();

  // Shooter Subsystem
  private final Shooter shooter = new Shooter();

  // Claw Subsystem
  private final Claw claw = new Claw();
  

  /** ------ COMMANDS ------ */

  //Drive Command
  private final Drive drive = new Drive(xbox1, rDrive);

  // AutoBalance Command
  private final AutoBalance autoBalance = new AutoBalance(rDrive);

  // MoveWrist Command
  private final MoveElevatorAndCarriage moveElevatorAndCarriage = new MoveElevatorAndCarriage(xbox2, elevator, carriage);

  // ShootAuto Command
  private final ShootAuto shootAuto = new ShootAuto(shooter);

  // Shoot Command
  private final ShootAndIntake shootAndIntake = new ShootAndIntake(xbox1, shooter);

  // IntakeSetSpeed Command
  private final IntakeSetSpeed intakeSetSpeed = new IntakeSetSpeed(shooter);

  // MoveClaw Command
  private final MoveClaw moveClaw = new MoveClaw(claw);

  /*** --- Move Wrist Duplicate Commands --- */
      /* these are duplicated for each different command by which it is used */

  // MoveWrist Command (used with setpoints)
  private final MoveWristOut moveWristOut = new MoveWristOut(wrist);

  // MoveWrist Command (used with setpoints)
  private final MoveWristIn moveWristIn = new MoveWristIn(wrist);

  // MoveWrist Command (used with setpoints)
  //private final MoveWristLevel1 moveWristLevel1 = new MoveWristLevel1(wrist);

  // MoveWrist Command (used with setpoints)
  private final MoveWristLevel2 moveWristLevel2 = new MoveWristLevel2(wrist);

  // MoveWrist Command (used with setpoints)
  private final MoveWristLevel3 moveWristLevel3 = new MoveWristLevel3(wrist);

  // MoveWrist Command (used with setpoints)
  private final MoveWristLevel4 moveWristLevel4 = new MoveWristLevel4(wrist);

  // Duplicate MoveWristOut Command (used alone)
  private final MMoveWristOut mMoveWristOut = new MMoveWristOut(wrist);

  // Duplicate MoveWristIn Command (used alone)
  private final MMoveWristIn mMoveWristIn = new MMoveWristIn(wrist);

  // Duplicate MoveWristLevel Command (used alone)
  private final MMoveWristLevel mMoveWristLevel = new MMoveWristLevel(wrist);
 
  
  // StartConfigSetpoint Command
  private final StartConfigSetpoint startConfigSetpoint = new StartConfigSetpoint(elevator, carriage, moveWristIn);
 
  // CollectingSetpoint Command
  private final CollectingSetpoint collectingSetpoint = new CollectingSetpoint(elevator, carriage, moveWristOut);

  // MidShootSetpoint Command
  private final MidShootSetpoint midShootSetpoint = new MidShootSetpoint(elevator, carriage, moveWristLevel2);
  
  // HighShootSetpoint Command
  private final HighShootSetpoint highShootSetpoint = new HighShootSetpoint(elevator, carriage, moveWristLevel3);

   private final SubstationSetpoint substationSetpoint = new SubstationSetpoint(elevator, carriage, moveWristLevel4);

  /* --- Container for the robot --- contains subsystems, OI devices, and commands */
  public RobotContainer() {

    ShuffleboardTab autoTab = Shuffleboard.getTab("Autonomous");

    autoChooser.setDefaultOption("Auto 1", auto1());
    autoChooser.addOption("Auto 2", auto2());

    autoTab.add(autoChooser);

    // configure the button bindings
    configureButtonBindings();

    // Default Commands

    rDrive.setDefaultCommand(drive);
    shooter.setDefaultCommand(shootAndIntake);
    //elevator.setDefaultCommand(moveElevatorAndCarriage);

  }

  private void configureButtonBindings() {

    // vision correction
    /*new JoystickButton(xbox, kStart.value)
    .whileTrue(new GoToTarget(rDrive));*/

    // auto balance
    /*new JoystickButton(xbox1, kBack.value)
    .whileTrue(autoBalance);*/

    // wrist in (xbox 2)
    new JoystickButton(xbox2, kLeftBumper.value)
    .onTrue(mMoveWristIn);

    // wrist out (xbox 1)
    new JoystickButton(xbox1, kRightBumper.value)
    .onTrue(mMoveWristOut);

    // wrist out (xbox 2)
    new JoystickButton(xbox2, kRightBumper.value)
    .onTrue(mMoveWristOut);

    // wrist level (xbox 2)
    new JoystickButton(xbox2, kB.value)
    .onTrue(mMoveWristLevel);

    // move claw (xbox 1)
    new JoystickButton(xbox1, kY.value)
    .onTrue(moveClaw);

    // move claw (xbox 2)
    new JoystickButton(xbox2, kX.value)
    .onTrue(moveClaw);
      
    // move to start config (xbox 1)
    new JoystickButton(xbox1, kLeftBumper.value)
    .onTrue(startConfigSetpoint);

    // high shoot setpoint (xbox 2)
    new JoystickButton(xbox2, kY.value)
    .onTrue(highShootSetpoint);

    // mid shoot setpoint (xbox 2)
    new JoystickButton(xbox2, kA.value)
    .onTrue(midShootSetpoint);

    // substation setpoint (xbox 2)
    new JoystickButton(xbox2, kLeftStick.value)
    .onTrue(substationSetpoint);

  }

  public void init() {
  }

  public void periodic() {
    rDrive.periodic();
  }

  public Command getAutonomousCommand() {
    m_autoSelected = autoChooser.getSelected();
    return m_autoSelected;
  }

  private Command auto1() {
    return new SequentialCommandGroup(

      new ParallelCommandGroup(
        new PIDElevatorSet(elevator, elevatorHighRotations),
        new PIDCarriageSet(carriage, -carriageOutRotations),
        new PIDWrist(wrist, wristLevelRotations)
      ),

      new InstantCommand(() -> claw.closeClaw()),
      new WaitCommand(.5),
      
      new ParallelCommandGroup(
        new PIDElevatorSet(elevator, elevatorLowRotations),
        new PIDCarriageSet(carriage, -carriageInRotations),
        new PIDWrist(wrist, wristInRotations)
      ),

      //new ParallelCommandGroup(
        new RunCommand(() -> rDrive.drive(.5, .5), rDrive)
        .withTimeout(4.3),
         /*.until(() -> rDrive.isEncoderAtPosition(autoMoveRotations))*/
         //new WristSet(wrist, wristOutRotations)
      //)

      new RunCommand(() -> rDrive.drive(-.5, .5), rDrive)
      .withTimeout(1.75)/*,
      
      new ParallelCommandGroup(
        new RunCommand(() -> rDrive.drive(-.3, -.3), rDrive),
        new RunCommand(() -> shooter.setSpeed(.7), shooter)
      ).withTimeout(1)*/

    );
  }

  private Command auto2() {
    return null;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
