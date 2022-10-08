// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {
  private CANSparkMax climberMotor = new CANSparkMax(ClimberConstants.climberMotorID, MotorType.kBrushless);
  private RelativeEncoder climberEncoder;

  public ClimberSubsystem() {
    climberMotor.restoreFactoryDefaults();

    climberMotor.setSoftLimit(SoftLimitDirection.kForward, ClimberConstants.forwardLimit);
    climberMotor.setSoftLimit(SoftLimitDirection.kReverse, ClimberConstants.reverseLimit);

    climberMotor.enableSoftLimit(SoftLimitDirection.kForward, true);
    climberMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);

    climberEncoder = climberMotor.getEncoder();
  }

  @Override
  public void periodic() {
    publishData();
  }

  public void setClimberSpeed (double speed){
    climberMotor.set(speed);
  }

  public void publishData() {
    SmartDashboard.putNumber("ClimberPosition", climberEncoder.getPosition());
  }
}