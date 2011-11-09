package cg2.raytracer.model;

import cg2.raytracer.Hit;
import cg2.raytracer.Ray;

public interface IShape {

	/**
	 * @param ray
	 * @return null if Ray doesn't hit shape.
	 */
	public abstract Hit getHit(final Ray ray);

}