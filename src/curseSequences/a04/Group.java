package curseSequences.a04;

import java.util.ArrayList;
import java.util.List;

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
			if (shape.intersect(ray) != null) {
				hitList.add(shape.intersect(ray));
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
