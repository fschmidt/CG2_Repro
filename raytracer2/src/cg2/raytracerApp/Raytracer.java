package cg2.raytracerApp;

import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.raytracer.Scene;
import cg2.vecmath.Color;

public class Raytracer implements Painter {

	private Scene scene;
	private Color backgroundColor;

	public Raytracer(Scene scene, Color backgroundColor) {
		super();
		this.scene = scene;
		this.backgroundColor = backgroundColor;
	}

	@Override
	public Color pixelColorAt(int x, int y, int width, int height) {
		Ray ray = scene.getCamera().getRay(x, y);
		Hit hit = scene.intersect(ray,0.0f,Float.POSITIVE_INFINITY);
		

		if (hit != null) {
			Color result = hit.getMaterial().shade(ray, hit, scene, 2);
			return result;
		}

		return backgroundColor;
	}
}
