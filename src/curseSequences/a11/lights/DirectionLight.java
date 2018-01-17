package curseSequences.a11.lights;

import static cgtools.Vec3.*;

import cgtools.Vec3;
import curseSequences.a11.rayTracing.Hit;
import curseSequences.a11.rayTracing.Ray;
import curseSequences.a11.sceneObjects.Shape;

public class DirectionLight implements Light {

	public final Vec3 direction;
	public final Vec3 illumination;

	public DirectionLight(Vec3 direction, Vec3 illumination) {
		this.direction = direction;
		this.illumination = illumination;
	}

	@Override
	public Vec3 sample(Hit hit, Shape scene) {
		if(hitpointNotShadowed(hit, scene)){
			return getIllumination(hit);
		}
		return black;
	}
	
	protected boolean hitpointNotShadowed(Hit hit, Shape scene) {
		Vec3 hp = hit.hitPoint;
		Ray shadowRay = new Ray (9.0E9, hp, normalize(multiply(-1, direction)));
		if (scene.intersect(shadowRay) != null) {
			return false;
		} else {
			return true;
		}
	}
	
	protected Vec3 getIllumination(Hit hit) {
		double cosinus = dotProduct(normalize(multiply(-1, direction)), hit.normal);
		return multiply(cosinus, illumination);
	}
}
