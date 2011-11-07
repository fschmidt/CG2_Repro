package cg2.raytracer;

import java.util.List;
import java.util.ArrayList;

import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class AxisAllignedBox implements IShapeColored {

	private final Vector pMin;
	private final Vector pMax;
	private final Color c;

	public Vector getPMin() {
		return pMin;
	}

	public Vector getPMax() {
		return pMax;
	}

	public Color getColor() {
		return c;
	}

	public AxisAllignedBox(Vector p, Vector q, Color c) {
		super();
		this.pMin = p;
		this.pMax = q;
		this.c = c;

		if (p.x > q.x || p.y > q.y || p.z > q.z) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Hit getHit(Ray ray) {
		Plane p1 = new Plane(pMax, Vector.X, new Color(0, 1, 0));
		Plane p2 = new Plane(pMax, Vector.Y, new Color(0, 0, 1));
		Plane p3 = new Plane(pMin, Vector.Z, new Color(1, 1, 1));
		Plane p4 = new Plane(pMin, Vector.X.mult(-1f), new Color(1, 0, 1));
		Plane p5 = new Plane(pMin, Vector.Y.mult(-1f), new Color(1, 1, 0));
		Plane p6 = new Plane(pMax, Vector.Z.mult(-1f), new Color(1, 0, 0));

		Plane[] planes = new Plane[] { p6, p5, p4, p3, p2, p1 };

		List<Plane> planesWhichPointToMe = new ArrayList<Plane>();

		for (int i = 0; i != 6; i++) {
			Plane plane = planes[i];
			Vector n = plane.getN();
			Vector p = plane.getX();
			Vector x0 = ray.getGaze();
			if (n.dot(x0.sub(p)) > 0)
				planesWhichPointToMe.add(plane);
		}

		// System.out.println(planesWhichPointToMe);

		float tMax = -1;
		Color color = c;

		for (Plane plane : planesWhichPointToMe) {
			Hit hit = plane.getHit(ray);
			if (hit != null) {
				float t = hit.getT();
				if (t > tMax) {
					tMax = t;
					color = hit.getColor();
				}
			}
		}

		Vector d = ray.getOrigin().add(ray.getGaze().mult(tMax));

		if (
				d.x >= pMin.x && d.y >= pMin.y && d.z >= pMin.z && 
				d.x <= pMax.x && d.y <= pMax.y && d.z <= pMax.z)
			return new Hit(tMax, color);

		return null;
	}
}
