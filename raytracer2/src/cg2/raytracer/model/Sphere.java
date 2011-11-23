package cg2.raytracer.model;

import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.vecmath.Vector;

public class Sphere implements IShapeColored {
	private final Vector origin;
	private final float radius;
	private final Material material; 
	
	public Sphere(Vector origin, float radius, Material material) {
		super();
		this.origin = origin;
		this.radius = radius;
		this.material = material;
	}

	public Material getMaterial() {
		return material;
	}

	public Vector getOrigin() {
		return origin;
	}

	public float getRadius() {
		return radius;
	}

	@Override
	public Hit getHit(Ray ray) {
		final float r = radius;
		final Vector x0 = ray.getOrigin(); // Ursprung des Rays
		final Vector d = ray.getGaze().normalize(); // Gaze Direction
		final Vector c = this.origin; // Ursprung der Sphäre

		final Vector x0subC = x0.sub(c);

		float q = x0subC.dot(x0subC) - r * r;
		float p = 2f * d.dot(x0subC);
		float pHalf = p / 2;
		float underSqrt = pHalf * pHalf - q;

		if (underSqrt < 0)
			return null;

		float minusPHalf = -pHalf;

		if (underSqrt == 0) {
			return new Hit(minusPHalf, material);
		}

		float sqrt = (float) Math.sqrt(underSqrt);
		float t1 = minusPHalf + sqrt;
		float t2 = minusPHalf - sqrt;

		if (t1 > 0 && t2 > 0)
			return t1 > t2 ? new Hit(t2, material) : new Hit(t1, material);

		if (t1 <= 0 && t2 > 0)
			return new Hit(t2, material);

		if (t1 > 0 && t2 <= 0)
			return new Hit(t1, material);

		return null;
	}
}
