/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

import java.util.function.BooleanSupplier;


public class RevDrivetrain extends SubsystemBase {

  private CANSparkMax LFrontWheel = new CANSparkMax(kLeftFrontPort, MotorType.kBrushless);
  private CANSparkMax RFrontWheel = new CANSparkMax(kRightFrontPort, MotorType.kBrushless);

  private CANSparkMax LRearWheel = new CANSparkMax(kLeftRearPort, MotorType.kBrushless);
  private CANSparkMax RRearWheel = new CANSparkMax(kRightRearPort, MotorType.kBrushless);

  private DifferentialDrive roboDrive = new DifferentialDrive(LFrontWheel, RFrontWheel);

  private RelativeEncoder leftEncoder = LFrontWheel.getEncoder();
  private RelativeEncoder rightEncoder = RFrontWheel.getEncoder();


  public RevDrivetrain() {
    
    LRearWheel.follow(LFrontWheel);
    RRearWheel.follow(RFrontWheel);
  }

 /* public void moveDistance (double distance) {

    leftEncoder.setPosition(0);
    new RunCommand = LFrontWheel.set(.1);
    leftEncoder.getPosition();
  }*/

  public RelativeEncoder getLeftEncoder () {
    return leftEncoder;
  }

  public RelativeEncoder getRightEncoder () {
    return rightEncoder;
  }

  public Boolean isEncoderAtPosition (double position) {
      return leftEncoder.getPosition() >= position;
  }

  public double deadband(double JoystickValue, double DeadbandCutOff) {
    double deadbandreturn;

    if (Math.abs(JoystickValue) < DeadbandCutOff) {
      deadbandreturn = 0;
    }
    else {
      deadbandreturn = JoystickValue;
    }
    
    return deadbandreturn;
  }

  public DifferentialDrive getDifferentialDrive() {
    return roboDrive;
  }
  
  @Override
  public void periodic() {
  }
}
