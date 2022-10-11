// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.HoodConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.DeliveryEnable;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.commands.MoveClimber;
import frc.robot.commands.ShooterSpeed;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DeliverySubsystem;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.utils.XboxControllerUpgrade;

public class RobotContainer {
        private final DeliverySubsystem delivery;
        private final ShooterSubsystem shooter;
        private final IntakeSubsystem intake;
        private final ClimberSubsystem climber;
        private final ChassisSubsystem chassis;
        private final LimelightSubsystem limelight;
        private final HoodSubsystem hood;

        private final XboxControllerUpgrade joystick1 = new XboxControllerUpgrade(OIConstants.driverControllerPort1,
                        0.2);
        private final XboxControllerUpgrade joystick2 = new XboxControllerUpgrade(OIConstants.driverControllerPort2,
                        0.2);

        public RobotContainer(DeliverySubsystem delivery, ShooterSubsystem shooter, IntakeSubsystem intake,
                        ClimberSubsystem climber, ChassisSubsystem chassis, LimelightSubsystem limelight,
                        HoodSubsystem hood) {
                this.delivery = delivery;
                this.shooter = shooter;
                this.intake = intake;
                this.climber = climber;
                this.chassis = chassis;
                this.limelight = limelight;
                this.hood = hood;

                /* Tan Drive */
                chassis.setDefaultCommand(
                                new RunCommand(() -> chassis.tankDrive(joystick1.getLeftY(), joystick1.getRightY()),
                                                chassis));

                /* Arcade Drive */
                /*
                 * chassis.setDefaultCommand(
                 * new RunCommand(() -> chassis.arcadeDrive(joystick1.getLeftY(),
                 * -joystick1.getRightX()), chassis));
                 */

                configureButtonBindings();
        }

        // BUTTON MAP FROM SMARTDASHBOARD

        private void configureButtonBindings() {

                /* Joystick 1 */
                /* Toggle reduction */
                new JoystickButton(joystick1, Button.kY.value)
                                .whenPressed(
                                                new InstantCommand(() -> chassis.toggleReduction()));

                /* Toggle Brake */
                new JoystickButton(joystick1, Button.kRightBumper.value)
                                .whileHeld(new InstantCommand(() -> chassis.toggleBrake(false)))
                                .whenReleased(new InstantCommand(() -> chassis.toggleBrake(true)));

                /* Toggle Aim */
                new JoystickButton(joystick1, Button.kLeftBumper.value)
                                .whileHeld(new StartEndCommand(() -> limelight.toggleAim(), () -> limelight.toggleAim(),
                                                limelight));

                /* Move hood */
                /* joystick1.rightTriggerButton.whileHeld(
                                new StartEndCommand(
                                                () -> hood.setHoodSpeed(HoodConstants.speed),
                                                () -> hood.setHoodSpeed(0),
                                                hood));
                joystick1.leftTriggerButton.whileHeld(
                                new StartEndCommand(
                                                () -> hood.setHoodSpeed(-HoodConstants.speed),
                                                () -> hood.setHoodSpeed(0),
                                                hood)); */

                /* Joystick 2 */
                /* Shooting next to fender */
                new JoystickButton(joystick2, Button.kA.value)
                                .whileHeld(new SequentialCommandGroup(
                                                new ShooterSpeed(ShooterConstants.shooterFender, shooter),
                                                new DeliveryEnable(0.7, delivery)))
                                .whenReleased(new ShooterSpeed(0, shooter));

                /* Shooting a meter from fender */
                new JoystickButton(joystick2, Button.kB.value)
                                .whileHeld(new SequentialCommandGroup(
                                                new ShooterSpeed(ShooterConstants.shooter1MeterFender, shooter),
                                                new DeliveryEnable(0.7, delivery)))
                                .whenReleased(new ShooterSpeed(0, shooter));

                /* Dropping ball */
                new JoystickButton(joystick2, Button.kY.value)
                                .whileHeld(new SequentialCommandGroup(
                                                new ShooterSpeed(1800, shooter),
                                                new DeliveryEnable(0.3, delivery)))
                                .whenReleased(new ShooterSpeed(0, shooter));

                /* Move climber */
                joystick2.Dpad.Down.whileHeld(
                                new MoveClimber(-ClimberConstants.speed, climber));

                joystick2.Dpad.Up.whileHeld(
                                new MoveClimber(ClimberConstants.speed, climber));

                /* Toggle Intake */
                new JoystickButton(joystick2, Button.kX.value)
                                .whenPressed(
                                                new InstantCommand(() -> intake.toggleIntake()));

                /* Move intake */
                joystick2.rightTriggerButton.whileHeld(
                                new StartEndCommand(
                                                () -> intake.setIntakeMotorSpeed(IntakeConstants.intakeSpeed),
                                                () -> intake.setIntakeMotorSpeed(0),
                                                intake));

                joystick2.leftTriggerButton.whileHeld(
                                new StartEndCommand(
                                                () -> intake.setIntakeMotorSpeed(-IntakeConstants.intakeSpeed),
                                                () -> intake.setIntakeMotorSpeed(0),
                                                intake));

                /* Inverse rotate delivery */
                new JoystickButton(joystick2, Button.kRightBumper.value)
                                .whileHeld(new DeliveryEnable(-0.3, delivery));
        }

        public Command getAutonomousCommand() {
                // An ExampleCommand will run in autonomous
                return new SequentialCommandGroup(
                                // Disparar primera pelota
                                new SequentialCommandGroup(
                                                new ShooterSpeed(3580, shooter),
                                                new DeliveryEnable(0.7, delivery)).withTimeout(2),
                                new ShooterSpeed(0, shooter),

                                // Ir por segunda pelota
                                // new
                                // RunCommand(()->intake.toggleIntake()).withInterrupt(intake.intakeOut::get),

                                new IntakeOut(intake).withTimeout(0.3),
                                new ParallelCommandGroup(
                                                new RunCommand(() -> chassis.tankDrive(-.45, -.45), chassis),
                                                new StartEndCommand(
                                                                () -> intake.setIntakeMotorSpeed(.7),
                                                                () -> intake.setIntakeMotorSpeed(0)))
                                                .withTimeout(2),
                                new RunCommand(() -> chassis.tankDrive(0, 0), chassis).withTimeout(.3),
                                new IntakeIn(intake).withTimeout(0.5),
                                // new RunCommand(()->intake.toggleIntake(), intake).withTimeout(0.11),
                                // new
                                // RunCommand(()->intake.toggleIntake()).withInterrupt(intake.intakeOut::get),
                                new RunCommand(() -> chassis.tankDrive(.45, .45), chassis).withTimeout(2),
                                new RunCommand(() -> chassis.tankDrive(0, 0), chassis).withTimeout(.2),
                                new SequentialCommandGroup(
                                                new ShooterSpeed(3550, shooter),
                                                new DeliveryEnable(0.7, delivery)).withTimeout(4),
                                new ShooterSpeed(0, shooter));
        }
}
