package cg2.raytracerApp;

import java.util.ArrayList;
import java.util.List;

import cg2.raytracer.Camera;
import cg2.raytracer.IShapeColored;
import cg2.raytracer.Plane;
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

		// old scene
		// Sphere sphereA = new Sphere(new Vector(0.0f, 0.0f, 400f), 100000.0f,
		// new Color(255,0,0));
		// Sphere sphereB = new Sphere(new Vector(0.0f, 0.0f, -1000f), 9.0f, new
		// Color(0,0,255));
		//
		// objects.add(sphereA);
		// objects.add(sphereB);
		// Camera camera = new Camera((float) Math.PI / 4, 750.0f, 750.0f);
		// Scene scene = new Scene(objects, camera);

		// new scene
		// the desired pixel resolution

		final int nx = 1024, ny = 768;

		// camera requires specification of field of view (X)

		Camera camera = new Camera(90f, 120, 90, nx, ny);

		// instantiate scene and contained shapes

		Scene scene = new Scene(objects, camera); // empty scene

		// some colors

		final Color red = new Color(1, 0, 0);

		final Color green = new Color(0, 1, 0);

		final Color blue = new Color(0, 0, 1);

		final Color yellow = new Color(1, 1, 0);

		final Color dirt = new Color(0.3f, 0.4f, 0.4f);

		// plane requires any point, a normal, and a color

		// Plane groundPlane = new Plane( new Vector(0f,-1.5f,0f), new
		// Vector(0f,1f,0f), dirt);
		//
		// objects.add(groundPlane);

		// sphere requires specification of center, radius, and color

		Sphere redSphere = new Sphere(new Vector(1.0f, 0.0f, -5.0f), 1000.3f, red);

		Sphere blueSphere = new Sphere(new Vector(3.5f, 0.0f, -7.0f), 1.0f,
				blue);

		Sphere greenSphere = new Sphere(new Vector(-1.0f, 0.0f, -4.0f), 1.5f,
				green);

		objects.add(redSphere);

		objects.add(blueSphere);

		objects.add(greenSphere);

		new ImageGenerator(new Raytracer(scene), nx, ny, filename, "png");
		ImageGenerator.showImage(filename);
	}

}
