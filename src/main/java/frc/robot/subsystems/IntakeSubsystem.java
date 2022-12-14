// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
  private static CANSparkMax intakeMotor = new CANSparkMax(IntakeConstants.intakeMotorID, MotorType.kBrushless);
  private Solenoid intakeIn = new Solenoid(PneumaticsModuleType.CTREPCM, IntakeConstants.intakeIn);
  private Solenoid intakeOut = new Solenoid(PneumaticsModuleType.CTREPCM, IntakeConstants.intakeOut);

  public IntakeSubsystem() {
    intakeMotor.restoreFactoryDefaults();
  }

  @Override
  public void periodic() {
    publishData();
  }

  /* Toggle intake solenoids */
  public void toggleIntake() {
    boolean intakeState = intakeIn.get();

    intakeIn.set((intakeState) ? !intakeState : !intakeState);
    intakeOut.set((intakeState) ? intakeState : intakeState);
  }

  /* Sets intake motor speed */
  public void setIntakeMotorSpeed(double speed) {
    intakeMotor.set(speed * IntakeConstants.multiplier);
  }

  /* Stops motor */
  public void stopMotor() {
    intakeMotor.set(0);
  }

  /* These two functions are only for the autonomous */
  public void intakeIn() {
    intakeIn.set(true);
    intakeOut.set(false);
  }

  public void intakeOut() {
    intakeOut.set(true);
    intakeIn.set(false);
  }

  public void publishData() {
    SmartDashboard.putBoolean("Intake In State", intakeIn.get());
    SmartDashboard.putBoolean("Intake Out State", intakeOut.get());
  }
}