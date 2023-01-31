package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Gains.*;

public class Update {

    public Update() {

        // display PID values (angle)
        SmartDashboard.putNumber("P value(angle)", angleCorrection.kP);
        SmartDashboard.putNumber("I value(angle)", angleCorrection.kI);
        SmartDashboard.putNumber("D value(angle)", angleCorrection.kD);
    }

    public void periodic() {

        // change PID values for angle correction
        if (angleCorrection.kP != SmartDashboard.getNumber("P value(angle)", angleCorrection.kP)) {
            angleCorrection.kP = SmartDashboard.getNumber("P value(angle)", angleCorrection.kP);
        }

        if (angleCorrection.kI != SmartDashboard.getNumber("I value(angle)", angleCorrection.kI)) {
            angleCorrection.kI = SmartDashboard.getNumber("I value(angle)", angleCorrection.kD);
        }
    }
}
