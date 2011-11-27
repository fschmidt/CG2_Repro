package cg2.raytracer;

import cg2.vecmath.Vector;

/**
 * Represents a ray through an origin and a direction
 * 
 * @author Frank and Rico
 * 
 */
public class Ray {
	private final Vector gaze;
	private final Vector origin;

	/**
	 * @param gaze
	 *            The direction of the ray
	 * @param origin
	 *            The origin of the ray
	 */
	public Ray(Vector gaze, Vector origin) {
		super();

		this.gaze = gaze.normalize();

		this.origin = origin;
	}

	/**
	 * Creates a new Ray with its origin at (0,0,0)
	 * 
	 * @param gaze
	 *            The origin of the ray
	 */
	public Ray(Vector gaze) {
		this(gaze, Vector.Zero);
	}

	public Vector getGaze() {
		return gaze;
	}

	public Vector getOrigin() {
		return origin;
	}

	@Override
	public String toString() {
		return "Ray [direction=" + gaze + ", origin=" + origin + "]";
	}

	public Vector getPoint(float t) {
		return origin.add(gaze.mult(t));
	}
}
