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

  /* Stops delivery rotation */
  public void stopRotation() {
    delivery.set(0);
    resetEncoder();
  }

  /* Getter function for delivery speed */
  public double getSpeed() {
    return delivery.get();
  }

  /* Setter function for delivery speed */
  public void setSpeed(double speed) {
    delivery.set(speed);
  }

  /* Resets delivery encoder's position */
  public void resetEncoder() {
    deliveryEncoder.setPosition(0);
  }

  public void publishData() {
    SmartDashboard.putNumber("Indexer position", deliveryEncoder.getPosition());
  }
}
