package curseSequences.a07.sceneObjects;

import static cgtools.Vec3.*;

import java.util.ArrayList;
import java.util.List;

import cgtools.Vec3;
import curseSequences.a07.materials.EmittingMaterial;
import curseSequences.a07.materials.GlassMaterial;
import curseSequences.a07.materials.MetalMaterial;
import curseSequences.a07.materials.PerfectDiffuseMaterial;
import curseSequences.a07.materials.RoughMetalMaterial;
import curseSequences.a07.rayTracing.Hit;
import curseSequences.a07.rayTracing.HitComparator;
import curseSequences.a07.rayTracing.Ray;

public class Scene implements Shape {

	protected List<Shape> sceneElementList;
	protected List<Hit> hitList;
	protected final HitComparator hitComparator = new HitComparator();
	protected final Group group;

	public Scene() {
		group = createGroup();
		createSceneElements(group);
	}

	protected void createSceneElements(Group group) {
		sceneElementList = new ArrayList<Shape>();
		sceneElementList.add(group);
		sceneElementList.add(new Background(new EmittingMaterial(lightYellow)));
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

	protected static Group createGroup() {
		List<Shape> geoObjectList = new ArrayList<Shape>();

		// Testkonfiguration
//		|+Y
//		|____+X
//	   /
//	  /+z
		
		int numbersOfMan = 8;
		for (int j = 0; j < 2; j++) {

			for (int i = 0; i < numbersOfMan; i++) {

				double angleOffset = 0 * Math.PI / 6;
				double angle = (2 * Math.PI) / numbersOfMan;
				double radius = 10;
				if (j == 1) {
					radius = 20;
					angleOffset = Math.PI / 8;
				}
				Vec3 circlePoint = calcCirclePoint(angle, radius, i, angleOffset);
				Vec3 normal = normalize(circlePoint);
				Vec3 distanceVec = vec3(-normal.z, 0, normal.x);
				Vec3 leftPoint = null;
				Vec3 rightPoint = null;

				// Füße
				double legDistance = 1.2;
				leftPoint = add(circlePoint, multiply(legDistance, distanceVec));
				rightPoint = add(circlePoint, multiply(legDistance, multiply(-1, distanceVec)));

				geoObjectList.add(new Cone(addHeight(leftPoint, 1), 1, new PerfectDiffuseMaterial(lightYellow)));
				geoObjectList.add(new Cone(addHeight(rightPoint, 1), 1, new PerfectDiffuseMaterial(lightYellow)));

				// Beine
				geoObjectList
						.add(new Cylinder(addHeight(leftPoint, 0.5), 0.5, 3.1, new RoughMetalMaterial(orange, 0.1)));
				geoObjectList
						.add(new Cylinder(addHeight(rightPoint, 0.5), 0.5, 3.1, new RoughMetalMaterial(orange, 0.1)));

				// Bauch
				geoObjectList.add(new Cylinder(addHeight(circlePoint, 3.7), 1.7, 6.0, new RoughMetalMaterial(light_blue, 0.1)));

				// Arme
				double armDistance = 2.5;
				leftPoint = add(circlePoint, multiply(armDistance, distanceVec));
				rightPoint = add(circlePoint, multiply(armDistance, multiply(-1, distanceVec)));
				geoObjectList.add(new Cylinder(addHeight(leftPoint, 4.7), 0.4, 4.0, new MetalMaterial(marine)));
				geoObjectList.add(new Cylinder(addHeight(rightPoint, 4.7), 0.4, 4.0, new MetalMaterial(marine)));

				// Schulter
				armDistance = 2.3;
				leftPoint = add(circlePoint, multiply(armDistance, distanceVec));
				rightPoint = add(circlePoint, multiply(armDistance, multiply(-1, distanceVec)));
				geoObjectList.add(new Sphere(addHeight(leftPoint, 8.5), 0.7, new RoughMetalMaterial(white, 0.1)));
				geoObjectList.add(new Sphere(addHeight(rightPoint, 8.5), 0.7, new RoughMetalMaterial(white, 0.1)));

				// Hals
				geoObjectList.add(new Cone(addHeight(circlePoint, 10.7), 1, new PerfectDiffuseMaterial(black)));

				// Kopf
				geoObjectList.add(new Sphere(addHeight(circlePoint, 11.5), 1.6, new GlassMaterial(aqua, 1.5)));

				// Augen
				double eyeDistance = 0.7;
				circlePoint = calcCirclePoint(angle, radius - 1.3, i, angleOffset);
				leftPoint = add(circlePoint, multiply(eyeDistance, distanceVec));
				rightPoint = add(circlePoint, multiply(eyeDistance, multiply(-1, distanceVec)));
				geoObjectList.add(new Sphere(addHeight(leftPoint, 11.4), 0.3, new EmittingMaterial(shiny_red)));
				geoObjectList.add(new Sphere(addHeight(rightPoint, 11.4), 0.3, new EmittingMaterial(shiny_red)));

			}
		}

		geoObjectList.add(new Plane(vec3(0.0, 0, 0.0), vec3(0, 1, 0), new RoughMetalMaterial(mint, 0.3)));
		geoObjectList.add(new Sphere(vec3(0, 6.5, 0), 5, new RoughMetalMaterial(light_gray, 0.05)));

		// geoObjectList.add(new Disk(vec3(0.0, 2, 0.0), vec3(0, 1, 0), 2, new RoughMetalMaterial(white, 0.1)));

		// geoObjectList.add(new AntiCone(vec3(0, 2, 0), 1.0, new PerfectDiffuseMaterial(violet)));
		// geoObjectList.add(new Cylinder(vec3(0, 6, 0), 0.5, 1.0, new PerfectDiffuseMaterial(orange)));
		// geoObjectList.add(new Disk(vec3(0, 7, 0), vec3(0, 1, 0), 0.5, new PerfectDiffuseMaterial(blue)));
		// geoObjectList.add(new Disk(vec3(0, 6, 0), vec3(0, 1, 0), 0.5, new PerfectDiffuseMaterial(red)));
		// geoObjectList.add(new Cylinder(vec3(-0.5, 0, -2.5), 0.5, 2.0, new GlassMaterial(white, 1.5)));
		// geoObjectList.add(new Cone(vec3(0, 7, 0), 1.0, new PerfectDiffuseMaterial(orange)));
		return new Group(geoObjectList);

	}

	private static Vec3 addHeight(Vec3 vector, double h) {
		return vec3(vector.x, vector.y + h, vector.z);
	}

	private static Vec3 calcCirclePoint(double angle, double radius, int i, double x0) {
		double x = radius * Math.cos(angle * i + x0);
		double z = radius * Math.sin(angle * i + x0);
		return vec3(x, 0, z);
	}

}
