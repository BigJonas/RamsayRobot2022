// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.Drivetrain.*;

public class DrivetrainSub extends SubsystemBase {

  private CANSparkMax l1 = new CANSparkMax(16, MotorType.kBrushless);
  private CANSparkMax l2 = new CANSparkMax(17, MotorType.kBrushless);
  private CANSparkMax r1 = new CANSparkMax(14, MotorType.kBrushless);
  private CANSparkMax r2 = new CANSparkMax(10, MotorType.kBrushless);
  private MotorControllerGroup left = new MotorControllerGroup(l1, l2);
  private MotorControllerGroup right = new MotorControllerGroup(r1, r2);
  private DifferentialDrive diffDrive = new DifferentialDrive(left, right);

  private DoubleSolenoid shifter = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 4, 5); 

  /** Creates a new DrivetrainSub. */
  public DrivetrainSub() {

    right.setInverted(true);
    shiftDown();//yay programing!! I love code! 

  }

  public void drive(double spd, double rot) {

    diffDrive.arcadeDrive(spd, rot, true);

  }

  public void shiftUp() {

    shifter.set(HIGH);

  }

  public void shiftDown() {

    shifter.set(LOW);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
