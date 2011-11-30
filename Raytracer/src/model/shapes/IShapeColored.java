package model.shapes;

import model.Hit;
import model.Material;
import model.Ray;


public interface IShapeColored {

	/**
	 * @param ray
	 * @return null if Ray doesn't hit shape.
	 */
	Hit getHit(final Ray ray);
	Material getMaterial();
}