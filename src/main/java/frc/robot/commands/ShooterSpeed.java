// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

/*
 * TODO: Consider using command inline rather than writing a subclass
 * https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
 */
public class ShooterSpeed extends CommandBase {
  private final ShooterSubsystem shooter;
  private int shooterSpeed;

  public ShooterSpeed(int shooterSpeed, ShooterSubsystem shooter) {
    this.shooter = shooter;
    this.shooterSpeed = shooterSpeed;

    addRequirements(shooter);
  }

  @Override
  public void initialize() {
    shooter.setShooterVelocity(shooterSpeed);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return shooter.isInTolerance();
  }
}
