package cg2.raytracer;

import cg2.vecmath.Color;

/**
 * Represents a Hit containing the color of the shape hit
 * 
 * @author Frank
 *
 */
public class HitColoredDistance extends HitDistance implements IHitColoredDistance {
	private final Color c;

	public HitColoredDistance(float t, Color c) {
		super(t); 
		this.c = c;
	}

	/* (non-Javadoc)
	 * @see cg2.raytracer.IHitColoredDistance#getColor()
	 */
	@Override
	public Color getColor() {
		return c;
	}

	@Override
	public String toString() {
		return "Hit [t=" + t + ", c=" + c + "]";
	} 
}
