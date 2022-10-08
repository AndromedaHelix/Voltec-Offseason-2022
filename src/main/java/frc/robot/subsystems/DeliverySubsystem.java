// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DeliveryConstants;

public class DeliverySubsystem extends SubsystemBase {
  private CANSparkMax delivery = new CANSparkMax(DeliveryConstants.deliveryID, MotorType.kBrushless);
  private SparkMaxPIDController deliveryPID;
  private RelativeEncoder deliveryEncoder;
  private double deliverySetpoint;

  public DeliverySubsystem() {
    delivery.restoreFactoryDefaults();

    deliveryPID = delivery.getPIDController();
    deliveryEncoder = delivery.getEncoder();

    delivery.setIdleMode(IdleMode.kBrake);
    deliveryPID.setP(DeliveryConstants.deliverykP);
    deliveryPID.setI(DeliveryConstants.deliverykI);
    deliveryPID.setD(DeliveryConstants.deliverykD);
    deliveryPID.setFF(DeliveryConstants.deliverykFF);
    deliveryPID.setIZone(DeliveryConstants.deliverykIz);
  }

  @Override
  public void periodic() {
    publishData();
  }

  /* Sets delivery rotation */
  public void setDeliveryRotation(double rotSetpoint) {
    this.deliverySetpoint = rotSetpoint;
    deliveryPID.setReference(rotSetpoint, ControlType.kPosition);
  }

  public void stopRotation() {
    delivery.set(0);
    deliverySetpoint = 0;
    resetEncoder();
  }

  public double getSpeed() {
    return delivery.get();
  }

  public void resetEncoder() {
    deliveryEncoder.setPosition(0);
  }

  public double deliveryError() {
    return Math.abs(deliverySetpoint - deliveryEncoder.getPosition());
  }

  public void setSpeed(double speed) {
    delivery.set(speed);
  }

  public void publishData() {
    SmartDashboard.putNumber("Indexer position", deliveryEncoder.getPosition());
  }
}
