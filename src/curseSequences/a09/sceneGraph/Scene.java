package curseSequences.a09.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cgtools.Mat4;
import cgtools.Vec3;
import curseSequences.a09.materials.EmittingMaterial;
import curseSequences.a09.materials.GlassMaterial;
import curseSequences.a09.materials.MetalMaterial;
import curseSequences.a09.materials.PerfectDiffuseMaterial;
import curseSequences.a09.materials.RoughMetalMaterial;
import curseSequences.a09.rayTracing.Hit;
import curseSequences.a09.rayTracing.HitComparator;
import curseSequences.a09.rayTracing.Ray;
import curseSequences.a09.rayTracing.Transformation;
import curseSequences.a09.sceneObjects.Background;
import curseSequences.a09.sceneObjects.Cube;
import curseSequences.a09.sceneObjects.Cylinder;
import curseSequences.a09.sceneObjects.Plane;
import curseSequences.a09.sceneObjects.Shape;
import curseSequences.a09.sceneObjects.Sphere;
import curseSequences.a09.sceneObjects.Torus;

public class Scene extends Group {
	
	protected List<Group> flattenGraphList;
	
	public Scene() {
		super(new Transformation(translate(vec3(0, 0, 0))));
		createFlattenGraph();
	}
	
	@Override
	public void generateShapesList() {
		
//		// Testkonfiguration
////	|+Y
////	|____+X
////   /
////  /+z
		
//		geoObjectList.add(new Plane(vec3(0.0, 0, 0.0), vec3(0, 1, 0), new PerfectDiffuseMaterial(light_mint)));
//		geoObjectList.add(new RoboterSphere(new Transformation(translate(vec3(0, 0, 0)))));
		geoObjectList.add(new Roboter(new Transformation(translate(vec3(25, 0, 0)))));
		geoObjectList.add(new Background(new EmittingMaterial(lightYellow)));
		
//		geoObjectList.add(new CubeRotated(cubeTrans()));
//		geoObjectList.add(new Cylinder(vec3(0, 0, 45.25), 7, 100, new RoughMetalMaterial(yellow, 1)));
//		geoObjectList.add(new Cylinder(vec3(32, 0, -32), 7, 100, new RoughMetalMaterial(shiny_red, 1)));
//		geoObjectList.add(new Cylinder(vec3(-32, 0, -32), 7, 100, new RoughMetalMaterial(violet, 1)));
	}
	
	public void createFlattenGraph() {
		flattenGraphList = flatten();
		for (Group group : flattenGraphList) {
			group.calcNewTransformation();
		}
	}
	
	public Transformation cubeTrans() {
		double edgeLengthCube = 20;
		Mat4 translation = translate(vec3(0, -length(vec3(edgeLengthCube))/5, 0));
		Mat4 rotationX = rotate(vec3(1, 0, 0), -35.5);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), 45);
		Mat4 transformation = translation.multiply(rotationX.multiply(rotationZ));
		return new Transformation(transformation);
	} 
	
	// ACHTUNG !!!!!!!!!!!!!!!!!!!!!!!!!!!!! mit flattenGraphList
	@Override
	public Hit intersect(Ray ray) {
		Ray transRay = transformation.toObject(ray);
		List<Hit> hitList = new ArrayList<Hit>();
//		if (intersectTestShape != null && intersectTestShape.intersect(transRay) == null) {
//			return null;
//		};
		for(Shape shape: flattenGraphList) {
			Hit hit = shape.intersect(transRay);
			if (hit != null) {
				hitList.add(hit);
			}
		}
		if (hitList.isEmpty()) {
			return null;
		} else {
			hitList.sort(hitComparator);
			Hit hit = hitList.get(0);
			return new Hit(hit.t, 
					transformation.toWorld(hit.hitPoint),
					transformation.toWorldN(hit.normal),
					hit.material);
		}
	}
}
