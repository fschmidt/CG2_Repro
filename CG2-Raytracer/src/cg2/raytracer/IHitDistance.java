package cg2.raytracer;

public interface IHitDistance extends IHit, Comparable<IHitDistance> {
	public abstract float getT();

	public abstract int compareTo(IHitDistance o);
}