// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DeliverySub;
import frc.robot.subsystems.DrivetrainSub;
import frc.robot.subsystems.FlywheelSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.util.Keyboard;
import frc.robot.util.Logitech;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static frc.robot.util.Logitech.Ports.*;
import static frc.robot.util.Keyboard.Keys.*;

import static frc.robot.Constants.Flywheel.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private static final IntakeSub intakeSub = new IntakeSub();
  private static final FlywheelSub flywheelSub = new FlywheelSub();
  private static final DeliverySub deliverySub = new DeliverySub();
  private static final DrivetrainSub drivetrainSub = new DrivetrainSub();

  // Assuming that port is 0
  private final Logitech controller = new Logitech(0);
  private final JoystickButton aButton = new JoystickButton(controller, A);
  private final JoystickButton bButton = new JoystickButton(controller, B);
  private final JoystickButton xButton = new JoystickButton(controller, X);
  private final JoystickButton yButton = new JoystickButton(controller, Y);

  private final JoystickButton leftBumper = new JoystickButton(controller, LEFT_BUMPER);
  private final JoystickButton rightBumper = new JoystickButton(controller, RIGHT_BUMPER);

  private final Keyboard board = new Keyboard(); 

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureKeyboardBindings();

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureJoystickBindings() {
    // Default commands

    drivetrainSub.setDefaultCommand(new RunCommand(

      () -> drivetrainSub.drive(

        controller.getRawAxis(LeftStick.Y), 
        controller.getRawAxis(RightStick.X)

        ),

        drivetrainSub

    ));

    flywheelSub.setDefaultCommand(new RunCommand(
    
      () -> flywheelSub.flywheelSpin(controller.getRawAxis(RIGHT_TRIGGER)), 
      flywheelSub
      
    ));

    deliverySub.setDefaultCommand(
      new RunCommand(() -> {

        // Auto run delivery when flywheel is at shooting speed
        if (flywheelSub.flywheelGetSpeed() >= FLYWHEEL_SHOOT_RPM) {

          deliverySub.deliveryRun(0.5);

        } 

        deliverySub.deliveryRun(controller.getRawAxis(LEFT_TRIGGER));

      }, 
      deliverySub
      
    ));

    // Button bindings

    aButton.whenPressed(new InstantCommand(
     
      () -> {

        intakeSub.intakeOut();

      },
      
      intakeSub
  
    ));

    aButton.whileHeld(new RunCommand(

      () -> {
        
        if (bButton.get()) {

          intakeSub.intakeRun(-0.5);

        } else {

          intakeSub.intakeRun(0.5);

        }
      },

      intakeSub

    ));

    aButton.whenReleased(new InstantCommand(
     
      () -> {
        intakeSub.intakeIn();
        intakeSub.intakeRun(0);
      },
      intakeSub
  
    ));

    leftBumper.whenPressed(new InstantCommand(

      () -> drivetrainSub.shiftUp()

    ));

    rightBumper.whenPressed(new InstantCommand(

      () -> drivetrainSub.shiftDown()

    ));
  }
  
  private void configureKeyboardBindings() {
    
    // Default commands
    drivetrainSub.setDefaultCommand(
      new RunCommand(() -> {
        drivetrainSub.drive(
          board.keys[W_].get() ?  1.0 : 
          board.keys[S_].get() ? -1.0 : 0, 
          board.keys[A_].get() ? -1.0 :
          board.keys[D_].get() ?  1.0 : 0
        );
      },

      drivetrainSub

    ));

    flywheelSub.setDefaultCommand(
      new RunCommand(() -> {
        flywheelSub.flywheelSpin(
          board.keys[J_].get() ? FLYWHEEL_MAX_SPD : 0
        );
      }, 
      
      flywheelSub

    ));

    deliverySub.setDefaultCommand(
      new RunCommand(() -> {
        // Auto run delivery when flywheel is at shooting speed
        if (flywheelSub.flywheelGetSpeed() >= FLYWHEEL_SHOOT_RPM) {

          deliverySub.deliveryRun(0.5);

        } 

        deliverySub.deliveryRun(
          board.keys[K_].get() ? 0.5 : 0
        );
      }, 
      
      deliverySub

    ));

    // Key bindings

    board.keys[L_].whenPressed(
      new InstantCommand(() -> {
        intakeSub.intakeOut();

      },

      intakeSub

    ))
    .whenHeld(
      new RunCommand(() -> {
        if (board.keys[SC_].get()) {

          intakeSub.intakeRun(-0.5);

        } else {

          intakeSub.intakeRun(0.5);

        }

      },

      intakeSub

    ))
    .whenReleased(
      new InstantCommand(() -> {
        intakeSub.intakeIn();
        intakeSub.intakeRun(0.0);
      },
      
        intakeSub
      
    ));

    board.keys[Q_].whenPressed(
      new InstantCommand(() -> {
        drivetrainSub.shiftUp();

      }

    ));
    board.keys[E_].whenPressed(
      new InstantCommand(() -> {
        drivetrainSub.shiftDown();

      }

    ));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
