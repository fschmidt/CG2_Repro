package cg2.raytracerApp;

import java.util.ArrayList;
import java.util.List;

import cg2.raytracer.Camera;
import cg2.raytracer.IShapeColored;
import cg2.raytracer.Ray;
import cg2.raytracer.Scene;
import cg2.raytracer.Sphere;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class Warmup {

	public static void main(String[] args) {
		String path = System.getProperty("user.home");

		String filename = path + "/" + "raytracer.png";

		List<IShapeColored> objects = new ArrayList<IShapeColored>();
		
		//Sphere sphereA = new Sphere(new Vector(0.0f, 0.0f, 400f), 100000.0f, new Color(255,0,0));
		Sphere sphereB = new Sphere(new Vector(0.0f, 0.0f, -1000f), 9.0f, new Color(0,0,255));
		
//		objects.add(sphereA);
		objects.add(sphereB);
		Camera camera = new Camera((float) Math.PI / 4, 750.0f, 750.0f);
		Scene scene = new Scene(objects, camera);

		new ImageGenerator(new Raytracer(scene), 750, 750, filename, "png");
		ImageGenerator.showImage(filename);
		
		System.out.printf("%f %f %f %f %f %f%n", Ray.minX, Ray.maxX, Ray.minY, Ray.maxY, Ray.minZ, Ray.maxZ); 
	}

}
