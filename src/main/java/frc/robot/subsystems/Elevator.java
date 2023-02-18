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


public class Elevator extends SubsystemBase {

  private CANSparkMax LElevator = new CANSparkMax(kLeftElevatorPort, MotorType.kBrushless);
  private CANSparkMax RElevator = new CANSparkMax(kRightElevatorPort, MotorType.kBrushless);

  private RelativeEncoder leftEncoder = LElevator.getEncoder();
  private RelativeEncoder rightEncoder = RElevator.getEncoder();


  /** Creates a new Elevator. */
  public Elevator() {

    LElevator.restoreFactoryDefaults();
    LElevator.setIdleMode(IdleMode.kBrake);
    LElevator.burnFlash();

    RElevator.restoreFactoryDefaults();
    RElevator.setIdleMode(IdleMode.kBrake);
    RElevator.follow(LElevator);
    RElevator.burnFlash();
  }

  public RelativeEncoder getLeftEncoder () {
    return leftEncoder;
  }

  public RelativeEncoder getRightEncoder () {
    return rightEncoder;
  }

  public Boolean isEncoderAtPosition (double position) {
    return leftEncoder.getPosition() >= position | rightEncoder.getPosition() >= position;
  }

  public void move(double speed) {
    LElevator.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
