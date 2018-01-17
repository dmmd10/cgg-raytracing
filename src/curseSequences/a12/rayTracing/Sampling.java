package curseSequences.a12.rayTracing;

import static cgtools.Vec3.*;

import java.time.temporal.ChronoUnit;

import cgtools.Vec3;
import curseSequences.a12.lights.WorldLighting;
import curseSequences.a12.sceneGraph.Scene;
import curseSequences.a12.sceneGraph.World;
import curseSequences.a12.sceneObjects.Shape;

public class Sampling implements Sampler {

	public final Camera camera;
	public final World world;
	public final WorldLighting worldLight;
	public int planeHitCounter = 0;
	public int hitCounter = 0;
	
	public Sampling(Camera camera, World world, WorldLighting worldLight) {
		this.camera = camera;
		this.world = world;
		this.worldLight = worldLight;
	}

	@Override
	public Vec3 pixelColor(double x, double y) {
		Ray ray = camera.generateRay(x, y);
		Vec3 color = calculateRadiance(world, ray, 5);
		if (x >= 1599 && y >= 899) {
			System.out.println("Scene Intersects Zeit kummuliert: " + world.scene.timer + " ms");
			System.out.println("Plane Hits mit SekundaerRay:  " + planeHitCounter);
			System.out.println("Hits mit SekundaerRay:  " + hitCounter);
		}
		return color;
	}
	
	public Vec3 calculateRadiance(Shape world, Ray ray, int depth) {
		if (depth == 0) {
			return black;
		}
		Hit hit = world.intersect(ray);
		if(depth < 6 && hit.objName.startsWith("Plane")) {
			planeHitCounter++;
		}
		if(depth < 6) {
			hitCounter++;
		}
		Vec3 illumination = worldLight.illumination(hit, ((World)world).sceneAndPlane);
    	Vec3 emittedRadiance = hit.material.emittedRadiance(ray, hit);
    	Ray scatteredRay = hit.material.scatteredRay(ray, hit);
    	if (scatteredRay != null) {
    		Vec3 albedo = hit.material.albedo(ray, hit);
    		Vec3 calcRad = calculateRadiance(world, scatteredRay, depth-1);
    		Vec3 color = add(emittedRadiance, multiply(albedo, add(calcRad, illumination)));
    		return color;
    	} else {
    		return emittedRadiance;
    	}
	}
	
	
}
