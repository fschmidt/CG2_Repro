package cg2.raytracer;

import cg2.vecmath.Color;

public interface IShape {
	/**
	 * @param ray
	 * @return null if Ray doesn't hit shape. 
	 */
	Hit getHit(final Ray ray);
	
	Color getColor(); 
}
