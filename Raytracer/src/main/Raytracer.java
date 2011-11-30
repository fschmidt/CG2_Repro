package main;

import model.Hit;
import model.Material;
import model.Ray;
import model.Scene;
import vecmath.Color;

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
		Hit hit = scene.intersect(ray, Constants.EPSILON, Float.POSITIVE_INFINITY);

		if (hit != null) {
			return hit.getMaterial().shade(ray, hit, scene, Constants.DEPTH, Material.AIR);
		}

		return backgroundColor;
	}
}
