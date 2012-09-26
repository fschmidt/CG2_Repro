package main;

import java.util.ArrayList;
import java.util.List;

import model.Camera;
import model.LightSource;
import model.Material;
import model.Scene;
import model.shapes.AxisAlignedBox;
import model.shapes.IShapeColored;
import model.shapes.Plane;
import model.shapes.Sphere;
import model.shapes.Triangle;
import vecmath.Color;
import vecmath.Vector;

public class Main {

	public static void main(String[] args) {
		System.out.println("Raytracer start");

		String path = System.getProperty("user.home");
		String filename = path + "/" + "raytracer.png";

		List<IShapeColored> objects = new ArrayList<IShapeColored>();
		List<LightSource> lights = new ArrayList<LightSource>();

		// pixel dimensions for the picture
		final int nx = 2048;
		final int ny = 1536;

		// the colors used for the objects in the scene
		final Color red = new Color(0.8f, 0, 0);
		final Color ambientRed = new Color(0.5f, 0, 0);
		final Color reflectRed = new Color(0.3f, 0, 0);
		final Color refractRed = new Color(0.3f, 0.001f, 0.001f);

		final Color green = new Color(0, 0.2f, 0);
		final Color ambientGreen = new Color(0, 0.1f, 0);
		final Color refractGreen = new Color(0.8f, 0.8f, 0.8f);

		final Color blue = new Color(0, 0, 0.8f);
		final Color ambientBlue = new Color(0, 0, 0.5f);
		final Color refractBlue = new Color(0, 0, 0.3f);

		final Color yellow = new Color(1, 1, 0);
		final Color ambientYellow = new Color(0.5f, 0.5f, 0);
		final Color reflectYellow = new Color(0.3f, 0.3f, 0);

		final Color dirt = new Color(0.2f, 0.2f, 0.2f);
		final Color boxColor = new Color(0, 0.8f, 1.0f);
		final Color background = new Color(0, 0, 0);
		final Color white = new Color(0.8f, 0.8f, 0.8f);
		final float phongExp = 300f;

		final Material redMaterial = new Material(ambientRed, red, white,
				white, refractRed, phongExp, Material.SOLID);

		final Material greenMaterial = new Material(ambientGreen, green, white,
				white, refractGreen, phongExp, Material.AIR+0.00001f);

		final Material blueMaterial = new Material(ambientBlue, blue, white,
				white, refractBlue, phongExp, Material.DIAMOND);

		final Material yellowMaterial = new Material(ambientYellow, yellow,
				white, reflectYellow, reflectYellow, phongExp);

		final Material planeMaterial = new Material(dirt, dirt, white, white,
				white, phongExp, Material.SOLID);

		// plane requires any point, a normal, and a color
		Plane groundPlane = new Plane(new Vector(0f, -3.0f, -1.0f), new Vector(
				0f, 1f, 0f), planeMaterial);
		Plane rightPlane = new Plane(new Vector(10f, -3.0f, -1f), new Vector(
				-10f, 0f, -5f), planeMaterial);
		Plane backPlane = new Plane(new Vector(0, 0, -200), Vector.Z,
				planeMaterial);

		// sphere requires specification of center, radius, and color
		Sphere redSphere2 = new Sphere(new Vector(-1.0f, -1.0f, -20.0f), 1.3f,
				redMaterial);
		Sphere redSphere = new Sphere(new Vector(4.0f, -1.0f, -7.0f), 1.3f,
				redMaterial);
		Sphere blueSphere = new Sphere(new Vector(-3.5f, -1.0f, -6.0f), 1.0f,
				blueMaterial);
		Sphere greenSphere = new Sphere(new Vector(-1.0f, 1.5f, -8.0f), 1.5f,
				greenMaterial);

		// an axis aligned box requires only two points
		AxisAlignedBox box = new AxisAlignedBox(new Vector(-3, 1, -5),
				new Vector(-4, -2, -9), redMaterial);

		// a triangle is defined by 3 points
		// Triangle triangle = new Triangle(new Vector(-9f, 5f, -10f), new
		// Vector(
		// -7f, 5f, -10f), new Vector(-8f, 7f, -10f), greenMaterial);
		//
		// Triangle triangle2 = new Triangle(new Vector(-7f, 5f, -10f),
		// new Vector(-5f, 5f, -10f), new Vector(-6f, 7f, -10f),
		// redMaterial);

		objects.add(groundPlane);
		objects.add(rightPlane);
		objects.add(redSphere2);
		objects.add(redSphere);
		objects.add(blueSphere);
		objects.add(greenSphere);
		// objects.add(box);
		// objects.add(triangle);
		// objects.add(triangle2);

		// camera (field of view, viewPlane width, viewPlane height, picture
		// width, picture height)
		Camera camera = new Camera(90f, 12, 9, nx, ny);

		// add some lights
		lights.add(new LightSource(new Vector(-5.0f, 10.0f, -10.0f), white));
		lights.add(new LightSource(new Vector(-4.0f, 12.0f, -15.0f), white));
		lights.add(new LightSource(new Vector(-8.0f, 9.0f, 10.0f), white));
		lights.add(new LightSource(new Vector(-1.0f, 200.0f, -10.0f), white));

		// instantiate scene an empty scene with yellow ambient light source
		Scene scene = new Scene(objects, lights, new Color(0.5f, 0.5f, 0.5f),
				camera);

		new ImageGenerator(new Raytracer(scene, background), nx, ny, filename,
				"png");
		ImageGenerator.showImage(filename);

		System.out.println("Raytracer end");
		System.out.printf("You can find the file at %s%n", filename);
		//System.out.println(Material.hitcount);
	}
}
