/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

// import Xbox Controller and related buttons and axes
import edu.wpi.first.wpilibj.XboxController;
import static edu.wpi.first.wpilibj.XboxController.Button.*;

// import commands
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Drive;
import frc.robot.commands.AutoBalance;
import frc.robot.commands.AutoMoveAndBalance;
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
import frc.robot.commands.ShootAuto;
import frc.robot.commands.StartConfigSetpoint;
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


  /* --- Container for the robot --- contains subsystems, OI devices, and commands */
  public RobotContainer() {


    // configure the button bindings
    configureButtonBindings();

    // run manualDrive and moveElevatorAndCarriage as the default commands
    //rDrive.setDefaultCommand(manualDrive);
    rDrive.setDefaultCommand(drive);
    elevator.setDefaultCommand(moveElevatorAndCarriage);
    shooter.setDefaultCommand(shootAndIntake);

  }

  private void configureButtonBindings() {

    // vision correction
    /*new JoystickButton(xbox, kStart.value)
    .whileTrue(new GoToTarget(rDrive));*/

    //auto balance
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

    // shoot auto
    /*new JoystickButton(xbox1, kA.value)
    .onTrue(shootAuto);*/

    // intake set speed
    /*new JoystickButton(xbox1, kX.value)
    .whileTrue(intakeSetSpeed);*/

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


    /* 
    // move to mid shoot setpoint when right or left POV pressed
    new Trigger(()-> {
      
      if(xbox1.getPOV() == 90 | xbox1.getPOV() == 270)
        return true;
      else
        return false;
      })
      .onTrue(midShootSetpoint);
 
    // move to high shoot setpoint when high POV pressed
    new Trigger(()-> {
      
      if(xbox1.getPOV() == 0)
        return true;
      else
        return false;
      })
      .onTrue(highShootSetpoint);
  
    // move to collecting setpoint when low POV pressed
    new Trigger(()-> {
      
      if(xbox1.getPOV() == 180)
        return true;
      else
        return false;
      })
      .onTrue(collectingSetpoint);*/

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
