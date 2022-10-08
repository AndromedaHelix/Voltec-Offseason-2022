// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.stuypulse.stuylib.network.limelight.Limelight;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//TODO FINISH
/*
 * Ajustar angulo chassis
 * Ajustar angulo hood
 * Ajustar velocidad motor hood
 * Igual y se puede ajustar el ángulo y velocidad,
 * así como la posición del chassis a un punto definido en la cancha. 
 * (USO DE VARIAS PIPELINES)
 */
import frc.robot.Constants.VisionConstants;

public class LimelightSubsystem extends SubsystemBase {
  public static Limelight limelight;
  private final ChassisSubsystem chassis;

  private final Object lock = new Object();
  private final Notifier notifier;
  private boolean aiming = false, firstRun = true;

  /** Creates a new LimelightSubsystem. */
  public LimelightSubsystem(ChassisSubsystem chassis) {
    limelight = Limelight.getInstance();
    this.chassis = chassis;
    notifier = new Notifier(() -> {
      synchronized (lock) {
        if (firstRun) {
          Thread.currentThread().setName("limelight");
          Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
          firstRun = false;
        }

        if (!aiming)
          return;

        synchronized (LimelightSubsystem.this) {
          if (!limelight.getValidTarget())
            return;

          // Saca los datos de la visión en X y Y
          double tx = limelight.getTargetXAngle(),
              ty = limelight.getTargetYAngle();

          // Inicializa los valores de vision
          double kpAim = VisionConstants.kpAim, kpDistance = VisionConstants.kpDistance,
              min_aim_command = VisionConstants.min_aim_command;

          // Calcula el ajuste de la distancia y angulo
          var steeringAdjust = tx > 1 ? (kpAim * -tx - min_aim_command)
              : (tx < -1 ? (kpAim * -tx + min_aim_command) : 0.0);
          var distanceAdjust = kpDistance * ty;

          System.out.println("Left: " + " " + -steeringAdjust + distanceAdjust);
          System.out.println("Right: " + " " + steeringAdjust + distanceAdjust);

          // Aplica los ajustes
          chassis.tankDrive((-steeringAdjust + distanceAdjust) * 0.5, (steeringAdjust + distanceAdjust) * 0.5);
        }
      }
    });

    notifier.startPeriodic(0.01);
  }

  @Override
  public void periodic() {
  }

  public void angleAdjustment() {

  }

  public void toggleAim() {
    aiming = !aiming;
  }

  public boolean isAiming() {
    return aiming;
  }
}
