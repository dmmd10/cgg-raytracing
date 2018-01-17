package curseSequences.a10.rayTracing;

import static cgtools.Vec3.*;

import cgtools.Vec3;
import curseSequences.a10.sceneGraph.Scene;
import curseSequences.a10.sceneObjects.Shape;

public class Sampling implements Sampler {

	public final Camera camera;
	public final Scene scene;
	
	public Sampling(Camera camera, Scene scene) {
		this.camera = camera;
		this.scene = scene;
	}

	@Override
	public Vec3 pixelColor(double x, double y) {
		Ray ray = camera.generateRay(x, y);
		return calculateRadiance(scene, ray, 5);
	}
	
	public Vec3 calculateRadiance(Shape scene, Ray ray, int depth) {
		if (depth == 0) {
			return black;
		}
		Hit hit = scene.intersect(ray);
    	Vec3 emittedRadiance = hit.material.emittedRadiance(ray, hit);
    	Ray scatteredRay = hit.material.scatteredRay(ray, hit);
    	if (scatteredRay != null) {
    		Vec3 albedo = hit.material.albedo(ray, hit);
    		Vec3 calcRad = calculateRadiance(scene, scatteredRay, depth-1);
    		return add(emittedRadiance, multiply(albedo, calcRad));
    	} else {
    		return emittedRadiance;
    	}
	}
	
	
}
