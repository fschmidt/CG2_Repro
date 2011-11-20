package cg2.raytracer;

import cg2.vecmath.Color;

public class HitDistance implements IHitDistance {
	public static final IHitDistance IS_HIT = new HitColoredDistance(0, new Color(0, 0, 0)); 
	
	protected final float t;

	public HitDistance(float t) {
		super();
		this.t = t; 
	}

	@Override
	public float getT() {
		return t;
	}

	@Override
	public int compareTo(IHitDistance o) {
		if (o == null)
			throw new IllegalArgumentException();
	
		float dif = this.t - o.getT();
	
		if (dif < 0)
			return -1;
	
		if (dif > 0)
			return 1;
	
		return 0;
	}

}