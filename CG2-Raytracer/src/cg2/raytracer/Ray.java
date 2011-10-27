package cg2.raytracer;

import cg2.vecmath.Vector;

public class Ray {

	private final Vector direction;
	private final Vector origin;
	
	public static float minX = Float.MAX_VALUE; 
	public static float maxX = Float.MIN_VALUE; 
	public static float minY = Float.MAX_VALUE; 
	public static float maxY = Float.MIN_VALUE; 
	public static float minZ = Float.MAX_VALUE; 
	public static float maxZ = Float.MIN_VALUE; 
	

	public Ray(Vector direction, Vector origin) {
		super();
//		System.out.println(direction);
		
		
		
		this.direction = direction.normalize();
		minX = Math.min(minX, this.direction.x); 
		minY = Math.min(minY, this.direction.y); 
		minZ = Math.min(minZ, this.direction.z); 
		maxX = Math.max(maxX, this.direction.x); 
		maxY = Math.max(maxY, this.direction.y); 
		maxZ = Math.max(maxZ, this.direction.z); 
		
		if(Math.abs( this.direction.z ) < 1)
			System.out.println(this.direction.x + " " + this.direction.y); 
		
		this.origin = origin;
	}

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

}
