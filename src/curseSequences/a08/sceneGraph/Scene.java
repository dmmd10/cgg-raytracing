package curseSequences.a08.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import java.util.ArrayList;
import java.util.List;

import cgtools.Mat4;
import cgtools.Vec3;
import curseSequences.a08.materials.EmittingMaterial;
import curseSequences.a08.materials.GlassMaterial;
import curseSequences.a08.materials.MetalMaterial;
import curseSequences.a08.materials.PerfectDiffuseMaterial;
import curseSequences.a08.materials.RoughMetalMaterial;
import curseSequences.a08.rayTracing.Hit;
import curseSequences.a08.rayTracing.HitComparator;
import curseSequences.a08.rayTracing.Ray;
import curseSequences.a08.rayTracing.Transformation;
import curseSequences.a08.sceneObjects.Background;
import curseSequences.a08.sceneObjects.Cube;
import curseSequences.a08.sceneObjects.Cylinder;
import curseSequences.a08.sceneObjects.Plane;
import curseSequences.a08.sceneObjects.Shape;
import curseSequences.a08.sceneObjects.Sphere;

public class Scene implements Shape {

	protected List<Shape> sceneElementList;
	protected List<Hit> hitList;
	protected final HitComparator hitComparator = new HitComparator();

	public Scene() {
		
//		// Testkonfiguration
////	|+Y
////	|____+X
////   /
////  /+z
		
		sceneElementList = new ArrayList<Shape>();
		sceneElementList.add(new Plane(vec3(0.0, 0, 0.0), vec3(0, 1, 0), new PerfectDiffuseMaterial(light_mint)));
		sceneElementList.add(new RoboterSphere(new Transformation(translate(vec3(0, 0, 0)))));
		sceneElementList.add(new Background(new EmittingMaterial(lightYellow)));

		double edgeLengthCube = 20;
		Mat4 translation = translate(vec3(0, -length(vec3(edgeLengthCube))/5, 0));
		Mat4 rotationX = rotate(vec3(1, 0, 0), -35.5);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), 45);
		Mat4 transformation = translation.multiply(rotationX.multiply(rotationZ));
		sceneElementList.add(new CubeRotated(new Transformation(transformation)));
		sceneElementList.add(new Cylinder(vec3(0, 0, 45.25), 7, 100, new RoughMetalMaterial(yellow, 1)));
		sceneElementList.add(new Cylinder(vec3(32, 0, -32), 7, 100, new RoughMetalMaterial(shiny_red, 1)));
		sceneElementList.add(new Cylinder(vec3(-32, 0, -32), 7, 100, new RoughMetalMaterial(violet, 1)));
	}

	@Override
	public Hit intersect(Ray ray) {
		hitList = new ArrayList<Hit>();
		for (Shape shape : sceneElementList) {
			Hit hit = shape.intersect(ray);
			if (hit != null) {
				hitList.add(hit);
			}
		}
		hitList.sort(hitComparator);
		return hitList.get(0);
	}
}
