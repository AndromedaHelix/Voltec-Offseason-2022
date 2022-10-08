// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DeliverySubsystem;

public class DeliveryEnable extends CommandBase {
  private final DeliverySubsystem delivery;
  private double deliverySpeed;

  public DeliveryEnable(double deliverySpeed, DeliverySubsystem delivery) {
    this.delivery = delivery;
    this.deliverySpeed = deliverySpeed;

    addRequirements(delivery);
  }

  @Override
  public void initialize() {
    delivery.setSpeed(deliverySpeed);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    delivery.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
