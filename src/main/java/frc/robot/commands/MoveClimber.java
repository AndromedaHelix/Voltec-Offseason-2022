// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class MoveClimber extends CommandBase {
  private final ClimberSubsystem climber;
  private final double climberSpeed;

  public MoveClimber(double speed, ClimberSubsystem climber) {
    this.climber = climber;
    this.climberSpeed = speed;

    addRequirements(climber);
  }

  @Override
  public void initialize() {
    climber.setClimberSpeed(climberSpeed);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    climber.setClimberSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
