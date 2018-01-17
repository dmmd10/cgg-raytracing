package curseSequences.a12.lights;

import static cgtools.Vec3.*;

import cgtools.Vec3;
import curseSequences.a12.rayTracing.Hit;
import curseSequences.a12.rayTracing.Ray;
import curseSequences.a12.sceneObjects.Shape;

public class PointLight implements Light {
	
	public final Vec3 point;
	public final Vec3 illumination;

	public PointLight(Vec3 point, Vec3 illumination) {
		this.point = point;
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
		Ray shadowRay = new Ray (1, hp, subtract(point, hp));
		if (scene.intersect(shadowRay) != null) {
			return false;
		} else {
			return true;
		}
	}
	
	protected Vec3 getIllumination(Hit hit) {
		Vec3 hp = hit.hitPoint;
		double lastTerm = dotProduct(normalize(subtract(point, hp)), hit.normal);
		double lengthPow2 = Math.pow(length(subtract(point, hp)), 2);
		Vec3 firstTerm = divide(illumination, lengthPow2);
		return multiply(lastTerm, firstTerm);
	}

}
