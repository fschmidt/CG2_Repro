package cg2.raytracer;

public interface IShape {

	/**
	 * @param ray
	 * @return null if Ray doesn't hit shape.
	 */
	public abstract Hit getHit(final Ray ray);

}