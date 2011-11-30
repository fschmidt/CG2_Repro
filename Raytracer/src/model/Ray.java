package model;

import vecmath.Vector;

/**
 * Represents a ray through an origin and a direction
 * 
 * @author Frank and Rico
 * 
 */
public class Ray {
	private final Vector direction;
	private final Vector origin;

	/**
	 * @param direction
	 *            The direction of the ray
	 * @param origin
	 *            The origin of the ray
	 */
	public Ray(Vector direction, Vector origin) {
		super();

		this.direction = direction.normalize();

		this.origin = origin;
	}
	
//	public Ray(Vector direction, Vector origin) {
//		super();
//
//		this.direction = direction.normalize();
//
//		this.origin = origin;
//	}

	/**
	 * Creates a new Ray with its origin at (0,0,0)
	 * 
	 * @param direction
	 *            The origin of the ray
	 */
	public Ray(Vector direction) {
		this(direction, Vector.Zero);
	}
	
//	public Ray(Vector direction) {
//		this(Vector.Zero, direction);
//	}

	public Vector getDirection() {
		return direction;
	}

	public Vector getOrigin() {
		return origin;
	}

	@Override
	public String toString() {
		return "Ray [direction=" + direction + ", origin=" + origin + "]";
	}

	public Vector getPoint(float t) {
		return origin.add(direction.mult(t));
	}
}
