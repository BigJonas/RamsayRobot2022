// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class Intake {

        public static final Value IN = Value.kForward;
        public static final Value OUT = Value.kReverse;

    }

    public static final class Flywheel {

        public static final double FLYWHEEL_RAD = .25; // In feet
        public static final double FLYWHEEL_SPD = -1;
        public static final double GEAR_RATIO = 3.0; // 3 Flywheel rotations per motor rotation
        public static final double FLYWHEEL_MAX_SPD = 0.5;

        
    }

    public static final class Drivetrain {

        public static final Value HIGH = Value.kReverse;
        public static final Value LOW = Value.kForward;

    }

}
