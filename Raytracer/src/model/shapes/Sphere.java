package model.shapes;

import main.Constants;
import model.Hit;
import model.Material;
import model.Ray;
import vecmath.Vector;

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
		final Vector x0 = ray.getOrigin();
		final Vector d = ray.getDirection().normalize();
		final Vector c = this.origin;

		final Vector x0subC = x0.sub(c);

		float q = x0subC.dot(x0subC) - r * r;
		float p = 2f * d.dot(x0subC);
		float pHalf = p / 2;
		float rootResult = pHalf * pHalf - q;

		if (rootResult < 0) {
			return null;
		}

		float minusPHalf = -pHalf;

		if (rootResult == 0) {
			return new Hit(minusPHalf, material, getNormal(ray, minusPHalf),
					this);
		}

		float sqrt = (float) Math.sqrt(rootResult);
		float t1 = minusPHalf + sqrt;
		float t2 = minusPHalf - sqrt;

		if (t1 > Constants.EPSILON && t2 > Constants.EPSILON) {
			return new Hit(Math.min(t1, t2), material, getNormal(ray,
					Math.min(t1, t2)), this);
		} else if (t1 <= Constants.EPSILON && t2 > Constants.EPSILON) {
			return new Hit(t2, material, getNormal(ray, t2), this);
		} else if (t1 > Constants.EPSILON && t2 <= Constants.EPSILON) {
			return new Hit(t1, material, getNormal(ray, t1), this);
		} else {
			return null;
		}

	}

	public Vector getNormal(Ray ray, float t) {
		return (ray.getPoint(t).sub(origin)).normalize();
	}
}
