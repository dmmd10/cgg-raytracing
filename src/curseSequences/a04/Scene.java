package curseSequences.a04;

import java.util.ArrayList;
import java.util.List;

import cgtools.Vec3;

public class Scene implements Sampler, Shape {

	final static Camera camera = new Camera(Math.PI/2, 1600, 900);
	protected List<Shape> sceneElementList;
	protected List<Hit> hitList;
	protected final HitComparator hitComparator = new HitComparator();
    
	public Scene(Camera camera, Group group) {
		createSceneElements(group);
	}
	
	protected void createSceneElements(Group group) {
		sceneElementList = new ArrayList<Shape>();
		sceneElementList.add(group);
		sceneElementList.add(new Background());
	}

	@Override
	public Hit intersect(Ray ray) {
		hitList = new ArrayList<Hit>();
		for(Shape shape: sceneElementList) {
			if (shape.intersect(ray) != null) {
				hitList.add(shape.intersect(ray));
			}
		}
		hitList.sort(hitComparator);
		return hitList.get(0);
	}

	@Override
	public Vec3 pixelColor(double x, double y) {
		Ray ray = camera.generateRay(x, y);
    	Hit hit = this.intersect(ray);
    	return hit.color;
	}
}
