package curseSequences.a10.sceneGraph;

import static cgtools.Mat4.translate;
import static cgtools.Vec3.vec3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cgtools.Mat4;
import curseSequences.a10.rayTracing.BoundingBox;
import curseSequences.a10.rayTracing.Hit;
import curseSequences.a10.rayTracing.HitComparator;
import curseSequences.a10.rayTracing.Ray;
import curseSequences.a10.rayTracing.Transformation;
import curseSequences.a10.sceneObjects.Shape;

public class Group implements Shape {

	protected Shape intersectTestShape;
	protected List<Shape> geoObjectList;
	protected Transformation transformation;
	protected final HitComparator hitComparator;
	protected BoundingBox bb;
	
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
//		if (bb.intersect(transRay)) {
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
					hit.uv,
					hit.material);
		}
	}
	
	
//	 intersect ohne BVH
//	@Override
//	public Hit intersect(Ray ray) {
//		Ray transRay = transformation.toObject(ray);
//		List<Hit> hitList = new ArrayList<Hit>();
//		// eigene TestBox
//		if (intersectTestShape != null && intersectTestShape.intersect(transRay) == null) {
//			return null;
//		};
//		
//		for(Shape shape: geoObjectList) {
//			Hit hit = shape.intersect(transRay);
//			if (hit != null) {
//				hitList.add(hit);
//			}
//		}
//		if (hitList.isEmpty()) {
//			return null;
//		} else {
//			hitList.sort(hitComparator);
//			Hit hit = hitList.get(0);
//			return new Hit(hit.t, 
//					transformation.toWorld(hit.hitPoint),
//					transformation.toWorldN(hit.normal),
//					hit.uv,
//					hit.material);
//		}
//	}
	
	
	// wird bei nach flatten aufgerufen um BBs aus Shapes zu generieren + transformation
	// ohne transformation gibt es eine NullPoint Ex im Sampler, weil Hit iwann null ist
	// sky macht mit infinity probs
	@Override
	public BoundingBox bounding() {
		Shape flattenGraph1ShapeGroup = geoObjectList.get(0);
		Mat4 worldObj = transformation.mWorldObj;
		BoundingBox ret = flattenGraph1ShapeGroup.bounding().transform(worldObj);
		System.out.println(ret);
		return ret;
	}
	
	public void add(Shape shape) {
		geoObjectList.add(shape);
	}

}
