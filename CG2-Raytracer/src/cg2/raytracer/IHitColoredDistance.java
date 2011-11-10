package cg2.raytracer;

import cg2.vecmath.Color;

public interface IHitColoredDistance extends IHitDistance {

	public abstract Color getColor();

}