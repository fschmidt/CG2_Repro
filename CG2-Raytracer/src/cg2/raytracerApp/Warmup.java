package cg2.raytracerApp;

public class Warmup {

	public static void main(String[] args) {
		String path = System.getProperty("user.home");

		String filename = path + "/" + "raytracer.png";
		new ImageGenerator(new Raytracer(), 750, 750, filename, "png");
		ImageGenerator.showImage(filename);
	}

}
