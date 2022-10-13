// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
    public static final class ChasisConstants {
        public static final int frontLeftID = 8;
        public static final int frontRightID = 1;
        public static final int rearLeftID = 6;
        public static final int rearRightID = 9;

        public static final int highGearSolenoid = 0;
        public static final int lowGearSolenoid = 1;
    }

    public static final class IntakeConstants {
        public static final int intakeMotorID = 1;
        public static final int intakeIn = 2;
        public static final int intakeOut = 3;

        public static final double multiplier = 1;
        public static final double intakeSpeed = 0.4;

    }

    public static final class ClimberConstants {
        public static final int climberMotorID = 4;

        public static final int forwardLimit = 155; // 165
        public static final int reverseLimit = 15; // 10

        public static final double speed = 0.8;
    }

    public static final class ShooterConstants {
        public static final int shooterID = 5;
        // Shooter PID
        public static final double shooterkP = 5e-5;
        public static final double shooterkI = 1e-7;
        public static final double shooterkD = 1e-3;
        public static final double shooterkIz = 0;
        public static final double shooterkFF = 1.5e-4;

        // Shooting velocities from diff positinos
        public static final int shooterFender = 3500;
        public static final int shooter1MeterFender = 4000;

        public static int velocityTolerance = 25;
    }

    public static final class HoodConstants {
        public static final int hoodID = 2;

        public static final float forwardLimit = (float) 0.38; //TODO: FIX
        public static final float reverseLimit = (float) 0.04;

        public static final double speed = 0.01;
    }

    public static final class DeliveryConstants {
        public static final int deliveryID = 3;

        // Delivery PID
        public static final double deliverykP = 0.7;
        public static final double deliverykI = 0;
        public static final double deliverykD = 0.5;
        public static final double deliverykIz = 0;
        public static final double deliverykFF = 0.0000;
    }

    public static final class OIConstants {
        public static final int driverControllerPort1 = 0;
        public static final int driverControllerPort2 = 1;
    }
    
    public static final class VisionConstants{
        public static final double kpAim = -0.01;
        public static final double kpDistance = -0.05; 
        public static final double min_aim_command = 0.0235;
        public static final double steeringAdjust = 0.0;
    }
}
