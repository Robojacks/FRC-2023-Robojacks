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

public class Wrist extends SubsystemBase {

  private CANSparkMax wristMotor = new CANSparkMax(wristPort, MotorType.kBrushless);
  
  private RelativeEncoder wristEncoder = wristMotor.getEncoder();

  public Wrist() {
    
    wristMotor.restoreFactoryDefaults();
    wristMotor.setIdleMode(IdleMode.kCoast);
    wristMotor.burnFlash();
  }

  public RelativeEncoder getWristEncoder () {
    return wristEncoder;
  }

  public Boolean isEncoderAtPosition (double position) {
      return wristEncoder.getPosition() >= position;
  }

  public void move(double speed) {
    wristMotor.set(speed);
  }
  
  @Override
  public void periodic() {
  }
}