// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Logitech.Ports;
import frc.robot.Logitech.Ports.LeftStick;
import frc.robot.subsystems.DeliverySub;
import frc.robot.subsystems.FlywheelSub;
import frc.robot.subsystems.IntakeSub;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

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

  // Assuming that port is 0
  private static final Logitech controller = new Logitech(0);
  private final JoystickButton aButton = new JoystickButton(controller, Ports.A);
  private final JoystickButton bButton = new JoystickButton(controller, Ports.B);
  private final JoystickButton xButton = new JoystickButton(controller, Ports.X);
  private final JoystickButton yButton = new JoystickButton(controller, Ports.Y);


  Command intakeOut = new InstantCommand(
     
    () -> intakeSub.intakeOut(),
    intakeSub

  );

  Command intakeIn = new InstantCommand(
     
    () -> intakeSub.intakeIn(),
    intakeSub

  );

  Command intakeFwd = new RunCommand(

    () -> intakeSub.intakeRun(0.5),
    intakeSub

  );

  Command intakeRev = new RunCommand(

    () -> intakeSub.intakeRun(-0.5),
    intakeSub

  );

  // If the B button is held before letting the intake out it is reversed
  Command intakeRun = new ConditionalCommand(
    intakeRev.withInterrupt(() -> !bButton.get()), 
    intakeFwd.withInterrupt(() -> bButton.get()), 
    bButton::get
  );

  Command flywheelRun = new RunCommand(
    
    () -> flywheelSub.flywheelSpin(controller.getRawAxis(Ports.RIGHT_TRIGGER)), 
    flywheelSub
  );

  Command deliveryBothOn = new RunCommand( 
    
    () -> deliverySub.deliveryRun(controller.getRawAxis(Ports.LEFT_TRIGGER)), 
    deliverySub
    
  );

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    flywheelSub.setDefaultCommand(flywheelRun);
    deliverySub.setDefaultCommand(deliveryBothOn);

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // Disabled cause it broke
    // aButton.whenPressed(intakeOut);
    // aButton.whenHeld(intakeRun);
    // aButton.whenReleased(intakeIn);

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
