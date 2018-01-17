package curseSequences.a06.sceneObjects;

import static cgtools.Vec3.*;

import java.util.ArrayList;
import java.util.List;

import curseSequences.a06.materials.BackgroundMaterial;
import curseSequences.a06.materials.GlassMaterial;
import curseSequences.a06.materials.MetalMaterial;
import curseSequences.a06.materials.PerfectDiffuseMaterial;
import curseSequences.a06.materials.RoughMetalMaterial;
import curseSequences.a06.rayTracing.Hit;
import curseSequences.a06.rayTracing.HitComparator;
import curseSequences.a06.rayTracing.Ray;

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
		sceneElementList.add(new Background(new BackgroundMaterial(lightYellow)));
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
//		|+Y
//		|____+X
//	   /
//	  /+z

		 geoObjectList.add(new Plane(vec3(0.0, -0.5, 0.0), vec3(0, 1, 0), new PerfectDiffuseMaterial(mint)));
		 geoObjectList.add(new Sphere(vec3(-1.7, 0.25, -5.5), 0.7, new RoughMetalMaterial(gray, 0.1)));
		 geoObjectList.add(new Sphere(vec3(0.0, 0.25, -5.5), 0.5, new MetalMaterial(gray)));
		 geoObjectList.add(new Sphere(vec3(0.3, 0.3, -2.5), 0.2, new MetalMaterial(marine)));
		 geoObjectList.add(new Sphere(vec3(0.4, 0.4, -1.3), 0.2, new GlassMaterial(white, 1.5)));
//		 geoObjectList.add(new Sphere(vec3(1.0, -0.25, -2.5), 0.7, new RoughMetalMaterial(green, 1)));
		 geoObjectList.add(new Sphere(vec3(-1.0, 1.5, -5.5), 0.7, new MetalMaterial(aqua)));
		 geoObjectList.add(new Sphere(vec3(1.7, 1.9, -9.5), 2.4, new MetalMaterial(aqua)));
		
		 geoObjectList.add(new Sphere(vec3(60, 20, 30), 20, new PerfectDiffuseMaterial(yellow)));
		 geoObjectList.add(new Sphere(vec3(20, 20, 30), 20, new PerfectDiffuseMaterial(orange)));
		 geoObjectList.add(new Sphere(vec3(-20, 20, 30), 20, new PerfectDiffuseMaterial(marine)));
		 geoObjectList.add(new Sphere(vec3(-60, 20, 30), 20, new PerfectDiffuseMaterial(violet)));
		 
		 geoObjectList.add(new Sphere(vec3(40, 65, 30), 20, new PerfectDiffuseMaterial(red)));
		 geoObjectList.add(new Sphere(vec3(0, 65, 30), 20, new PerfectDiffuseMaterial(green)));
		 geoObjectList.add(new Sphere(vec3(-40, 65, 30), 20, new PerfectDiffuseMaterial(blue)));
		 
		 geoObjectList.add(new Sphere(vec3(20, 110, 30), 20, new PerfectDiffuseMaterial(aqua)));
		 geoObjectList.add(new Sphere(vec3(-20, 110, 30), 20, new PerfectDiffuseMaterial(gray)));
		 
		 geoObjectList.add(new Sphere(vec3(0, 155, 30), 20, new PerfectDiffuseMaterial(black)));

		return new Group(geoObjectList);
	}

}
