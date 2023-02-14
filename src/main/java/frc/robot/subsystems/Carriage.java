// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;


public class Carriage extends SubsystemBase {

  private CANSparkMax carriageMotor = new CANSparkMax(carriagePort, MotorType.kBrushless);



  /** Creates a new Elevator. */
  public Carriage() {

    carriageMotor.restoreFactoryDefaults();
    carriageMotor.setIdleMode(IdleMode.kCoast);
    carriageMotor.burnFlash();
  }

  public void move(double speed) {
    carriageMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
