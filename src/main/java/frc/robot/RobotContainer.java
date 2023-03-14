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
import frc.robot.commands.Intake;
import frc.robot.commands.LowShootSetpoint;
import frc.robot.commands.MidShootSetpoint;
import frc.robot.commands.MoveClaw;
import frc.robot.commands.MoveElevatorAndCarriage;
import frc.robot.commands.MoveWristOut;
import frc.robot.commands.MoveWristIn;
import frc.robot.commands.MoveWristLevel;
import frc.robot.commands.Shoot;
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
  
  

  // Drive Controller
  private XboxController xbox = new XboxController(kXboxPort);

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
  private final Drive drive = new Drive(xbox, rDrive);

  // AutoBalance Command
  private final AutoBalance autoBalance = new AutoBalance(rDrive);

  // MoveWrist Command
  private final MoveWristOut moveWristOut = new MoveWristOut(wrist);

  // MoveWrist Command
  private final MoveWristIn moveWristIn = new MoveWristIn(wrist);

  // MoveWrist Command
  private final MoveWristLevel moveWristLevel = new MoveWristLevel(wrist);

  // MoveWrist Command
  private final MoveElevatorAndCarriage moveElevatorAndCarriage = new MoveElevatorAndCarriage(xbox, elevator, carriage);

  // Shoot Command
  private final Shoot shoot = new Shoot(shooter);

  // Intake Command
  private final Intake intake = new Intake(shooter);

  // MoveClaw Command
  private final MoveClaw moveClaw = new MoveClaw(claw);
 
  
  // StartConfigSetpoint Command
  private final StartConfigSetpoint startConfigSetpoint = new StartConfigSetpoint(elevator, carriage, moveWristIn);
/* 
  // CollectingSetpoint Command
  private final CollectingSetpoint collectingSetpoint = new CollectingSetpoint(elevator, carriage, moveWristOut);
 
  // LowShootSetpoint Command
  private final LowShootSetpoint lowShootSetpoint = new LowShootSetpoint(elevator, carriage, moveWristLevel);

  // MidShootSetpoint Command
  private final MidShootSetpoint midShootSetpoint = new MidShootSetpoint(elevator, carriage, moveWristLevel);
  */
  // HighShootSetpoint Command
  //private final HighShootSetpoint highShootSetpoint = new HighShootSetpoint(elevator, carriage, moveWristLevel);

  // CloseClaw Command
  //private final CloseClaw closeClaw = new CloseClaw(claw);

  // OpenClaw Command
  //private final OpenClaw openClaw = new OpenClaw(claw);


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
    /*new JoystickButton(xbox, kStart.value)
    .whileTrue(new GoToTarget(rDrive));*/

    //auto balance
    new JoystickButton(xbox, kBack.value)
    .whileTrue(autoBalance);

    // wrist in
    new JoystickButton(xbox, kLeftBumper.value)
    .onTrue(moveWristIn);

    // wrist out
    new JoystickButton(xbox, kRightBumper.value)
    .onTrue(moveWristOut);

    // wrist level *** the B button is being used by start config -- need to rethink buttonmapping ***
    new JoystickButton(xbox, kStart.value)
    .onTrue(moveWristLevel);

    // shoot
    new JoystickButton(xbox, kA.value)
    .onTrue(shoot);

    // intake
    new JoystickButton(xbox, kX.value)
    .whileTrue(intake);

    // move claw
    new JoystickButton(xbox, kY.value)
    .onTrue(moveClaw);
 
      
    // move to start config
    new JoystickButton(xbox, kB.value)
    .onTrue(startConfigSetpoint);
/* 
    // move to low shoot setpoint when left POV pressed
    new Trigger(()-> {
      
      if(xbox.getPOV() == 270)
        return true;
      else
        return false;
      })
      .onTrue(lowShootSetpoint);

    // move to mid shoot setpoint when right POV pressed
    new Trigger(()-> {
      
      if(xbox.getPOV() == 90)
        return true;
      else
        return false;
      })
      .onTrue(midShootSetpoint);
 */
    // move to high shoot setpoint when high POV pressed
    /*new Trigger(()-> {
      
      if(xbox.getPOV() == 0)
        return true;
      else
        return false;
      })
      .onTrue(highShootSetpoint);*/
 /* 
    // move to collecting setpoint when low POV pressed
    new Trigger(()-> {
      
      if(xbox.getPOV() == 180)
        return true;
      else
        return false;
      })
      .onTrue(collectingSetpoint);
*/

    /*// close claw
    new JoystickButton(xbox, kA.value)
    .onTrue(closeClaw);

    // close claw
    new JoystickButton(xbox, kA.value)
    .onTrue(closeClaw);*/

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
