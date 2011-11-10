package cg2.raytracer.model;


import cg2.raytracer.HitColoredDistance;
import cg2.raytracer.IHitColoredDistance;
import cg2.raytracer.IHitDistance;
import cg2.raytracer.Ray;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class AxisAllignedBoxColored extends AxisAllignedBox implements IShapeColored {
	protected final Color c;

	public Color getColor() {
		return c;
	}

	public AxisAllignedBoxColored(Vector p, Vector q, Color c) {
		super(p, q);  
		this.c = c;

		if (p.x > q.x || p.y > q.y || p.z > q.z) {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public IHitColoredDistance getHit(Ray ray) {
		IHitDistance hit = super.getHit(ray);
		
		if(hit == null) 
			return null; 
		
		return new HitColoredDistance(hit.getT(), c); 
	}
}
