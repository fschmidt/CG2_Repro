package model;

import model.shapes.IShapeColored;
import vecmath.Vector;

/**
 * Represents a Hit containing the color of the shape hit
 * 
 * @author Frank
 * 
 */
public class Hit implements Comparable<Hit> {
	private final float t;
	private final Material material;
	private final Vector normal;
	private final IShapeColored shape;

	public Hit(float t, Material material, Vector normal, IShapeColored shape) {
		super();
		this.t = t;
		this.material = material;
		this.normal = normal;
		this.shape = shape;
	}

	public float getT() {
		return t;
	}

	public Material getMaterial() {
		return material;
	}

	@Override
	public int compareTo(Hit o) {
		if (o == null) {
			throw new IllegalArgumentException();
		}

		float dif = this.t - o.t;

		if (dif < 0)
			return -1;

		if (dif > 0)
			return 1;

		return 0;
	}

	public Vector getNormal() {
		return normal;
	}

	public IShapeColored getShape() {
		return shape;
	}
}
