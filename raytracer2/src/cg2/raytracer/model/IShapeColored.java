package cg2.raytracer.model;

import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.vecmath.Vector;

public interface IShapeColored {

	/**
	 * @param ray
	 * @return null if Ray doesn't hit shape.
	 */
	Hit getHit(final Ray ray);
	Material getMaterial();
}