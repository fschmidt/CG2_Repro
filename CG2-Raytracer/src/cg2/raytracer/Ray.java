package cg2.raytracer;

import cg2.vecmath.Vector;

public class Ray {
	private final Vector direction; 
	private final Vector origin;
	
	public Ray(Vector direction, Vector origin) {
		super();
		this.direction = direction.normalize();
		this.origin = origin;
	}
	
	public Vector getDirection() {
		return direction;
	}
	
	public Vector getOrigin() {
		return origin;
	} 	
}
