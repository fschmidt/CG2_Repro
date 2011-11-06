package cg2.raytracer;

import cg2.vecmath.Vector;

public class Camera { 
	private final float angle;
	private final float width;
	private final float height;

	public Camera(float angle, float width, float height) {
		this.angle = angle; 
		this.width = width; 
		this.height = height; 
	}

	public float getAngle() {
		return angle;
	}

	public Ray getRay(int i, int j) {
//		System.out.println("i: " + i);
//		System.out.println("j: " + j);
//		System.out.println((float) (width / (2.0 * Math
//				.tan(oeffnungswinkel / 2.0))));
		float za = (float) (width / (2.0 * Math.tan(angle*Math.PI/180.0f / 2.0)));
		float xi = -(width / 2) + (i + 0.5f);
		float yj = -(height / 2) + (j + 0.5f);
		return new Ray(new Vector(xi, yj, za));
	}
}
