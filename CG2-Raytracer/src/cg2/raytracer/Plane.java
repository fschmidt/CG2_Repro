package cg2.raytracer;

import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class Plane implements IShape {
	private final Vector x0; 
	private final Vector n;
	private final Color color; 
		 
	public Plane(Vector x0, Vector n, Color color) {
		super();
		this.x0 = x0;
		this.n = n;
		this.color = color;
	}

	public Vector getX0() {
		return x0;
	}
	
	public Vector getN() {
		return n;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public Hit getHit(Ray ray) {
		Vector v = ray.getDirection().cross(n);
		
		throw new UnsupportedOperationException(); 
	}
}
