package curseSequences.a08.rayTracing;

import static cgtools.Vec3.*;

import cgtools.Vec3;
import curseSequences.a08.sceneGraph.Scene;
import curseSequences.a08.sceneObjects.Shape;

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
//		System.out.printf("X: %6.1f   Y: %6.1f  -->  ",x,y);
		return calculateRadiance(scene, ray, 5);
	}
	
	public Vec3 calculateRadiance(Shape scene, Ray ray, int depth) {
		if (depth == 0) {
			return black;
		}
		Hit hit = scene.intersect(ray);
    	Vec3 emittedRadiance = hit.material.emittedRadiance(ray, hit);
    	Ray scatteredRay = hit.material.scatteredRay(ray, hit);
//    	System.out.println(depth + " Von Plane reflektierter " + scatteredRay);
    	if (scatteredRay != null) {
    		Vec3 albedo = hit.material.albedo(ray, hit);
//    		System.out.println(depth + " Von Plane albedo " + albedo);
    		Vec3 calcRad = calculateRadiance(scene, scatteredRay, depth-1);
//    		System.out.println(depth-1 + " calcRad " + calcRad);
    		return add(emittedRadiance, multiply(albedo, calcRad));
    	} else {
    		return emittedRadiance;
    	}
	}
	
	
}
