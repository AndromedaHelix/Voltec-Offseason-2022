package frc.robot.utils.vision;

import frc.robot.utils.vision.LimelightControlModes.CamMode;
import frc.robot.utils.vision.LimelightControlModes.LEDMode;
import frc.robot.utils.vision.LimelightControlModes.SnapshotMode;
import frc.robot.utils.vision.LimelightControlModes.StreamMode;
import frc.robot.utils.vision.LimelightData.Data;
import frc.robot.utils.vision.LimelightData.RawCrosshair;
import frc.robot.utils.vision.LimelightData.RawData;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Notifier;

/**
 * Wrapper for simple usage of a Limelight camera.
 * <p>
 * Originally copied over from:
 * https://github.com/Spectrum3847/Ultraviolet-2020/blob/master/src/main/java/frc/lib/drivers/LimeLight.java
 */
public class LimelightCamera {
	/** Name of {@link LimelightCamera} instance. */
	private final String name;
	/** {@link NetworkTable} used by the {@link LimelightCamera}. */
	private final NetworkTable table;
	/** Whether or not the {@link LimelightCamera} is connected. */
	private boolean isConnected = false;

	/** Handles running the {@link LimelightCamera} in a separate {@link Thread}. */
	Notifier heartbeat;

	/**
	 * Constructor for a {@link LimelightCamera}. Initializes it with the given
	 * {@link #name}, and a default period of 0.01s.
	 * 
	 * @param name The name to assign the {@link LimelightCamera}
	 */
	public LimelightCamera(String name) {
		this(name, 0.1);
	}

	/**
	 * Constructor for a {@link LimelightCamera}. Initializes it with the given
	 * {@link #name} and specified period time.
	 * 
	 * @param name   The name to assign the {@link LimelightCamera}
	 * @param period The period time of this {@link LimelightCamera}'s
	 *               {@link heartbeat} {@link Notifier}.
	 */
	public LimelightCamera(String name, double period) {
		this.name = name;
		table = NetworkTableInstance.getDefault().getTable(name);

		heartbeat = new Notifier(() -> {
			resetPipelineLatency();

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}

			isConnected = getData(Data.PIPELINE_LATENCY) != 0.0;
		});

