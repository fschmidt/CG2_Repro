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
	private final Color kRefract;
	private final float phongExponent;

	public Material(Color kAmbient, Color kDiffuse, Color kSpecular, Color kRefract,
			float phongExponent) {
		this.kAmbient = kAmbient;
		this.kDiffuse = kDiffuse;
		this.kSpecular = kSpecular;
		this.kRefract = kRefract;
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

		float epsilon = 0.002f;

		Color result = kAmbient.modulate(scene.getAmbientLight());

		for (LightSource light : scene.getLights()) {

			Vector hitPoint = ray.getOrigin().add(
					ray.getGaze().mult(hit.getT()));
			
			Vector s = light.getOrigin().sub(hitPoint);
			Vector r = ((hit.getNormal().mult(2 * (hit.getNormal().dot(s)))).sub(s)).normalize();

			// Ray shadowRay = <generate shadow ray>;
			Vector directionShadowRay = light.getOrigin().sub(hitPoint);

			Ray shadowray = new Ray(directionShadowRay, hitPoint);
			Vector betweenLightSourceAndHitPoint = light.getOrigin().sub(
					ray.getOrigin().add(ray.getGaze().mult(hit.getT())));
			float tmax = betweenLightSourceAndHitPoint.length();

			if (scene.intersect(shadowray, epsilon, tmax) == null) {
				final Color red = new Color(1, 0, 0);
				float ns = hit.getNormal().dot(directionShadowRay.normalize());
//				if (ns > 0) {
					if (red.equals(getkDiffuse())) {
						System.out.println("ambient: " + result);
						System.out.println("diffuse : "
								+ kDiffuse.modulate(light.getColor().modulate(
										ns)));
						System.out.println("Final: "
								+ result.add(kDiffuse.modulate(light
										.getColor().modulate(ns))));
						System.out.println("LightColor: "+ light.getColor());

					}
					result.add(kDiffuse.modulate(light.getColor().modulate(hit.getNormal().dot(r))));
//				}
			}
			// if(null==scene.intersect(shadowray, originShadowRay.length(),
			// light.getOrigin().length()))
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
