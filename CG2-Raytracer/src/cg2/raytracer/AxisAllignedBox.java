package cg2.raytracer;

import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class AxisAllignedBox implements IShapeColored {

	private final Vector p;
	private final Vector q;
	private final Color c;

	public Vector getP() {
		return p;
	}

	public Vector getQ() {
		return q;
	}

	public Color getColor() {
		return c;
	}

	public AxisAllignedBox(Vector p, Vector q, Color c) {
		super();
		this.p = p;
		this.q = q;
		this.c = c;

		if (p.x > q.x || p.y > q.y || p.z > q.z) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Hit getHit(Ray ray) {
		Vector x0 = ray.getOrigin();

		Vector x0subp = x0.sub(p);
		Vector x0subq = x0.sub(q);
		
		Plane p1 = new Plane(p, Vector.Z, c); 
		Plane p2 = new Plane(p, Vector.X.mult(-1), c); 
		Plane p3 = new Plane(q, Vector.Y, c); 
		Plane p4 = new Plane(q, Vector.X, c); 
		Plane p5 = new Plane(q, Vector.Z.mult(-1), c); 
		Plane p6 = new Plane(p, Vector.Y.mult(-1), c); 
		
		float n1 = Vector.Z.dot(x0subp);
		float n2 = Vector.X.mult(-1).dot(x0subp);
		float n3 = Vector.Y.dot(x0subq);
		float n4 = Vector.X.dot(x0subq);
		float n5 = Vector.Z.mult(-1).dot(x0subq);
		float n6 = Vector.Y.mult(-1).dot(x0subp);
		
		Plane[] planes = new Plane[] {p1, p2, p3, p4, p5, p6};
		float[] ns = new float[] {n1,n2,n3,n4,n5,n6}; 
		Plane[] planesISee = new Plane[3]; 
		int i = 0; 
		
		for(int j = 0; j != 6; j++) {
			if(ns[j] > 0)
				planesISee[i++] = planes[j]; 
		}
		
		float t1 = planesISee[0] != null ? (planesISee[0].getHit(ray) != null ? planesISee[0].getHit(ray).getT() : 0) : 0; 
		float t2 = planesISee[1] != null ? (planesISee[1].getHit(ray) != null ? planesISee[1].getHit(ray).getT() : 0) : 0; 
		float t3 = planesISee[2] != null ? (planesISee[2].getHit(ray) != null ? planesISee[2].getHit(ray).getT() : 0) : 0; 
			
		float t = Math.max(t1, Math.max(t2, t3));  
		
		Vector td = ray.getGaze().mult(t).add(ray.getOrigin()); 
		
		if(td.x >= p.x && td.y >= p.y && td.z >= p.z && td.x <= q.x && td.y <= q.y && td.z <= q.z) {
			System.out.println(t);
			return new Hit(t, c); 
		}
		else 
			return null; 
	}
}
