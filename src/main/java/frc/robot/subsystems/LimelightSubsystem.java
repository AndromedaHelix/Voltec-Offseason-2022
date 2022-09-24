// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.stuypulse.stuylib.network.limelight.Limelight;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.vision.LimelightCamera;
//TODO FINISH
/*
 * Ajustar angulo chassis
 * Ajustar angulo hood
 * Ajustar velocidad motor hood
 * Igual y se puede ajustar el ángulo y velocidad,
 * así como la posición del chassis a un punto definido en la cancha. 
 * (USO DE VARIAS PIPELINES)
 */

public class LimelightSubsystem extends SubsystemBase {
  public static Limelight limelight;

  //private final Object lock = new Object();
  //private final Notifier notifier;
  private boolean aiming = false; //firstRun = true;

  /** Creates a new LimelightSubsystem. */
  public LimelightSubsystem() {
    limelight = Limelight.getInstance();
    /* notifier = new Notifier(() -> {
      synchronized (lock){
        if(firstRun) {
          Thread.currentThread().setName("limelight");
          Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
          firstRun = false;
        }

        if(!aiming)
          return;
        
          synchronized (LimelightSubsystem.this){
            if(!limelight.getValidTarget())
              return;
          }
      }
    }); */
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void angleAdjustment(){
    
  }

  public void toolgeAim(){
    aiming = !aiming;
  }

  public boolean isAiming(){
    return aiming;
  }
}
