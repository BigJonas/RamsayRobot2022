// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DeliverySub extends SubsystemBase {

  private WPI_TalonSRX deliveryWheel = new WPI_TalonSRX(2);
  private WPI_TalonSRX deliveryStar = new WPI_TalonSRX(4);

  /** Creates a new DeliverySub. */
  public DeliverySub() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void deliveryRun(double spd) {

    deliveryWheel.set(-spd);
    deliveryStar.set(spd);

  }

  public void deliveryStarRun(double spd) {

    deliveryStar.set(spd);

  }
}
