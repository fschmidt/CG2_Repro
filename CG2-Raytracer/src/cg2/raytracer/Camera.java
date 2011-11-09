package cg2.raytracer;

import cg2.vecmath.Vector;

/**
 * Represents a camera.
 * 
 * @author Frank
 *
 */
public class Camera {
	private final float angle;
	private final float virtualWidth;
	private final float virtualHeight;
	private final float physicalWidth;
	private final float physicalHeight;

	/**
	 * @param angle
	 * @param virtualWidth
	 * @param virtualHeight
	 * @param physicalWidth
	 * @param physicalHeight
	 */
	public Camera(float angle, float virtualWidth, float virtualHeight,
			float physicalWidth, float physicalHeight) {
		super();
		this.angle = angle;
		this.virtualWidth = virtualWidth;
		this.virtualHeight = virtualHeight;
		this.physicalWidth = physicalWidth;
		this.physicalHeight = physicalHeight;
	}

	public float getAngle() {
		return angle;
	}

	public Ray getRay(int i, int j) {
		float za = (float) (virtualWidth / (2.0 * Math
				.tan((angle * (Math.PI / 180.0f)) / 2.0)));
		float xi = -(virtualWidth / 2) + (i + 0.5f)
				* (virtualWidth / physicalWidth);
		float yj = -(virtualHeight / 2) + (j + 0.5f)
				* (virtualHeight / physicalHeight);
		return new Ray(new Vector(xi, yj, -za));
	}
}
