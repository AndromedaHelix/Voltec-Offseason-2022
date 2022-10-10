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
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  private CANSparkMax shooter = new CANSparkMax(ShooterConstants.shooterID, MotorType.kBrushless);
  private SparkMaxPIDController shooterPID;
  private RelativeEncoder shooterEncoder;
  private int shooterSetpoint;

  public ShooterSubsystem() {
    shooter.restoreFactoryDefaults();

    shooterPID = shooter.getPIDController();

    shooterEncoder = shooter.getEncoder();

    shooter.setIdleMode(IdleMode.kCoast);
    shooterPID.setP(ShooterConstants.shooterkP);
    shooterPID.setI(ShooterConstants.shooterkI);
    shooterPID.setD(ShooterConstants.shooterkD);
    shooterPID.setFF(ShooterConstants.shooterkFF);
    shooterPID.setIZone(ShooterConstants.shooterkIz);
  }

  @Override
  public void periodic() {
    publishData();
  }

  /* Sets the velocity for the shooter */
  public void setShooterVelocity(int velSetpoint) {
    this.shooterSetpoint = velSetpoint;
    shooterPID.setReference(velSetpoint, ControlType.kVelocity);
  }

  /* Checs if the shooter is near the setpoint */
  public boolean isInTolerance() {
    return Math.abs(shooterEncoder.getVelocity()-shooterSetpoint)<ShooterConstants.velocityTolerance;
  }

  /* Stops motor */
  public void stopMotor() {
    shooter.set(0);
  }

  public void publishData() {
    SmartDashboard.putNumber("ShooterVelocitu", shooterEncoder.getVelocity());
  }
}
