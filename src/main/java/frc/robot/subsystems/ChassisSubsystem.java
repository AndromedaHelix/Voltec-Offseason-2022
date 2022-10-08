// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ChasisConstants;

public class ChassisSubsystem extends SubsystemBase {

  private WPI_TalonFX frontLeft = new WPI_TalonFX(ChasisConstants.frontLeftID);
  private WPI_TalonFX frontRight = new WPI_TalonFX(ChasisConstants.frontRightID);
  private WPI_TalonFX rearLeft = new WPI_TalonFX(ChasisConstants.rearLeftID);
  private WPI_TalonFX rearRight = new WPI_TalonFX(ChasisConstants.rearRightID);

  private DifferentialDrive chassis;
  private double leftSpeed, rightSpeed;

  private Solenoid forwardSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, ChasisConstants.highGearSolenoid);
  private Solenoid backwardSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, ChasisConstants.lowGearSolenoid);

  public ChassisSubsystem() {
    rearLeft.follow(frontLeft);
    rearRight.follow(frontRight);

    frontLeft.setInverted(true);
    frontRight.setInverted(false);

    // Sets rear robots to same inverted type as master
    rearLeft.setInverted(InvertType.FollowMaster);
    rearRight.setInverted(InvertType.FollowMaster);

    chassis = new DifferentialDrive(frontLeft, frontRight);

    frontLeft.setNeutralMode(NeutralMode.Coast);
    frontRight.setNeutralMode(NeutralMode.Coast);
    rearLeft.setNeutralMode(NeutralMode.Coast);
    rearRight.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void periodic() {
    publishData();
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;

    chassis.tankDrive(leftSpeed, rightSpeed);
  }

  public void arcadeDrive(double linearSpeed, double rotSpeed) {
    chassis.arcadeDrive(linearSpeed, rotSpeed);
  }

  public void toggleReduction() {
    var currentState = forwardSolenoid.get();

    forwardSolenoid.set(!currentState);
    backwardSolenoid.set(currentState);
  }

  public void toggleBrake(boolean state) {
    var brakeState = state;

    if (brakeState) {
      rearLeft.setNeutralMode(NeutralMode.Coast);
      rearRight.setNeutralMode(NeutralMode.Coast);
      frontLeft.setNeutralMode(NeutralMode.Coast);
      frontRight.setNeutralMode(NeutralMode.Coast);
    } else if (!brakeState) {
      rearLeft.setNeutralMode(NeutralMode.Brake);
      rearRight.setNeutralMode(NeutralMode.Brake);
      frontLeft.setNeutralMode(NeutralMode.Brake);
      frontRight.setNeutralMode(NeutralMode.Brake);
    }

  }

  public void publishData() {
    SmartDashboard.putNumber("Left Speed", frontLeft.get());
    SmartDashboard.putNumber("Right Speed", frontRight.get());
    SmartDashboard.putNumber("Left Joystick", leftSpeed);
    SmartDashboard.putNumber("Right Joystick", rightSpeed);

  }
}
