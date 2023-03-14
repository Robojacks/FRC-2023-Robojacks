// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;


public class Elevator extends SubsystemBase {

  private CANSparkMax lElevatorMotor = new CANSparkMax(kLeftElevatorPort, MotorType.kBrushless);
  private CANSparkMax rElevatorMotor = new CANSparkMax(kRightElevatorPort, MotorType.kBrushless);

  private RelativeEncoder lElevatorEncoder = lElevatorMotor.getEncoder();
  private RelativeEncoder rElevatorEncoder = rElevatorMotor.getEncoder();


  /** Creates a new Elevator. */
  public Elevator() {

    lElevatorMotor.restoreFactoryDefaults();
    lElevatorMotor.setIdleMode(IdleMode.kBrake);
    lElevatorEncoder.setPosition(0);
    lElevatorMotor.burnFlash();

    rElevatorMotor.restoreFactoryDefaults();
    rElevatorMotor.setIdleMode(IdleMode.kBrake);
    rElevatorMotor.follow(lElevatorMotor);
    rElevatorEncoder.setPosition(0);
    rElevatorMotor.burnFlash();
  }

  public RelativeEncoder getlElevatorEncoder () {
    return lElevatorEncoder;
  }

  public RelativeEncoder getrElevatorEncoder () {
    return rElevatorEncoder;
  }

  public Boolean isEncoderAtPosition (double position) {
    return lElevatorEncoder.getPosition() >= position | rElevatorEncoder.getPosition() >= position;
  }

  public void setSpeed(double speed) {
    lElevatorMotor.set(speed);
  }

  public Boolean isEncoderAtLowPosition (double goalPosition) {
    return lElevatorEncoder.getPosition() <= goalPosition | rElevatorEncoder.getPosition() <= goalPosition;
  }

  public Boolean isEncoderAtHighPosition (double goalPosition) {
    return lElevatorEncoder.getPosition() >= goalPosition | rElevatorEncoder.getPosition() >= goalPosition;
  }

  public Boolean isEncoderInRange (double goalPosition, double tolerance) {
    return 
      lElevatorEncoder.getPosition() + tolerance >= goalPosition && lElevatorEncoder.getPosition() - tolerance <= goalPosition |
      rElevatorEncoder.getPosition() + tolerance >= goalPosition && rElevatorEncoder.getPosition() - tolerance <= goalPosition;
  }

  /** Returns -1 or +1 depending on which direction the motor needs to run to get to the setpoint. */
  public double motorAutoSpeedSign (double goalPosition) {
    
    return (goalPosition - lElevatorEncoder.getPosition()) 
    / (Math.abs(goalPosition - lElevatorEncoder.getPosition()));
    
  }

  @Override
  public void periodic() {

    SmartDashboard.putNumber("Left Elevator Encoder Counts", lElevatorEncoder.getPosition());
    SmartDashboard.putNumber("Right Elevator Encoder Counts", rElevatorEncoder.getPosition());

    SmartDashboard.putNumber("Motor Direction (to go to mid position)", motorAutoSpeedSign(elevatorMidRotations));


    // This method will be called once per scheduler run
  }
}
