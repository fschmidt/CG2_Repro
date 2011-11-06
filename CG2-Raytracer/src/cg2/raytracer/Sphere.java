package cg2.raytracer;

import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class Sphere implements IShapeColored {
	private final Vector origin;
	private final float radius;
	private final Color color; 

	public Sphere(Vector origin, float radius, Color color) {
		super();
		this.origin = origin;
		this.radius = radius;
		this.color = color;
	}
	 
	public Color getColor() {
		return color;
	}

	public Vector getOrigin() {
		return origin;
	}

	public float getRadius() {
		return radius;
	}

	@Override
	public Hit getHit(Ray ray) {
		if(ray.getGaze().x>0 && ray.getGaze().x < 0.001 && ray.getGaze().y >0 && ray.getGaze().y < 0.001 && this.color.g == 1) {
			int foo = 0; 
		}
		
		Vector x0 = ray.getOrigin().sub(this.origin); // L
		
		Vector d = ray.getGaze(); // V 
		
		float r = radius; 
		float x0DotD = x0.dot(d); // < 0 : Kamera in der Kugel oder mittelpunkt hinter Kamera. 
		float rootResult = x0DotD * x0DotD - 2 * ( x0.dot(x0) - r*r ); 
		
		
		if(rootResult < 0) 
			return null; 
		
		if(rootResult == 0)
			return new Hit(-x0DotD, this.getColor());  
		
		float sqrt = (float)Math.sqrt(rootResult);
		float t1 = -x0DotD + sqrt;
		float t2 = -x0DotD - sqrt;
		
		Hit a = new Hit( t1, getColor() ); 
		Hit b = new Hit( t2, getColor() );
		
		if(t1 > 0 && t2 > 0)
			return t1 < t2 ? a : b;
		
		if(t1 < 0 && t2 > 0)
			return b;
		
		if(t1 > 0 && t2 < 0)
			return a;
		
		return null; 
	}
}
