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


public class Carriage extends SubsystemBase {

  private CANSparkMax carriageMotor = new CANSparkMax(carriagePort, MotorType.kBrushless);

  private RelativeEncoder carriageEncoder = carriageMotor.getEncoder();



  /** Creates a new Elevator. */
  public Carriage() {

    carriageMotor.restoreFactoryDefaults();
    carriageMotor.setIdleMode(IdleMode.kBrake);

    carriageEncoder.setPosition(0);

    carriageMotor.burnFlash();
  }

  public double GetPosition() {
    return carriageEncoder.getPosition();
  }

  public void setSpeed(double speed) {
    carriageMotor.set(speed);
  }

  /** Use isEncoderAtLowerBound if the motor is moving in a negative direction, otherwise use isEncoderAtUpperBound */
  public Boolean isEncoderAtLowerBound (double goalPosition) {
    return carriageEncoder.getPosition() <= goalPosition;
  }
  
  /** Use isEncoderAtUpperBound if the motor is moving in a positive direction, otherwise use isEncoderAtLowerBound */
  public Boolean isEncoderAtUpperBound (double goalPosition) {
    return carriageEncoder.getPosition() >= goalPosition;
  }

  public Boolean isEncoderInRange (double goalPosition, double tolerance) {
    return carriageEncoder.getPosition() + tolerance >= goalPosition && carriageEncoder.getPosition() - tolerance <= goalPosition;
  }

  /** Returns -1 or +1 depending on which direction the motor needs to run to get to the setpoint. */
  public double motorAutoSpeedSign (double goalPosition) {
    
    return (goalPosition - carriageEncoder.getPosition()) 
    / (Math.abs(goalPosition - carriageEncoder.getPosition()));
    
  }

  @Override
  public void periodic() {

    SmartDashboard.putNumber("Carriage Encoder Counts", carriageEncoder.getPosition());
    SmartDashboard.putNumber("Motor Direction (to go to mid position)", motorAutoSpeedSign(-carriageMidRotations));


    // This method will be called once per scheduler run
  }
}
