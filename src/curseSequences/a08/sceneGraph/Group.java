package curseSequences.a08.sceneGraph;

import java.util.ArrayList;
import java.util.List;

import curseSequences.a08.rayTracing.Hit;
import curseSequences.a08.rayTracing.HitComparator;
import curseSequences.a08.rayTracing.Ray;
import curseSequences.a08.rayTracing.Transformation;
import curseSequences.a08.sceneObjects.Shape;

public class Group implements Shape {

	protected Shape intersectTestShape;
	protected List<Shape> geoObjectList;
	protected Transformation transformation;
	protected List<Hit> hitList;
	protected final HitComparator hitComparator = new HitComparator();
	
	public Group(Transformation transformation) {
		this.transformation = transformation;
		geoObjectList = new ArrayList<Shape>();
		generateShapesList();
	}
	
	public void generateShapesList() {
		
	}

	@Override
	public Hit intersect(Ray ray) {
		Ray transRay = transformation.toObject(ray);
		hitList = new ArrayList<Hit>();
		if (intersectTestShape != null && intersectTestShape.intersect(transRay) == null) {
			return null;
		};
		for(Shape shape: geoObjectList) {
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
