// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;


public class Elevator extends SubsystemBase {

  private CANSparkMax LElevator = new CANSparkMax(kLeftElevatorPort, MotorType.kBrushless);
  private CANSparkMax RElevator = new CANSparkMax(kRightElevatorPort, MotorType.kBrushless);



  /** Creates a new Elevator. */
  public Elevator() {

    LElevator.restoreFactoryDefaults();
    LElevator.setIdleMode(IdleMode.kCoast);
    LElevator.burnFlash();

    RElevator.restoreFactoryDefaults();
    RElevator.setIdleMode(IdleMode.kCoast);
    RElevator.follow(LElevator);
    RElevator.burnFlash();
  }

  public void move(double speed) {
    LElevator.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
