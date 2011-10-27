package cg2.raytracer;

import java.util.List;

public class Scene {
	private List<IShape> objects;
	private final Camera camera;

	public Scene(final List<IShape> objects, final Camera camera) {
		super();
		this.objects = objects;
		this.camera = camera;
	}

	public Camera getCamera() {
		return camera;
	}

	/**
	 * @param ray
	 * @return null if no object was hit.
	 */
	public Hit intersect(Ray ray) {
		Hit nearest = null;

		for (IShape shape : objects) {
			if (nearest == null)
				nearest = shape.getHit(ray);
			else {
				Hit current = shape.getHit(ray);
				if (current.compareTo(nearest) < 0)
					nearest = current;
			}
		}

		return nearest;
	}
}
