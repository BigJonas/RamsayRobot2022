// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.Flywheel.*;

public class FlywheelSub extends SubsystemBase {

  private CANSparkMax flyWheelMotor1 = new CANSparkMax(13, MotorType.kBrushless);
  private CANSparkMax flyWheelMotor2 = new CANSparkMax(12, MotorType.kBrushless);

  /** Creates a new Flywheel. */
  public FlywheelSub() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void flywheelSpin(double spd) {

    spd = Math.max(-FLYWHEEL_MAX_SPD, Math.min(FLYWHEEL_MAX_SPD, spd));
    flyWheelMotor1.set(spd);
    flyWheelMotor2.set(spd);

  }

  public double flywheelGetSpeed() {

    return flyWheelMotor1.getEncoder().getVelocity() * GEAR_RATIO;

  }

}
