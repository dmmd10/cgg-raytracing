package curseSequences.a12.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import java.util.ArrayList;
import java.util.List;

import cgtools.Mat4;
import curseSequences.a12.rayTracing.BoundingBox;
import curseSequences.a12.rayTracing.Hit;
import curseSequences.a12.rayTracing.HitComparator;
import curseSequences.a12.rayTracing.Ray;
import curseSequences.a12.rayTracing.Transformation;
import curseSequences.a12.sceneObjects.Shape;
import curseSequences.a12.sceneObjects.Sphere;

public class Group implements Shape {

	protected List<Shape> geoObjectList;
	public Transformation transformation;
	protected final HitComparator hitComparator;
	protected BoundingBox bb;
	public BoundingBox transformedBB;
	
	public Group() {
		this(translate(vec3(0, 0, 0)));
	}
	
	public Group(BoundingBox bb) {
		this();
		this.bb = bb;
	}
	
	public Group(Mat4 transformation) {
		this.transformation = new Transformation(transformation);
		hitComparator = new HitComparator();
		geoObjectList = new ArrayList<Shape>();
		generateShapesList();
	}
	
	public Group(Mat4 transformation, Shape shape) {
		this(transformation);
		geoObjectList = new ArrayList<Shape>(1);
		geoObjectList.add(shape);
		bb = shape.bounding();
	}
	
	public Group(Shape shape) {
		this(translate(vec3(0, 0, 0)), shape);
	}

	public void generateShapesList() {}
	

	public List<Group> flatten() {
		List<Group> globalList = new ArrayList<Group>();
		for (Shape shape : geoObjectList) {
			if (shape instanceof Group) {
				List<Group> list = ((Group) shape).flatten();
				for (Group lowerTierGlobalList: list) {
					lowerTierGlobalList.transformation.mObjWorld = transformation.mObjWorld.multiply(lowerTierGlobalList.transformation.mObjWorld);
					globalList.add(lowerTierGlobalList);
				}		
			} else {
				globalList.add(new Group(transformation.mObjWorld, shape));
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
		List<Hit> hitList = new ArrayList<Hit>();
		// BoundingBox check
		if (!bb.intersect(transRay)) {
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
					hit.uv,
					hit.material, hit.objName);
		}
	}
	
	public void add(Shape shape) {
		geoObjectList.add(shape);
	}
	
	@Override
	public BoundingBox bounding() {
		bb = new BoundingBox();
		for (Shape shape : geoObjectList) {
			if (shape instanceof Group) {
				Group group = (Group)shape;
				bb = bb.extend(group.bounding().transform(group.transformation.mObjWorld));
			} else {
				bb = bb.extend(shape.bounding());
			}
		}
		return bb;
	}

	@Override
	public String toString() {
		return ">>Group [" + geoObjectList.get(0) + " | bb="+bb+"]<<";
	}
	
	
}
