package frc.robot.utils.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

/**
 * Class containing several enums useful for obtaining data from a
 * {@link LimelightCamera}.
 */
public class LimelightData {
	/**
	 * Data types a {@link LimelightCamera Limelight camera} sends over a
	 * {@link NetworkTable}.
	 * <p>
	 * <b>TARGET_FOUND - tv</b> - Whether the limelight has any valid targets (0 or
	 * 1).
	 * <p>
	 * <b>HORIZONTAL_OFFSET - tx</b> - Horizontal Offset From Crosshair To Target
	 * (LL1: -27 degrees to 27 degrees | LL2: -29.8 to 29.8 degrees).
	 * <p>
	 * <b>VERTICAL_OFFSET - ty</b> - Vertical Offset From Crosshair To Target (LL1:
	 * -20.5 degrees to 20.5 degrees | LL2: -24.85 to 24.85 degrees).
	 * <p>
	 * <b>TARGET_AREA - ta</b> - Target Area (0% of image to 100% of image).
	 * <p>
	 * <b>ROTATION - ts</b> - Skew or rotation (-90 degrees to 0 degrees).
	 * <p>
	 * <b>SKEW - ts</b> - Same as {@link Data#ROTATION}.
	 * <p>
	 * <b>PIPELINE_LATENCY - tl</b> - The pipeline’s latency contribution (ms) Add
	 * at least 11ms for image capture latency.
	 * <p>
	 * <b>SIDELENGTH_SHORTEST - tshort</b> - Sidelength of shortest side of the
	 * fitted bounding box (pixels).
	 * <p>
	 * <b>SIDELENGTH_LONGEST - tlong</b> - Sidelength of longest side of the fitted
	 * bounding box (pixels).
	 * <p>
	 * <b>SIDELENGTH_HORIZONTAL - thor</b> - Horizontal sidelength of the rough
	 * bounding box (0 - 320 pixels).
	 * <p>
	 * <b>SIDELENGTH_VERTICAL - tvert</b> - Vertical sidelength of the rough
	 * bounding box (0 - 320 pixels).
	 * <p>
	 * <b>ACTIVE_PIPELINE - getpipe</b> - True active pipeline index of the camera
	 * (0 ... 9).
	 */
	public enum Data {
		TARGET_FOUND("tv"), HORIZONTAL_OFFSET("tx"), VERTICAL_OFFSET("ty"), TARGET_AREA("ta"), ROTATION("ts"),
		SKEW("ts"), PIPELINE_LATENCY("tl"), SIDELENGTH_SHORTEST("tshort"), SIDELENGTH_LONGEST("tlong"),
		SIDELENGTH_HORIZONTAL("thor"), SIDELENGTH_VERTICAL("tvert"), ACTIVE_PIPELINE("getpipe");

		/** {@link NetworkTableEntry} name of the {@link Data data type}. */
		private final String name;

		/**
		 * Constructor for {@link Data}.
		 * 
		 * @param name The name of the {@link NetworkTableEntry} {@link Data Limelight
		 *             data type}
		 */
		private Data(String name) {
			this.name = name;
		}

		/**
		 * Method to get the {@link NetworkTableEntry} name of a {@link Data Limelight
		 * data type}.
		 * 
		 * @return The {@link NetworkTableEntry} name of the specified {@link Data
		 *         Limelight data type}
		 */
		public String getName() {
			return name;
		}
	}

	/**
	 * Raw data types a {@link LimelightCamera Limelight camera} sends over a
	 * {@link NetworkTable}.
	 * <p>
	 * <b>SCREENSPACE_X - tx</b> - Raw Screenspace X.
	 * <p>
	 * <b>SCREENSPACE_Y - ty</b> - Raw Screenspace Y.
	 * <p>
	 * <b>TARGET_AREA - ta</b> - Area (0% of image to 100% of image).
	 * <p>
	 * <b>ROTATION - ts</b> - Skew or rotation (-90 degrees to 0 degrees).
	 * <p>
	 * <b>SKEW - ts</b> - Same as {@link RawData#ROTATION}.
	 */
	public enum RawData {
		SCREENSPACE_X("tx"), SCREENSPACE_Y("ty"), TARGET_AREA("ta"), ROTATION("ts"), SKEW("ts");

		/** {@link NetworkTableEntry} name of the {@link RawData} type. */
		private final String name;

		/**
		 * Constructor for {@link RawData}.
		 * 
		 * @param name The name of the {@link NetworkTableEntry} {@link RawData} type
		 */
		private RawData(String name) {
			this.name = name;
		}

		/**
		 * Method to get the {@link NetworkTableEntry} name of a {@link RawData} type.
		 * 
		 * @return The {@link NetworkTableEntry} name of the specified {@link RawData}
		 *         type
		 */
		public String getName() {
			return name;
		}
	}

	/**
	 * Raw crosshair types a {@link LimelightCamera Limelight camera} sends over a
	 * {@link NetworkTable}.
	 * <p>
	 * <b>CROSSHAIR_X - cx</b> - Crosshair X in normalized screen space.
	 * <p>
	 * <b>CROSSHAIR_Y - cy</b> - Crosshair Y in normalized screen space.
	 */
	public enum RawCrosshair {
		CROSSHAIR_X("cx"), CROSSHAIR_Y("cy");

		/** {@link NetworkTableEntry} name of the {@link RawCrosshair} type. */
		private final String name;

		/**
		 * Constructor for {@link RawCrosshair}.
		 * 
		 * @param name The name of the {@link NetworkTableEntry} {@link RawCrosshair}
		 *             type
		 */
		private RawCrosshair(String name) {
			this.name = name;
		}

		/**
		 * Method to get the {@link NetworkTableEntry} name of a {@link RawCrosshair}
		 * type.
		 * 
		 * @return The {@link NetworkTableEntry} name of the specified
		 *         {@link RawCrosshair} type
		 */
		public String getName() {
			return name;
		}
	}
}