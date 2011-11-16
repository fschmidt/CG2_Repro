package cg2.raytracer.model;

import cg2.raytracer.HitColoredDistance;
import cg2.raytracer.IHitColoredDistance;
import cg2.raytracer.IHitDistance;
import cg2.raytracer.Ray;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;
import de.bht.fb6.cg1.math.IMatrix;

public class Model implements IShapeColored {
	private final IMatrix<Float> mesh;
	private final int[][] faces;
	private final Color color;
	private final AxisAllignedBox surroundingBox;

	public Model(IMatrix<Float> mesh, int[][] faces) {
		this(mesh, faces, new Color(1, 1, 1));
	}

	public Model(IMatrix<Float> mesh, int[][] faces, Color color) {
		this.mesh = mesh;
		this.faces = faces;
		this.color = color;
		this.surroundingBox = calculateSurroundingBox(mesh, faces);
	}

	private AxisAllignedBox calculateSurroundingBox(IMatrix<Float> mesh, int[][] faces) {
		float[] max = new float[] { Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE };
		float[] min = new float[] { Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE };

		for (int m = 0; m != mesh.getColumns(); m++) {
			max[0] = Math.max(max[0], mesh.get(0, m));
			max[1] = Math.max(max[1], mesh.get(1, m));
			max[2] = Math.max(max[2], mesh.get(2, m));

			min[0] = Math.min(min[0], mesh.get(0, m));
			min[1] = Math.min(min[1], mesh.get(1, m));
			min[2] = Math.min(min[2], mesh.get(2, m));
		}

		Vector p = new Vector(min[0], min[1], min[2]);
		Vector q = new Vector(max[0], max[1], max[2]);

		AxisAllignedBox box = new AxisAllignedBox(p, q);

		return box;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IHitColoredDistance getHit(Ray ray) {
		IHitDistance hit = null;
		for (int f = 0; f != faces.length; f++) {
			int f1 = faces[f][0];
			int f2 = faces[f][1];
			int f3 = faces[f][2];

			Vector v1 = new Vector(mesh.get(0, f1), mesh.get(1, f1), mesh.get(2, f1));
			Vector v2 = new Vector(mesh.get(0, f2), mesh.get(1, f2), mesh.get(2, f2));
			Vector v3 = new Vector(mesh.get(0, f3), mesh.get(1, f3), mesh.get(2, f3));

			Triangle triangle = new Triangle(v1, v2, v3, null);

			IHitDistance lastHit = triangle.getHit(ray);

			if (lastHit == null)
				continue;

			if (hit == null) {
				hit = lastHit;
			} else {
				if (lastHit.getT() < hit.getT() && lastHit.getT() > 0)
					hit = lastHit;
			}
		}

		if (hit == null)
			return null;

		return new HitColoredDistance(hit.getT(), this.color);
	}

}
