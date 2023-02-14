/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

public class Shooter extends SubsystemBase {

  private CANSparkMax LShooterMotor = new CANSparkMax(kLShooterPort, MotorType.kBrushless);
  private CANSparkMax RShooterMotor = new CANSparkMax(kRShooterPort, MotorType.kBrushless);
  
  private RelativeEncoder LShooterEncoder = LShooterMotor.getEncoder();
  private RelativeEncoder RShooterEncoder = RShooterMotor.getEncoder();


  public Shooter() {
    
    LShooterMotor.restoreFactoryDefaults();
    LShooterMotor.setIdleMode(IdleMode.kCoast);
    LShooterMotor.setInverted(false);
    LShooterMotor.burnFlash();

    RShooterMotor.restoreFactoryDefaults();
    RShooterMotor.setIdleMode(IdleMode.kCoast);
    RShooterMotor.setInverted(true);
    RShooterMotor.follow(LShooterMotor);
    RShooterMotor.burnFlash();
  }

  public RelativeEncoder getLShooterEncoder () {
    return LShooterEncoder;
  }

  public RelativeEncoder getRShooterEncoder () {
    return RShooterEncoder;
  }


  public Boolean isEncoderAtPosition (double position) {
      return LShooterEncoder.getPosition() >= position | LShooterEncoder.getPosition() >= position;
  }

  public void move(double speed) {
    LShooterMotor.set(speed);
  }
  
  @Override
  public void periodic() {
  }
}
