package cg2.raytracer;

import cg2.vecmath.Vector;

public class Ray {

	private final Vector gaze;
	private final Vector origin;
	
	public static float minX = Float.MAX_VALUE; 
	public static float maxX = Float.MIN_VALUE; 
	public static float minY = Float.MAX_VALUE; 
	public static float maxY = Float.MIN_VALUE; 
	public static float minZ = Float.MAX_VALUE; 
	public static float maxZ = Float.MIN_VALUE; 
	

	public Ray(Vector gaze, Vector origin) {
		super();
		
		this.gaze = gaze.normalize();
//		minX = Math.min(minX, this.gaze.x); 
//		minY = Math.min(minY, this.gaze.y); 
//		minZ = Math.min(minZ, this.gaze.z); 
//		maxX = Math.max(maxX, this.gaze.x); 
//		maxY = Math.max(maxY, this.gaze.y); 
//		maxZ = Math.max(maxZ, this.gaze.z); 
//		
//		if(Math.abs( this.gaze.z ) < 1)
//			System.out.println(this.gaze.x + " " + this.gaze.y); 
		
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
