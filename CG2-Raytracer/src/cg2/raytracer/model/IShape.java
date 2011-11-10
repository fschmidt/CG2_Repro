package cg2.raytracer.model;

import cg2.raytracer.IHitDistance;
import cg2.raytracer.Ray;

public interface IShape {

	/**
	 * @param ray
	 * @return null if Ray doesn't hit shape.
	 */
	IHitDistance getHit(final Ray ray);
}