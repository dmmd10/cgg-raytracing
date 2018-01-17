package curseSequences.a10.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cgtools.Mat4;
import cgtools.Vec3;
import curseSequences.a10.materials.ChessTexture;
import curseSequences.a10.materials.ColoredTexture;
import curseSequences.a10.materials.ConstantTexture;
import curseSequences.a10.materials.EmittingMaterial;
import curseSequences.a10.materials.GlassMaterial;
import curseSequences.a10.materials.MetalMaterial;
import curseSequences.a10.materials.PerfectDiffuseMaterial;
import curseSequences.a10.materials.PicTexture;
import curseSequences.a10.materials.TransformedTexture;
import curseSequences.a10.materials.RoughMetalMaterial;
import curseSequences.a10.rayTracing.BoundingBox;
import curseSequences.a10.rayTracing.Hit;
import curseSequences.a10.rayTracing.HitComparator;
import curseSequences.a10.rayTracing.Ray;
import curseSequences.a10.rayTracing.Transformation;
import curseSequences.a10.sceneObjects.AntiCone;
import curseSequences.a10.sceneObjects.Background;
import curseSequences.a10.sceneObjects.Cone;
import curseSequences.a10.sceneObjects.Cube;
import curseSequences.a10.sceneObjects.Cylinder;
import curseSequences.a10.sceneObjects.Plane;
import curseSequences.a10.sceneObjects.Rectangle;
import curseSequences.a10.sceneObjects.Shape;
import curseSequences.a10.sceneObjects.Sphere;
import curseSequences.a10.sceneObjects.Torus;

public class Scene extends Group {

	protected List<Group> flattenGraphList;
	protected Group bvhGroup;

	public Scene() {
		createFlattenGraph();
		bvhGroup = buildBVH(flattenGraphList, bounding());
		System.out.println("fertig");
	}

	@Override
	public void generateShapesList() {

		// // Testkonfiguration
		//// |+Y
		//// |____+X
		//// /
		//// /+z

		// hat was mit Rotation und Ebenen zu tun (vll Nullpunkt)
		// light_mint
		geoObjectList.add(new Rectangle(vec3(-30.0, 0.002, -40.0), 60, 80,
				new EmittingMaterial(new PicTexture("doc/rasen_linear.jpg"))));
		geoObjectList.add(new Plane(vec3(0.0, 0.001, 0.0),
				new EmittingMaterial(new PicTexture("doc/granite_linear.jpg")), 20, 20));
		 geoObjectList.add(new Plane(vec3(0.0, 0.0, 0.0), new
		 PerfectDiffuseMaterial(new ChessTexture(light_mint, marine, 0.003)),
		 1000, 1000));
		geoObjectList.add(new RoboterSphere(translate(vec3(0, 0, 0))));
		 geoObjectList.add(new Roboter(translate(vec3(25, 0, 0))));
		geoObjectList.add(new Background(
				new EmittingMaterial(new TransformedTexture(new PicTexture("doc/sky_linear.jpg"), bgTransform())))); 
		// scale(vec3(10, 10, 0))
		// positive x Werte --> nach rechts im Bild (0.1 = 1/10)
		// positive y Werte --> nach unten im Bild

		// geoObjectList.add(new CubeRotated(cubeTrans()));
		geoObjectList.add(new Cylinder(vec3(0, 0, 45.25), 7, 100,
				new EmittingMaterial(new ColoredTexture(new PicTexture("doc/treebark_linear.jpg"), yellow))));
		// 0, 0, 45.25 new RoughMetalMaterial(new ConstantTexture(yellow), 1)));
//		geoObjectList.add(new Cylinder(vec3(32, 0, -32), 7, 100,
//				new EmittingMaterial(new ColoredTexture(new PicTexture("doc/treebark_linear.jpg"), shiny_red))));
//		geoObjectList.add(new Cylinder(vec3(-32, 0, -32), 7, 100,
//				new EmittingMaterial(new ColoredTexture(new PicTexture("doc/treebark_linear.jpg"), violet))));
	}

	private Mat4 bgTransform() {
		Mat4 translation = translate(vec3(0.25, 0, 0));
		Mat4 scale = scale(vec3(-1, 1, 0));
		return scale.multiply(translation);
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
			group.bb = group.bounding(); // jede 1 ShapeGroup bekommt BB mit transformation
		}
	}

	public Group buildBVH(List<Group> flattenGraphList, BoundingBox bounds) {
		
		BoundingBox leftBb = bounds.splitLeft();
		BoundingBox rightBb = bounds.splitRight();
		List<Group> leftShapes = new ArrayList<Group>();
		List<Group> rightShapes = new ArrayList<Group>();
		Group containedShapes = new Group(bounds); //Group mit translate(0,0,0) Transformation

		for (Group shape : flattenGraphList) {
			if (leftBb.contains(shape.bb)) {
				leftShapes.add(shape);
			} else if (rightBb.contains(shape.bb)) { //
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
		Vec3 min = vec3(-110, -5, -110);
		Vec3 max = vec3(110);
		return new BoundingBox(min, max);
	};

	@Override
	public Hit intersect(Ray ray) {
		Ray transRay = transformation.toObject(ray);
		List<Hit> hitList = new ArrayList<Hit>();
		for (Shape shape : bvhGroup.geoObjectList) {
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
			return new Hit(hit.t, transformation.toWorld(hit.hitPoint), transformation.toWorldN(hit.normal), hit.uv,
					hit.material);
		}
	}

}
