package curseSequences.a11.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cgtools.Mat4;
import cgtools.Random;
import cgtools.Vec3;
import curseSequences.a11.materials.ChessTexture;
import curseSequences.a11.materials.ColoredTexture;
import curseSequences.a11.materials.ConstantTexture;
import curseSequences.a11.materials.EmittingMaterial;
import curseSequences.a11.materials.GlassMaterial;
import curseSequences.a11.materials.MetalMaterial;
import curseSequences.a11.materials.PerfectDiffuseMaterial;
import curseSequences.a11.materials.PicTexture;
import curseSequences.a11.materials.TransformedTexture;
import curseSequences.a11.materials.RoughMetalMaterial;
import curseSequences.a11.rayTracing.BoundingBox;
import curseSequences.a11.rayTracing.Hit;
import curseSequences.a11.rayTracing.HitComparator;
import curseSequences.a11.rayTracing.Ray;
import curseSequences.a11.rayTracing.Transformation;
import curseSequences.a11.sceneObjects.AntiCone;
import curseSequences.a11.sceneObjects.Background;
import curseSequences.a11.sceneObjects.Cone;
import curseSequences.a11.sceneObjects.Cube;
import curseSequences.a11.sceneObjects.Cylinder;
import curseSequences.a11.sceneObjects.Plane;
import curseSequences.a11.sceneObjects.Rectangle;
import curseSequences.a11.sceneObjects.Shape;
import curseSequences.a11.sceneObjects.Sphere;
import curseSequences.a11.sceneObjects.Torus;

public class Scene extends Group {

	protected List<Group> flattenGraphList;
	protected Group bvhGroup;
	public long timer = 0;

	public Scene() {
		Instant start, end;
		start = Instant.now();
		createFlattenGraph();
		bvhGroup = buildBVH(flattenGraphList, bounding());
		end = Instant.now();
		System.out.println("Flatten mit BVH: " + ChronoUnit.MILLIS.between(start, end) + " ms");
	}

	@Override
	public void generateShapesList() {

		// // Testkonfiguration
		//// |+Y
		//// |____+X
		//// /
		//// /+z


//		for (int i = -200; i < 200; i=i+32) {
//			for (int j = -200; j < 200; j=j+32) {
//				geoObjectList.add(new RoboterSphere(translate(vec3(i, 0, j))));
//			}
//		} 
		
		int[] xOffArr = {0, -500, -750, -1000, -1500, -3000, -4000, -4500, -5000, -5000, -6000, -6000, -7500, -6000, -7500, -7500};
		int[] zOffArr = {0, -750, -300, 500, -1000, -1500, 200, 1500, -4000, 2000, -2000, 1000, 2000, 3500, 5000, -4000};
		
		for (int i = 0; i < zOffArr.length; i++) {
			int xOff = xOffArr[i];
			int zOff = zOffArr[i];
			geoObjectList.add(new RoboterSphere(translate(vec3(0 + xOff, 0, 0 + zOff))));
			geoObjectList.add(new RoboterDome(translate(vec3(43 + xOff, 0, 43 + zOff))));
			geoObjectList.add(new RoboterDome(translate(vec3(43 + xOff, 0, -43 + zOff))));
			geoObjectList.add(new RoboterDome(translate(vec3(-43 + xOff, 0, 43 + zOff))));
			geoObjectList.add(new RoboterDome(translate(vec3(-43 + xOff, 0, -43 + zOff))));
		}
		
//		
		
		
//		geoObjectList.add(new RoboterSphere(translate(vec3(32, 0, 0))));
//		geoObjectList.add(new RoboterSphere(translate(vec3(0, 0, -32))));
//		geoObjectList.add(new RoboterSphere(translate(vec3(0, 0, 32))));
//		 geoObjectList.add(new Roboter(translate(vec3(25, 25, 0))));
//		 geoObjectList.add(new Group(translate(vec3(15, 15, 1)), 
//				 new Sphere(vec3(0, 19, -1), 10, new PerfectDiffuseMaterial(new ConstantTexture(red)))));
		
		// scale(vec3(10, 10, 0))
		// positive x Werte --> nach rechts im Bild (0.1 = 1/10)
		// positive y Werte --> nach unten im Bild

//		 geoObjectList.add(new CubeRotated(cubeTrans()));
		
//		geoObjectList.add(new Cylinder(vec3(0, 0, 45.25), 7, 100,
//		new PerfectDiffuseMaterial(new ColoredTexture(new PicTexture("doc/treebark_linear.jpg"), yellow))));
//		geoObjectList.add(new Cylinder(vec3(32, 0, -32), 7, 100,
//		new PerfectDiffuseMaterial(new ColoredTexture(new PicTexture("doc/treebark_linear.jpg"), shiny_red))));
//		geoObjectList.add(new Cylinder(vec3(-32, 0, -32), 7, 100,
//		new PerfectDiffuseMaterial(new ColoredTexture(new PicTexture("doc/treebark_linear.jpg"), violet))));
		
		
//		geoObjectList.add(new Sphere(vec3(0, 10, 0), 3, new PerfectDiffuseMaterial(new ConstantTexture(green))));
		
		// Test für Berechnungszeit mit mehr Objekten
//		double x = 0;
//		double y = 0;
//		double z = 0;
//		int numbersOfSpheres = 1000;
//		double radiusSize = 0.2;
//		for (int i = 0; i < numbersOfSpheres; i++) {
//			x = Random.random() * 10;
//			y = Random.random() * 10;
//			z = Random.random() * 10;
//			double red = Random.random();
//			double green  = Random.random();
//			double blue = Random.random();			
//			geoObjectList.add(new Sphere(vec3(x, y, z), radiusSize, new PerfectDiffuseMaterial(new ConstantTexture(vec3(red, green, blue)))));
//		}
		
	}

	

