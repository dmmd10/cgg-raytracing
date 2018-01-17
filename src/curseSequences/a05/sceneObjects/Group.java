package curseSequences.a05.sceneObjects;

import java.util.ArrayList;
import java.util.List;

import curseSequences.a05.rayTracing.Hit;
import curseSequences.a05.rayTracing.HitComparator;
import curseSequences.a05.rayTracing.Ray;

public class Group implements Shape {

	protected List<Shape> geoObjectList;
	protected List<Hit> hitList;
	protected final HitComparator hitComparator = new HitComparator();
	
	public Group(List<Shape> geoObjectList) {
		this.geoObjectList = geoObjectList;
	}

	@Override
	public Hit intersect(Ray ray) {
		hitList = new ArrayList<Hit>();
		for(Shape shape: geoObjectList) {
			Hit hit = shape.intersect(ray);
			if (hit != null) {
				hitList.add(hit);
			}
		}
		if (hitList.isEmpty()) {
			return null;
		} else {
			hitList.sort(hitComparator);
			return hitList.get(0);
		}
	}

}
