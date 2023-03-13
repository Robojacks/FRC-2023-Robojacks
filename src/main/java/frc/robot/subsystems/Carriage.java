// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

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

  public void setSpeed(double speed) {
    carriageMotor.set(speed);
  }

  public Boolean isEncoderAtLowPosition (double goalPosition) {
    return carriageEncoder.getPosition() <= goalPosition;
  }

  public Boolean isEncoderAtHighPosition (double goalPosition) {
    return carriageEncoder.getPosition() >= goalPosition;
  }

  public Boolean isEncoderInRange (double goalPosition, double tolerance) {
    return carriageEncoder.getPosition() + tolerance >= goalPosition && carriageEncoder.getPosition() - tolerance <= goalPosition;
  }

  // returns -1 or +1 depending on which direction the motor needs to run to get to the setpoint
  public double motorAutoSpeedSign (double goalPosition) {
    
    return (goalPosition - carriageEncoder.getPosition()) 
    / (Math.abs(goalPosition - carriageEncoder.getPosition()));
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
