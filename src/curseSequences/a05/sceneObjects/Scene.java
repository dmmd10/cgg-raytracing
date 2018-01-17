package curseSequences.a05.sceneObjects;

import static cgtools.Vec3.*;

import java.util.ArrayList;
import java.util.List;

import curseSequences.a05.rayTracing.Hit;
import curseSequences.a05.rayTracing.HitComparator;
import curseSequences.a05.rayTracing.Ray;
import curseSequences.a05.reflection.BackgroundMaterial;
import curseSequences.a05.reflection.PerfectDiffuseMaterial;

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
		sceneElementList.add(new Background(new BackgroundMaterial(null)));
	}

	@Override
	public Hit intersect(Ray ray) {
		hitList = new ArrayList<Hit>();
		for(Shape shape: sceneElementList) {
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
		
//		 geoObjectList.add(new Plane(vec3(0.0, -0.5, 0.0), vec3(0, 1, 0), new PerfectDiffuseMaterial(gray)));
//		 geoObjectList.add(new Sphere(vec3(0.0, 0.0, -2.5), 0.5, new PerfectDiffuseMaterial(green)));


		// Scene
		
		geoObjectList.add(new Plane(vec3(0.0, -0.5, 0.0), vec3(0, 1, 0), new PerfectDiffuseMaterial(gray)));
		
		for (int i = 0; i < 15; i++) {
			geoObjectList.add(new Sphere(vec3(0.5*i*Math.sin(Math.PI / 6 * i) + 0.1, -0.3, (0.0 - Math.exp(i * 0.5))), (0.1 + i * 0.05), 
					new PerfectDiffuseMaterial(orange)));
		}
		for (int i = 0; i < 15; i++) {
			geoObjectList.add(new Sphere(vec3(-0.5*i*Math.sin(Math.PI / 6 * i) - 0.1, -0.3, (0.0 - Math.exp(i * 0.5))), (0.1 + i * 0.05), 
					new PerfectDiffuseMaterial(mint)));
		}
		for (int i = 0; i < 15; i++) {
			geoObjectList.add(new Sphere(vec3(-Math.exp(i*0.4)*Math.sin(Math.PI / 6 * i)+0.7, -0.3, (0.0 - Math.exp(i * 0.5))), (0.1 + i * 0.05), 
					new PerfectDiffuseMaterial(red)));
		}
		for (int i = 0; i < 15; i++) {
			geoObjectList.add(new Sphere(vec3(Math.exp(i*0.4)*Math.sin(Math.PI / 6 * i)-0.7, -0.3, (0.0 - Math.exp(i * 0.5))), (0.1 + i * 0.05), 
					new PerfectDiffuseMaterial(marine)));
		}
		for (int i = 0; i < 15; i++) {
			geoObjectList.add(new Sphere(vec3(0.5*i*Math.sin(Math.PI / 6 * i) + 0.1, 0.3, (0.0 - Math.exp(i * 0.5))), (0.1 + i * 0.05), 
					new PerfectDiffuseMaterial(aqua)));
		}
		for (int i = 0; i < 15; i++) {
			geoObjectList.add(new Sphere(vec3(-0.5*i*Math.sin(Math.PI / 6 * i) - 0.1, 0.3, (0.0 - Math.exp(i * 0.5))), (0.1 + i * 0.05), 
					new PerfectDiffuseMaterial(lightYellow)));
		}
		for (int i = 0; i < 15; i++) {
			geoObjectList.add(new Sphere(vec3(-Math.exp(i*0.4)*Math.sin(Math.PI / 6 * i)+0.7, 0.3, (0.0 - Math.exp(i * 0.5))), (0.1 + i * 0.05), 
					new PerfectDiffuseMaterial(green)));
		}
		for (int i = 0; i < 15; i++) {
			geoObjectList.add(new Sphere(vec3(Math.exp(i*0.4)*Math.sin(Math.PI / 6 * i)-0.7, 0.3, (0.0 - Math.exp(i * 0.5))), (0.1 + i * 0.05), 
					new PerfectDiffuseMaterial(violet)));
		}

		// a04 Scene
		
//				geoObjectList.add(new Plane(vec3(0.0, -0.5, 0.0), vec3(0, 1, 0), new PerfectDiffuseMaterial(gray)));
//				geoObjectList.add(new Plane(vec3(0.0, 1.5, 0.0), vec3(0, -1, -0.3), new PerfectDiffuseMaterial(dark_gray)));
//				geoObjectList.add(new Plane(vec3(-2.5, 0, 0), vec3(-1, 0, 0), new PerfectDiffuseMaterial(blue)));
//
//				for (int i = 0; i < 10; i++) {
//					geoObjectList.add(new Sphere(vec3(-2.5, -0.5, (-2.5 - i * 2.0)), 0.6, new PerfectDiffuseMaterial(red)));
//					geoObjectList.add(new Sphere(vec3(-2.5, -0.5, (-3.5 - i * 2.0)), 0.6, new PerfectDiffuseMaterial(green)));
//				}
//				for (int i = 0; i < 10; i++) {
//					geoObjectList.add(new Sphere(vec3(3.5, -0.2, (-2.5 - i * 2.0)), 0.6, new PerfectDiffuseMaterial(blue)));
//					geoObjectList.add(new Sphere(vec3(3.5, -0.2, (-3.5 - i * 2.0)), 0.6, new PerfectDiffuseMaterial(aqua)));
//				}
//				for (int i = 0; i < 15; i++) {
//					geoObjectList.add(new Sphere(vec3(Math.sin(Math.PI / 6 * i), -0.5, (0.0 - Math.exp(i * 0.5))),
//							(0.1 + i * 0.05), new PerfectDiffuseMaterial(red)));
//				}
//				for (int i = 0; i < 10; i++) {
//					geoObjectList.add(new Sphere(vec3(-2.5, (3.5 + i * 0.5), (-4.5 - i * 2.0)), 0.6, new PerfectDiffuseMaterial(orange)));
//
//				}
//				for (int i = 0; i < 8; i++) {
//					geoObjectList.add(new Sphere(vec3(-2.6, (3.0 + i * -0.5), (-4.5 - i * 2.0)), 0.6, new PerfectDiffuseMaterial(light_gray)));
//				}

		return new Group(geoObjectList);
	}

}
