package model.shapes;

import main.Constants;
import model.Hit;
import model.Material;
import model.Ray;
import vecmath.Vector;

public class Plane implements IShapeColored {
	private final Vector position;
	private final Vector normal;
	private final Material material;

	public Plane(Vector position, Vector normal, Material material) {
		super();
		this.position = position;
		this.normal = normal.normalize();
		this.material = material;
	}

	public Vector getX() {
		return position;
	}

	public Vector getN() {
		return normal;
	}

	public Material getMaterial() {
		return material;
	}

	@Override
	public Hit getHit(Ray ray) {
		float d = normal.dot(position);
		float ndotd = normal.dot(ray.getDirection());

		if (ndotd == 0) {
			return null;
		}

		float t = (d - normal.dot(ray.getOrigin())) / ndotd;

		if (t < Constants.EPSILON) {
			return null;
		}
		
		return new Hit(t, material, normal, this);
	}

	@Override
	public String toString() {
		return "Plane [x=" + position + ", n=" + normal + ", color=" + material
				+ "]";
	}
}
