package cg2.raytracer.model;

import java.util.List;
import java.util.ArrayList;

import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
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
		Plane p1 = new Plane(pMax, Vector.X, null);
		Plane p2 = new Plane(pMax, Vector.Y, null);
		Plane p3 = new Plane(pMax, Vector.Z, null);
		Plane p4 = new Plane(pMin, Vector.X.mult(-1f), null);
		Plane p5 = new Plane(pMin, Vector.Y.mult(-1f), null);
		Plane p6 = new Plane(pMin, Vector.Z.mult(-1f), null);
		float epsilon = 0.0002f;

		Plane[] planes = new Plane[] { p6, p5, p4,p3,p2,p1};

		List<Plane> planesWhichPointToMe = new ArrayList<Plane>();

		for (Plane plane : planes) {
			Vector ni = plane.getN();
			Vector pi = plane.getX();
			Vector x0 = ray.getOrigin();
			// check whether each planes normal points to the camera
			if (ni.dot(x0.sub(pi)) > epsilon){
				planesWhichPointToMe.add(plane);
			}
		}

		float tMax = Float.MIN_VALUE;

		for (Plane plane : planesWhichPointToMe) {
			Hit hit = plane.getHit(ray);
			
			if (hit != null) {
				float t = hit.getT();
				if (t > tMax) {
					tMax = t; 
				}
			}
		}

		Vector d = ray.getOrigin().add(ray.getGaze().mult(tMax));
		
		if (d.x >= pMin.x - epsilon && d.y >= pMin.y - epsilon
				&& d.z >= pMin.z - epsilon && d.x <= pMax.x + epsilon
				&& d.y <= pMax.y + epsilon && d.z <= pMax.z + epsilon) {
			return new Hit(tMax, c);
		} else {
			return null;
		}

	}
}
