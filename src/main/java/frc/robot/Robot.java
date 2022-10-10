// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DeliverySubsystem;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private final DeliverySubsystem delivery = new DeliverySubsystem();
  private final ShooterSubsystem shooter = new ShooterSubsystem();
  private final IntakeSubsystem intake = new IntakeSubsystem();
  private final ClimberSubsystem climber = new ClimberSubsystem();
  private final ChassisSubsystem chassis = new ChassisSubsystem();
  private final LimelightSubsystem limelight = new LimelightSubsystem(chassis);
  private final HoodSubsystem hood = new HoodSubsystem();


  boolean intakeState = false;

  private double startTime;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer(delivery, shooter, intake, climber, chassis, limelight, hood);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();

    startTime = Timer.getFPGATimestamp();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    /* double time = Timer.getFPGATimestamp();
    double trueTime = time - startTime;

    if (time - startTime < 3) {
      chassis.tankDrive(0.5, 0.5);
    } else if (time - startTime >= 3 && time - startTime < 6) {
      chassis.tankDrive(-0.5, -0.5);
    } else if (time - startTime >= 6 && time - startTime < 9) {
      chassis.tankDrive(0.5, -0.5);
    } else if (time - startTime >= 9 && time - startTime < 12) {
      chassis.tankDrive(-0.5, 0.5);
    } else if (time - startTime >= 12 && time - startTime < 15) {
      chassis.tankDrive(0, 0);
    } else if (trueTime >= 15 && trueTime < 25) {
      shooter.setShooterVelocity(ShooterConstants.shooterFender);
      if (shooter.isInTolerance()) {
        delivery.setSpeed(0.7);
      }
    } else if (trueTime >= 25 && trueTime < 28) {
      shooter.stopMotor();
      delivery.stopRotation();
    } else if (trueTime >= 28 && trueTime < 31) {
      intake.setIntakeMotorSpeed(IntakeConstants.intakeSpeed);
    } else if (trueTime >= 31 && trueTime < 34) {
      intake.stopMotor();
    } else if (trueTime >= 34 & trueTime < 37) {
      intake.setIntakeMotorSpeed(-IntakeConstants.intakeSpeed);
    } else if (trueTime >= 37 && trueTime < 40) {
      intake.stopMotor();
      CommandScheduler.getInstance().disable();
    } else if(trueTime >= 40 && trueTime < 43){
      climber.setClimberSpeed(ClimberConstants.speed);
    } else if(trueTime >= 43 && trueTime < 36){
      climber.setClimberSpeed(-ClimberConstants.speed);
    } */
    chassis.prepareSong();
    chassis.toggleSong();
  }
}
