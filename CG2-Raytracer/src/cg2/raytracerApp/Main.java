package cg2.raytracerApp;

import java.util.ArrayList;
import java.util.List;

import cg2.raytracer.Camera;
import cg2.raytracer.Scene;
import cg2.raytracer.model.AxisAllignedBox;
import cg2.raytracer.model.IShapeColored;
import cg2.raytracer.model.Plane;
import cg2.raytracer.model.Sphere;
import cg2.raytracer.model.Triangle;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class Main {

	public static void main(String[] args) {
		String path = System.getProperty("user.home");
		String filename = path + "/" + "raytracer.png";

		List<IShapeColored> objects = new ArrayList<IShapeColored>();

		// pixel dimensions for the picture
		final int nx = 1024;
		final int ny = 768;

		// the colors used for the objects in the scene
		final Color red = new Color(1, 0, 0);
		final Color green = new Color(0, 1, 0);
		final Color blue = new Color(0, 0, 1);
		final Color yellow = new Color(1, 1, 0);
		final Color dirt = new Color(0.55f, 0.46f, 0.4f);
		final Color boxColor = new Color(0, 0.8f, 1.0f);
		final Color background = new Color(0, 0, 0);

		// camera (field of view, viewPlane width, viewPlane height, picture
		// width, picture height)
		Camera camera = new Camera(90f, 12, 9, nx, ny);

		// instantiate scene an empty scene
		Scene scene = new Scene(objects, camera);

		// plane requires any point, a normal, and a color
		Plane groundPlane = new Plane(new Vector(0f, -1.0f, 0f), new Vector(0f,
				1f, 0f), dirt);
		Plane rightPlane = new Plane(new Vector(10f, 0f, -1f), new Vector(-12f,
				0f, 1f), yellow);

		// sphere requires specification of center, radius, and color
		Sphere redSphere = new Sphere(new Vector(1.0f, 0.0f, -5.0f), 1.3f, red);
		Sphere blueSphere = new Sphere(new Vector(-4.5f, 1.0f, -7.0f), 1.0f,
				blue);
		Sphere greenSphere = new Sphere(new Vector(-1.0f, 0.0f, -4.0f), 1.5f,
				green);
		Sphere sphereFoo = new Sphere(new Vector(9.0f, -1.0f, -11.5f), 2.5f,
				red.darken(0.4f));

		// an axis aligned box requires only two points
		AxisAllignedBox box = new AxisAllignedBox(new Vector(2, 1, -12),
				new Vector(6, 5, -10), boxColor);

		// a triangle is defined by 3 points
		Triangle triangle = new Triangle(new Vector(-9f, 5f, -10f), new Vector(
				-7f, 5f, -10f), new Vector(-8f, 7f, -10f), green);

		Triangle triangle2 = new Triangle(new Vector(-7f, 5f, -10f),
				new Vector(-5f, 5f, -10f), new Vector(-6f, 7f, -10f), blue.darken(0.8f));

		objects.add(groundPlane);
		objects.add(rightPlane);
		objects.add(redSphere);
		objects.add(blueSphere);
		objects.add(greenSphere);
		objects.add(sphereFoo);
		objects.add(box);
		objects.add(triangle);
		objects.add(triangle2);

		new ImageGenerator(new Raytracer(scene, background), nx, ny, filename,
				"png");
		ImageGenerator.showImage(filename);
	}
}
