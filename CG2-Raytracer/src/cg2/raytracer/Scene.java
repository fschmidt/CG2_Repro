package cg2.raytracer;

import java.util.List;

public class Scene {
	private List<IShape> objects;		
	
	public Scene(List<IShape> objects) {
		super();
		this.objects = objects;
	}

	/**
	 * @param ray
	 * @return null if no object was hit. 
	 */
	public Hit intersect(Ray ray) {
		Hit nearest = null;
		
		for (IShape shape : objects) {
			if(nearest == null)
				nearest = shape.getHit(ray); 
			else {
				Hit current = shape.getHit(ray); 
				if(current.compareTo(nearest) < 0) 
					nearest = current;
			}
		} 
		
		return nearest; 
	}
}