		heartbeat.setName(name + "Heartbeat");
		heartbeat.startPeriodic(period);
	}

	/**
	 * Gets the {@link #name} of this {@link LimelightCamera} instance.
	 * 
	 * @return The {@link #name} of this {@link LimelightCamera} instance
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to check if the {@link LimelightCamera} is running and operational.
	 * 
	 * @return Whether the {@link LimelightCamera} is running and operational or
	 *         not.
	 */
	public boolean isConnected() {
		return isConnected;
	}

	/**
	 * Gets the {@link NetworkTableEntry} of a given {@link Data data type}.
	 * 
	 * @param type The {@link Data data type} to get
	 * @return The {@link NetworkTableEntry} of the specified {@link Data data type}
	 */
	public NetworkTableEntry getDataEntry(Data type) {
		return table.getEntry(type.getName());
	}

	/**
	 * Gets the {@link NetworkTableEntry} of a given {@link RawData raw data type}.
	 * 
	 * @param type    The {@link RawData raw data type} to get
	 * @param contour Which of the {@link LimelightCamera}'s contours to use
	 * @return The {@link NetworkTableEntry} of the specified {@link RawData raw
	 *         data type}, for the specified contour
	 */
	public NetworkTableEntry getRawDataEntry(RawData type, int contour) {
		return table.getEntry(type.getName() + MathUtil.clamp(contour, 0, 2));
	}

	/**
	 * Gets the {@link NetworkTableEntry} of a given {@link RawCrosshair raw
	 * crosshair type}.
	 * 
	 * @param type                The {@link RawCrosshair raw crosshair type} to get
	 * @param calibratedCrosshair Which of the {@link LimelightCamera}'s crosshairs
	 *                            to use
	 * @return The {@link NetworkTableEntry} of the specified {@link RawCrosshair
	 *         raw crosshair type}, for the specified crosshair
	 */
	public NetworkTableEntry getRawCrosshairEntry(RawCrosshair type, int calibratedCrosshair) {
		return table.getEntry(type.getName() + MathUtil.clamp(calibratedCrosshair, 0, 1));
	}

	/**
	 * Gets the value of a given {@link Data data type}'s {@link NetworkTableEntry}.
	 * 
	 * @param type The {@link Data data type} to get
	 * @return The value of the specified {@link Data data type}'s
	 *         {@link NetworkTableEntry}
	 */
	public double getData(Data type) {
		return getDataEntry(type).getDouble(0.0);
	}

	/**
	 * Gets the value of a given {@link RawData raw data type}'s
	 * {@link NetworkTableEntry}.
	 * 
	 * @param type    The {@link RawData raw data type} to get
	 * @param contour Which of the {@link LimelightCamera}'s contours to use
	 * @return The value of the specified {@link RawData raw data type}'s
	 *         {@link NetworkTableEntry}, for the specified contour
	 */
	public double getRawData(RawData type, int contour) {
		return table.getEntry(type.getName() + MathUtil.clamp(contour, 0, 2)).getDouble(0.0);
	}

	/**
	 * Gets the value of a given {@link RawCrosshair raw crosshair type}'s
	 * {@link NetworkTableEntry}.
	 * 
	 * @param type                The {@link RawCrosshair raw crosshair type} to get
	 * @param calibratedCrosshair Which of the {@link LimelightCamera}'s crosshairs
	 *                            to use
	 * @return The value of the specified {@link RawCrosshair raw crosshair type}'s
	 *         {@link NetworkTableEntry}, for the specified crosshair
	 */
	public double getRawCrosshair(RawCrosshair type, int calibratedCrosshair) {
		return table.getEntry(type.getName() + MathUtil.clamp(calibratedCrosshair, 0, 1)).getDouble(0.0);
	}

	/**
	 * Method to check if a target is found, only if the {@link LimelightCamera} is
	 * connected.
	 * 
	 * @return Whether a target is found (and the {@link LimelightCamera} is
	 *         connected) or not
	 */
	public boolean isTargetFound() {
		return isConnected() && getData(Data.TARGET_FOUND) != 0.0f;
	}

	/**
	 * Method to reset the {@link LimelightCamera}'s pipeline latency, should only
	 * be called by the {@link LimelightCamera}'s {@link #heartbeat Notifier
	 * instance}.
	 */
	private void resetPipelineLatency() {
		getDataEntry(Data.PIPELINE_LATENCY).setValue(0.0);
	}

	/**
	 * Sets the {@link LimelightCamera}'s {@link LEDMode LED state}.
	 * 
	 * @param mode The {@link LEDMode mode} at which to set the
	 *             {@link LimelightCamera}
	 */
	public void setLEDMode(LEDMode mode) {
		table.getEntry("ledMode").setValue(mode.getValue());
	}

	/**
	 * Gets the current {@link LEDMode LED state} of the {@link LimelightCamera}, as
	 * a number value.
	 * <p>
	 * <b>See:</b> {@link LEDMode}, for what each number value represents.
	 * 
	 * @return The current {@link LEDMode}, as a number
	 */
	public double getLEDMode() {
		return table.getEntry("ledMode").getDouble(0.0);
	}

	/**
	 * Sets the {@link LimelightCamera}'s {@link CamMode operational mode}.
	 * 
	 * @param mode The {@link CamMode mode} at which to set the
	 *             {@link LimelightCamera}
	 */
	public void setCamMode(CamMode mode) {
		table.getEntry("camMode").setValue(mode.getValue());
	}

	/**
	 * Gets the current {@link CamMode operational mode} of the
	 * {@link LimelightCamera}, as a number value.
	 * <p>
	 * <b>See:</b> {@link CamMode}, for what each number value represents.
	 * 
	 * @return The current {@link CamMode}, as a number
	 */
	public double getCamMode() {
		return table.getEntry("camMode").getDouble(0.0);
	}

	/**
	 * Sets the {@link LimelightCamera}'s {@link StreamMode streaming mode}.
	 * 
	 * @param mode The {@link StreamMode mode} at which to set the
	 *             {@link LimelightCamera}
	 */
	public void setStreamMode(StreamMode mode) {
		table.getEntry("stream").setValue(mode.getValue());
	}

	/**
	 * Gets the current {@link StreamMode streaming mode} of the
	 * {@link LimelightCamera}, as a number value.
	 * <p>
	 * <b>See:</b> {@link StreamMode}, for what each number value represents.
	 * 
	 * @return The current {@link StreamMode}, as a number
	 */
	public double getStreamMode() {
		return table.getEntry("stream").getDouble(0.0);
	}

	/**
	 * Sets the {@link LimelightCamera}'s {@link SnapshotMode snapshot mode}.
	 * 
	 * @param mode The {@link SnapshotMode mode} at which to set the
	 *             {@link LimelightCamera}
	 */
	public void setSnapshotMode(SnapshotMode mode) {
		table.getEntry("snapshot").setValue(mode.getValue());
	}

	/**
	 * Gets the current {@link SnapshotMode snapshot mode} of the
	 * {@link LimelightCamera}, as a number value.
	 * <p>
	 * <b>See:</b> {@link SnapshotMode}, for what each number value represents.
	 * 
	 * @return The current {@link SnapshotMode}, as a number
	 */
	public double getSnapshotMode() {
		return table.getEntry("snapshot").getDouble(0.0);
	}

	/**
	 * Sets the {@link LimelightCamera}'s pipeline to the specified id.
	 * 
	 * @param pipeline The pipeline to switch to
	 */
	public void setPipeline(int pipeline) {
		table.getEntry("pipeline").setValue(MathUtil.clamp(pipeline, 0, 9));
	}

	/**
	 * Gets the {@link LimelightCamera}'s currently running pipeline id.
	 * 
	 * @return The currently running pipeline's id
	 */
	public double getPipeline() {
		return table.getEntry("pipeline").getDouble(0.0);
	}
}