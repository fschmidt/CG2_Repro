package cg2.raytracer.model;

import cg2.raytracer.HitColoredDistance;
import cg2.raytracer.Ray;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class Triangle implements IShapeColored {
	private final Vector p0;
	private final Vector p1;
	private final Vector p2;
	private final Color c;

	public Triangle(Vector p0, Vector p1, Vector p2, Color c) {
		super();
		this.p0 = p0;
		this.p1 = p1;
		this.p2 = p2;
		this.c = c;
	}

	@Override
	public HitColoredDistance getHit(Ray ray) {

		// calculate the vectors defining the plane of the triangle
		Vector e1 = p1.sub(p0);
		Vector e2 = p2.sub(p0);

		// calculate the result of the linear equation system
		Vector s = ray.getOrigin().sub(p0);
		Vector p = ray.getGaze().cross(e2);
		Vector q = s.cross(e1);
		Vector x = new Vector(q.dot(e2), p.dot(s), q.dot(ray.getGaze()));
		Vector tvu = x.mult(1 / p.dot(e1));

		float t = tvu.x;
		float u = tvu.y;
		float v = tvu.z;

		HitColoredDistance hit = new HitColoredDistance(t, c);

		if (u > 0 && v > 0 && u + v < 1.0f) {
			return hit;
		}

		return null;
	}

	@Override
	public Color getColor() {
		return c;
	}

}
