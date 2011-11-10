package cg2.raytracer;

import java.util.List;

import cg2.raytracer.model.IShapeColored;

/**
 * Class representing a scene containing a list of objects and its camera.
 * 
 * @author Frank and Rico
 *
 */
public class Scene {
	private List<IShapeColored> objects;
	private final Camera camera;

	/**
	 * Creates a new scene with the given camera and objects.
	 * 
	 * @param objects The Objects in the scene
	 * @param camera The camera
	 */
	public Scene(final List<IShapeColored> objects, final Camera camera) {
		super();
		this.objects = objects;
		this.camera = camera;
	}

	/**
	 * @return The camera
	 */
	public Camera getCamera() {
		return camera;
	}

	/**
	 * @param ray The ray to intersect the scenes objects with
	 * @return The Hit Object of the nearest Shape hit or null if no object was hit.
	 */
	public IHitColoredDistance intersect(Ray ray) {
		IHitColoredDistance nearest = null;

		for (IShapeColored shape : objects) {
			if (nearest == null) {
				nearest = shape.getHit(ray);
			} else {
				IHitColoredDistance current = shape.getHit(ray);
				if (current != null && current.compareTo(nearest) < 0) {
					if (current.getT() > 0) {
						nearest = current;
					}
				}
			}
		}

		if (nearest != null && nearest.getT() > 0)
			return nearest;

		return null;
	}
}
