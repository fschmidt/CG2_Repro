package cg2.raytracer.model;

import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.vecmath.Vector;

public class Plane implements IShapeColored {
	private final Vector x;
	private final Vector n;
	private final Material material;

	public Plane(Vector x, Vector n, Material material) {
		super();
		this.x = x;
		this.n = n;
		this.material = material;
	}

	public Vector getX() {
		return x;
	}

	public Vector getN() {
		return n;
	}

	public Material getMaterial() {
		return material;
	}

	@Override
	public Hit getHit(Ray ray) {
		float d = n.dot(x);
		float ndotd = n.dot(ray.getGaze());

		if (ndotd == 0)
			return null;

		float t = (d - n.dot(ray.getOrigin())) / ndotd;

		if (t < 0)
			return null;

		return new Hit(t, material, n);
	}

	@Override
	public String toString() {
		return "Plane [x=" + x + ", n=" + n + ", color=" + material + "]";
	}
}
