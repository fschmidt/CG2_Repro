package model.shapes;

import java.util.ArrayList;
import java.util.List;

import main.Constants;
import model.Hit;
import model.Material;
import model.Ray;

import vecmath.Vector;

public class AxisAlignedBox implements IShapeColored {

	private final Vector pMin;
	private final Vector pMax;
	private final Material material;
	private final List<Plane> planes;

	public Material getMaterial() {
		return material;
	}

	public AxisAlignedBox(Vector pMin, Vector pMax, Material m) {
		this.pMin = pMin;
		this.pMax = pMax;
		this.material = m;

		planes = new ArrayList<Plane>();

		planes.add(new Plane(pMin, new Vector(0, 0, 1), material));
		planes.add(new Plane(pMin, new Vector(0, 1, 0), material));
		planes.add(new Plane(pMin, new Vector(1, 0, 0), material));
		planes.add(new Plane(pMax, new Vector(0, 0, -1), material));
		planes.add(new Plane(pMax, new Vector(0, -1, 0), material));
		planes.add(new Plane(pMax, new Vector(-1, 0, 0), material));

	}

	public Vector getPMin() {
		return pMin;
	}

	public Vector getPMax() {
		return pMax;
	}

	@Override
	public Hit getHit(Ray ray) {

		List<Hit> hits = new ArrayList<Hit>();

		for (Plane plane : planes) {
			Hit hit = plane.getHit(ray);
			if (hit != null) {
				Vector raypoint = ray.getPoint(hit.getT());
				if ((ray.getOrigin().sub(raypoint)).dot(plane.getN()) > -Constants.EPSILON) {
					hits.add(hit);
				}
			}
		}
		
		Hit farthest = null;
		// farthest hit
		for (Hit hit : hits) {
			
			if(farthest == null){
				farthest = hit;
			}else if (farthest != null && farthest.getT() > hit.getT()) {
				farthest = hit;
			}
		}

		// now check whether the point is legal
		if (farthest != null) {
			Vector raypoint = ray.getPoint(farthest.getT());
			if (raypoint.x + Constants.EPSILON >= Math.min(pMin.x, pMax.x)
					&& raypoint.x - Constants.EPSILON <= Math.max(pMin.x,
							pMax.x)
					&& raypoint.y + Constants.EPSILON >= Math.min(pMin.y,
							pMax.y)
					&& raypoint.y - Constants.EPSILON <= Math.max(pMin.y,
							pMax.y)
					&& raypoint.z + Constants.EPSILON >= Math.min(pMin.z,
							pMax.z)
					&& raypoint.z - Constants.EPSILON <= Math.max(pMin.z,
							pMax.z)) {
				return farthest;
			}
		}
		return null;

	}
}