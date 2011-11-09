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
		Hit hit = scene.intersect(ray);

		if (hit != null) {
			return hit.getColor();
		}

		return backgroundColor;
	}
}
