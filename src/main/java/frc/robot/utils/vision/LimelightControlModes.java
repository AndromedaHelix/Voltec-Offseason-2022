package frc.robot.utils.vision;

import edu.wpi.first.networktables.NetworkTableEntry;

/**
 * Class containing several enums, meant to be used for adjusting/changing
 * several settings of a {@link LimelightCamera}.
 * <p>
 * Originally copied over from:
 * https://github.com/Spectrum3847/Ultraviolet-2020/blob/master/src/main/java/frc/lib/drivers/LimeLightControlModes.java
 */
public class LimelightControlModes {
	/**
	 * Possible LED states a {@link LimelightCamera} can be set to.
	 * <p>
	 * <b>CURRENT_PIPELINE - 0</b> - Use the {@link LEDMode} set in the current
	 * pipeline.
	 * <p>
	 * <b>FORCE_OFF - 1</b> - Force off.
	 * <p>
	 * <b>FORCE_BLINK - 2</b> - Force blink.
	 * <p>
	 * <b>FORCE_ON - 3</b> - Force on.
	 */
	public enum LEDMode {
		CURRENT_PIPELINE(0), FORCE_OFF(1), FORCE_BLINK(2), FORCE_ON(3);

		/** Possible {@link NetworkTableEntry} value for this {@link LEDMode}. */
		private final int value;

		/**
		 * Constructor for {@link LEDMode}, which sets a {@link LimelightCamera}'s LED
		 * state.
		 * 
		 * @param value The value at which to set a {@link LimelightCamera}'s LED state
		 */
		private LEDMode(int value) {
			this.value = value;
		}

		/**
		 * Get the specified {@link LEDMode}'s value.
		 * 
		 * @return The specified {@link LEDMode}'s value
		 */
		public int getValue() {
			return value;
		}
	}

	/**
	 * Possible operation modes a {@link LimelightCamera} can be set to.
	 * <p>
	 * <b>VISION - 0</b> - Use the vision processor.
	 * <p>
	 * <b>DRIVER - 1</b> - Driver camera (increases exposure, disables vision
	 * processing).
	 */
	public enum CamMode {
		VISION(0), DRIVER(1);

		/** Possible {@link NetworkTableEntry} value for this {@link CamMode}. */
		private final int value;

		/**
		 * Constructor for {@link CamMode}, which sets a {@link LimelightCamera}'s
		 * operation mode.
		 * 
		 * @param value The value at which to set a {@link LimelightCamera}'s operation
		 *              mode
		 */
		private CamMode(int value) {
			this.value = value;
		}

		/**
		 * Get the specified {@link CamMode}'s value.
		 * 
		 * @return The specified {@link CamMode}'s value
		 */
		public int getValue() {
			return value;
		}
	}

	/**
	 * Possible streaming modes a {@link LimelightCamera} can be set to.
	 * <p>
	 * <b>STANDARD - 0</b> - Side-by-side streams if a webcam is attached to a
	 * {@link LimelightCamera}.
	 * <p>
	 * <b>PIP_PRIMARY - 1</b> - The secondary camera stream is placed in the
	 * lower-right corner of the primary camera stream.
	 * <p>
	 * <b>PIP_SECONDARY - 2</b> - The primary camera stream is placed in the
	 * lower-right corner of the secondary camera stream.
	 */
	public enum StreamMode {
		STANDARD(0), PIP_PRIMARY(1), PIP_SECONDARY(2);

		/** Possible {@link NetworkTableEntry} value for this {@link StreamMode}. */
		private final int value;

		/**
		 * Constructor for {@link StreamMode}, which sets a {@link LimelightCamera}'s
		 * streaming mode.
		 * 
		 * @param value The value at which to set a {@link LimelightCamera}'s streaming
		 *              mode
		 */
		private StreamMode(int value) {
			this.value = value;
		}

		/**
		 * Get the specified {@link StreamMode}'s value.
		 * 
		 * @return The specified {@link StreamMode}'s value
		 */
		public int getValue() {
			return value;
		}
	}

	/**
	 * Possible snapshot modes a {@link LimelightCamera} can be set to. Allows users
	 * to take snapshots during a match.
	 * <p>
	 * <b>STOP - 0</b> - Stop taking snapshots.
	 * <p>
	 * <b>TWO_PER_SECOND - 1</b> - Take two snapshots per second.
	 */
	public enum SnapshotMode {
		STOP(0), TWO_PER_SECOND(1);

		/** Possible {@link NetworkTableEntry} value for this {@link SnapshotMode}. */
		private final int value;

		/**
		 * Constructor for {@link SnapshotMode}, which sets a {@link LimelightCamera}'s
		 * snapshot mode.
		 * 
		 * @param value The value at which to set a {@link LimelightCamera}'s snapshot
		 *              mode
		 */
		private SnapshotMode(int value) {
			this.value = value;
		}

		/**
		 * Get the specified {@link SnapshotMode}'s value.
		 * 
		 * @return The specified {@link SnapshotMode}'s value
		 */
		public int getValue() {
			return value;
		}
	}
}