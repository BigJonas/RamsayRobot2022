// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSub extends SubsystemBase {

  private WPI_TalonSRX intakeMotor = new WPI_TalonSRX(3);
  private DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);

  /** Creates a new Intake. */
  public IntakeSub() {

    intakeIn();

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void intakeIn() {

    intakeSolenoid.set(Constants.Intake.IN);

  }

  public void intakeOut() {

    intakeSolenoid.set(Constants.Intake.OUT);

  }
  
  public void intakeRun(double spd) {

    intakeMotor.set(spd);

  }

}