	public Mat4 cubeTrans() {
		double edgeLengthCube = 20;
		Mat4 translation = translate(vec3(0, -length(vec3(edgeLengthCube)) / 5, 0));
		Mat4 rotationX = rotate(vec3(1, 0, 0), -35.5);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), 45);
		return translation.multiply(rotationX.multiply(rotationZ));
	}

	public void createFlattenGraph() {
		flattenGraphList = flatten();
		for (Group group : flattenGraphList) {
			group.calcNewTransformation();
		}
	}

	public Group buildBVH(List<Group> flattenGraphList, BoundingBox bounds) {
		
		BoundingBox leftBb = bounds.splitLeft();
		BoundingBox rightBb = bounds.splitRight();
		List<Group> leftShapes = new ArrayList<Group>();
		List<Group> rightShapes = new ArrayList<Group>();
		Group containedShapes = new Group(bounds);

		for (Group shape : flattenGraphList) {
			if (leftBb.contains(shape.transformedBB)) {
				leftShapes.add(shape);
			} else if (rightBb.contains(shape.transformedBB)) {
				rightShapes.add(shape);
			} else {
				containedShapes.add(shape);
			}
		}
		if (!leftShapes.isEmpty()) {
			containedShapes.add(buildBVH(leftShapes, leftBb));
		}
		if (!rightShapes.isEmpty()) {
			containedShapes.add(buildBVH(rightShapes, rightBb));
		}
		return containedShapes;
	}
	
	@Override
	public BoundingBox bounding() {
		bb = new BoundingBox();
		for (Shape shape: flattenGraphList) {
			if (shape instanceof Group) {
				Group group = (Group)shape;
				BoundingBox groupBB = group.bounding().transform(group.transformation.mObjWorld);
				group.transformedBB = groupBB;
				bb = bb.extend(groupBB);
			}
		}
		return bb;
	}

	@Override
	public Hit intersect(Ray ray) {
		Instant start, end;
		start = Instant.now();		
		Ray transRay = transformation.toObject(ray);
		List<Hit> hitList = new ArrayList<Hit>();
		for (Shape shape : bvhGroup.geoObjectList) {
			Hit hit = shape.intersect(transRay);
			if (hit != null) {
				hitList.add(hit);
			}
		}
		if (hitList.isEmpty()) {
			end = Instant.now();
			timer += ChronoUnit.MILLIS.between(start, end);
			return null;
		} else {
			hitList.sort(hitComparator);
			Hit hit = hitList.get(0);
			end = Instant.now();
			timer += ChronoUnit.MILLIS.between(start, end);
			return new Hit(hit.t, transformation.toWorld(hit.hitPoint), transformation.toWorldN(hit.normal), hit.uv,
					hit.material, hit.objName);
		}
	}
}
