// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;


public class Claw extends SubsystemBase {
  /** Creates a new Claw. */

   // add a piston for moving right arm

   private Solenoid LClawPiston = new Solenoid(compressorModule, kLClawPistonPort);
   private Solenoid RClawPiston = new Solenoid(compressorModule, kRClawPistonPort);

  public Claw() {}

  // method sets the position of the claw (open or closed)
  public void setClawPosition (boolean isClawClosed) {
    LClawPiston.set(isClawClosed);
    RClawPiston.set(isClawClosed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
