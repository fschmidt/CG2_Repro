package cg2.raytracer;

import cg2.vecmath.Vector;

public class Ray {
	private final Vector gaze;
	private final Vector origin;

	private Ray(Vector gaze, Vector origin) {
		super();
		
		this.gaze = gaze.normalize();
 
		this.origin = origin;
	}

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
}
