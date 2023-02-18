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

   private Solenoid clawPiston = new Solenoid(compressorCANID, compressorModule, 15);

  public Claw() {
  }

  // method sets the position of the claw (open = false and closed = true)
  /*public void setClawPosition (Boolean setClawClosed) {
    
    LClawPiston.set(setClawClosed);
    RClawPiston.set(setClawClosed);
  }*/

  public void toggleSol(){
    clawPiston.toggle();
  }

  public Boolean isClawClosed () {
    return clawPiston.get();
  }

  public void closeClaw() {
    clawPiston.set(true);
  }

  public void openClaw() {
    clawPiston.set(false);
  }

  public void setClawPosition () {
      
    if (isClawClosed()) {
      openClaw();
    
    } else {
      closeClaw();
    }
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
