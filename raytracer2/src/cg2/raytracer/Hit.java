package cg2.raytracer;

import cg2.raytracer.model.Material;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

/**
 * Represents a Hit containing the color of the shape hit
 * 
 * @author Frank
 *
 */
public class Hit implements Comparable<Hit> {
	private final float t;
	private final Material material; 
	//private final Vector normal; // usw! 

	public Hit(float t, Material material) {
		super();
		this.t = t;
		this.material = material;
	}

	public float getT() {
		return t;
	}

	public Material getMaterial() {
		return material;
	}

	@Override
	public int compareTo(Hit o) {
		if (o == null)
			throw new IllegalArgumentException();

		float dif = this.t - o.t;

		if (dif < 0)
			return -1;

		if (dif > 0)
			return 1;

		return 0;
	}
}
