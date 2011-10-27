package cg2.raytracer;

import cg2.vecmath.Color;

public class Hit implements Comparable<Hit> {
	private final float t; 
	private final Color c;
	
	public Hit(float t, Color c) {
		super();
		this.t = t;
		this.c = c;
	}
	
	public float getT() {
		return t;
	}
	
	public Color getColor() {
		return c;
	}

	@Override
	public int compareTo(Hit o) {
		if(o == null) 
			throw new IllegalArgumentException(); 
		
		 float dif = this.t - o.t; 
		 
		 if(dif < 0)
			 return -1;
		 
		 if(dif > 0)
			 return 1; 
		 
		 return 0; 
	}
}
