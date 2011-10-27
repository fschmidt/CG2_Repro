package cg2.raytracer;

import cg2.vecmath.Vector;

public class Camera {
	private final Vector position;
	private final Vector gazeDirection;
	private final float oeffnungswinkel;
	private final float width;
	private final float height;

	public Camera(float oeffnungswinkel, float width, float height) {
		this(new Vector(0.0f, 0.0f, 0.0f), new Vector(0.0f, 0.0f, -1.0f),
				oeffnungswinkel, width, height);
	}

	public Camera(Vector position, Vector gazeDirection, float oeffnungswinkel,
			float width, float height) {
		super();
		this.position = position;
		this.gazeDirection = gazeDirection;
		if (oeffnungswinkel <= 0.0f || oeffnungswinkel >= 180.0f) {
			throw new IllegalArgumentException("oeffnungswinkel falsch");
		}
		this.oeffnungswinkel = oeffnungswinkel;
		this.width = width;
		this.height = height;
	}

	public Vector getPosition() {
		return position;
	}

	public Vector getGazeDirection() {
		return gazeDirection;
	}

	public float getOeffnungswinkel() {
		return oeffnungswinkel;
	}

	public Ray getRay(int i, int j) {
//		System.out.println("i: " + i);
//		System.out.println("j: " + j);
//		System.out.println((float) (width / (2.0 * Math
//				.tan(oeffnungswinkel / 2.0))));
		float za = (float) (width / (2.0 * Math.tan(oeffnungswinkel / 2.0)));
		float xi = -(width / 2) + (i + 0.5f);
		float yj = -(height / 2) + (j + 0.5f);
		return new Ray(new Vector(xi, yj, -za), position);
	}
}
