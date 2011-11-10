package cg2.raytracer.model;

import cg2.raytracer.IHitColoredDistance;
import cg2.raytracer.Ray;
import cg2.vecmath.Color;

public interface IShapeColored extends IShape {
	Color getColor(); 
	@Override
	IHitColoredDistance getHit(Ray ray); 
}
