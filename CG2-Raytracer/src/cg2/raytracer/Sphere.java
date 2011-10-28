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
		
		Vector x0 = ray.getOrigin().sub(this.origin);
		
		Vector d = ray.getGaze();
		
		float r = radius; 
		float x0DotD = x0.dot(d);
		float rootResult = x0DotD * x0DotD - 2 * ( x0.dot(x0) - r*r ); 
		
		if(rootResult < 0) 
			return null; 
		
		if(rootResult == 0)
			return new Hit(-x0DotD, this.getColor());  
		
		float sqrt = (float)Math.sqrt(rootResult);
		Hit a = new Hit( -x0DotD + sqrt, getColor() ); 
		Hit b = new Hit( -x0DotD - sqrt, getColor() );
		
		System.out.println("at: "+a.getT());
		System.out.println("bt: "+b.getT());
		
		return a.compareTo(b) < 0 ? a : b; 
	}
}
