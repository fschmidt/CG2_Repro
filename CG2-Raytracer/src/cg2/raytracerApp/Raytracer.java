package cg2.raytracerApp;

import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.raytracer.Scene;
import cg2.vecmath.Color;

public class Raytracer implements Painter {
	
	private Scene scene;
	
	public Raytracer(Scene scene) {
		super();
		this.scene = scene;
	}

	@Override
	public Color pixelColorAt(int x, int y, int width, int height) {
		Ray ray = scene.getCamera().getRay(x, y);
		Hit hit = scene.intersect(ray);
		
		if(hit!=null){
			return hit.getColor();
		}
		
		return new Color(0,0,0);
	}
}
