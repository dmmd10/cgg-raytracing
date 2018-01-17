package curseSequences.a09.sceneGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import curseSequences.a09.rayTracing.Hit;
import curseSequences.a09.rayTracing.HitComparator;
import curseSequences.a09.rayTracing.Ray;
import curseSequences.a09.rayTracing.Transformation;
import curseSequences.a09.sceneObjects.Shape;

public class Group implements Shape {

//	protected Shape intersectTestShape;
	protected List<Shape> geoObjectList;
	protected Transformation transformation;
	protected final HitComparator hitComparator;
	
	public Group(Transformation transformation) {
		this.transformation = transformation;
		hitComparator = new HitComparator();
		geoObjectList = new ArrayList<Shape>();
		generateShapesList();
	}

	public void generateShapesList() {}
	

	public List<Group> flatten() {
		List<Group> globalList = new ArrayList<Group>();
		for (Shape shape : geoObjectList) {
			if (shape instanceof Group) {
				List<Group> list = ((Group) shape).flatten();
				for (Group group : list) {
					group.transformation.mObjWorld = transformation.mObjWorld.multiply(group.transformation.mObjWorld);
//					group.calcNewTransformation();
					globalList.add(group);
				}		
			} else {
				globalList.add(new Group(transformation) {
					public void generateShapesList(){
						geoObjectList = new ArrayList<Shape>(1);
						geoObjectList.add(shape);
					}
				});
			}
		}
		return globalList;
	}
	
	public void calcNewTransformation() {
		transformation = new Transformation(transformation.mObjWorld);
	}
	
	@Override
	public Hit intersect(Ray ray) {
		Ray transRay = transformation.toObject(ray);
//		System.out.println(this.getClass().getName() + " : \n" + transformation.mObjWorld);
		List<Hit> hitList = new ArrayList<Hit>();
//		if (intersectTestShape != null && intersectTestShape.intersect(transRay) == null) {
//			return null;
//		};
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
