package model;

import java.util.List;

import main.Constants;
import model.shapes.IShapeColored;
import model.shapes.Plane;
import vecmath.Color;

/**
 * Class representing a scene containing a list of objects and its camera.
 * 
 * @author Frank and Rico
 * 
 */
public class Scene {
	private final List<IShapeColored> objects;
	private final List<LightSource> lights;
	private final Color ambientLight;
	private final Camera camera;

	/**
	 * Creates a new scene with the given camera and objects.
	 * 
	 * @param objects
	 *            The Objects in the scene
	 * @param camera
	 *            The camera
	 */
	public Scene(final List<IShapeColored> objects,
			final List<LightSource> lights, final Color ambientLight,
			final Camera camera) {
		this.objects = objects;
		this.lights = lights;
		this.camera = camera;
		this.ambientLight = ambientLight;
	}

	/**
	 * @return The camera
	 */
	public Camera getCamera() {
		return camera;
	}

	public List<IShapeColored> getObjects() {
		return objects;
	}

	public List<LightSource> getLights() {
		return lights;
	}

	public Color getAmbientLight() {
		return ambientLight;
	}

	/**
	 * @param ray
	 *            The ray to intersect the scenes objects with
	 * @return The Hit Object of the nearest Shape hit or null if no object was
	 *         hit.
	 */
	public Hit intersect(Ray ray, float tMin, float tMax) {

		Hit nearest = null;

		for (IShapeColored shape : objects) {
			if (nearest == null) {
				nearest = shape.getHit(ray);
			} else {
				Hit current = shape.getHit(ray);
				if (current != null && current.compareTo(nearest) < 0) {
					if (current.getT() > Constants.EPSILON) {
						nearest = current;
					}
				}
			}
		}
		return nearest;
	}
}
