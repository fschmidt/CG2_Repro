package cg2.raytracer.model;

import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.raytracer.Scene;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class Material {
	private final Color kAmbient;
	private final Color kDiffuse;
	private final Color kSpecular;
	private final float phongExponent;

	public Material(Color kAmbient, Color kDiffuse, Color kSpecular, float phongExponent) {
		this.kAmbient = kAmbient;
		this.kDiffuse = kDiffuse;
		this.kSpecular = kSpecular;
		this.phongExponent = phongExponent;
	}

	public Color getkAmbient() {
		return kAmbient;
	}

	public Color getkDiffuse() {
		return kDiffuse;
	}

	public Color getkSpecular() {
		return kSpecular;
	}

	public float getPhongExponent() {
		return phongExponent;
	}

	public Color shade(Ray ray, Hit hit, Scene scene, int depth) {

		Color result = kAmbient.modulate(scene.getAmbientLight());
		
		// if( viewer not in the back of the surface )
		
		// for each light in scene.getLightSources() do
		for(LightSource light : scene.getLights()) {
			// if( light not in the back of the surface )
			
			// Ray shadowRay = <generate shadow ray>;
			Vector originShadowRay = ray.getOrigin().add(ray.getGaze().mult(hit.getT())); 
			Vector directionShadowRay = light.getOrigin().sub(originShadowRay);
			Ray shadowray = new Ray(directionShadowRay, originShadowRay);
			// if( <intersect scene with shadowRay> == null )
			//if(null==scene.intersect(shadowray, originShadowRay.length(), light.getOrigin().length()))
			// result += kDiffuse * dot(n,s) * <light color> ;
			
		}
		// if( angle to ight source not extremely small )
		// result += kSpec * dot(v,r)^a * <light color> ;
		// if( depth > 0 && viewing angle not extremely small )
		// float R = <calculate reflectance>;
		// if( R > epsilon )
		// Hit reflHit = <intersect scene with reflection ray>;
		// Color reflectedColor = reflHit.material().shade(..., depth-1 );
		// result += R * kMirror * reflectedColor;
		// if( (1-R) > epsilon )
		// Hit refrHit = <intersect scene with refraction ray>;
		// Color refractedColor = refrHit.material().shade(..., depth-1 );
		// result += (1-R) * kMirror * refractedColor ;
		return result;
	}
}
