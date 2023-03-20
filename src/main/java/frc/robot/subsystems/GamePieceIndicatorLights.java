// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;


public class GamePieceIndicatorLights extends SubsystemBase {
  /** Creates a new GamePieceIndicatorLights. */

   // add a piston for moving right arm

   private Solenoid yellowLight = new Solenoid(compressorCANID, compressorModule, 0);
   private Solenoid purpleLight = new Solenoid(compressorCANID, compressorModule, 1);



  public GamePieceIndicatorLights() {
  }

  // method sets the position of the claw (open = false and closed = true)
  /*public void setClawPosition (Boolean setClawClosed) {
    
    LClawPiston.set(setClawClosed);
    RClawPiston.set(setClawClosed);
  }*/

  public void toggleYellow(){
    yellowLight.toggle();
  }

  public void togglePurple(){
    purpleLight.toggle();
  }

  public Boolean isYellowOff () {
    return yellowLight.get();
  }

  public Boolean isPurpleOff () {
    return purpleLight.get();
  }

  public void YellowOff() {
    yellowLight.set(false);
  }

  public void PurpleOff() {
    purpleLight.set(false);
  }

  public void YellowOn() {
    yellowLight.set(true);
  }

  public void PurpleOn() {
    purpleLight.set(true);
  }

  public void setYellow () {
      
    if (isYellowOff()) {
      YellowOn();
      System.out.println("Yellow Light On");
    } else {
      YellowOff();
    }
    
  }

  public void setPurple () {
      
    if (isPurpleOff()) {
      PurpleOn();
      System.out.println("Purple Light On");
    
    } else {
      PurpleOff();
    }
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
