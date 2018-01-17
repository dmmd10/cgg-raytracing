package curseSequences.a11.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import java.util.ArrayList;
import java.util.List;

import cgtools.Mat4;
import curseSequences.a11.materials.ChessTexture;
import curseSequences.a11.materials.ColoredTexture;
import curseSequences.a11.materials.ConstantTexture;
import curseSequences.a11.materials.EmittingMaterial;
import curseSequences.a11.materials.MetalMaterial;
import curseSequences.a11.materials.PerfectDiffuseMaterial;
import curseSequences.a11.materials.PicTexture;
import curseSequences.a11.materials.TransformedTexture;
import curseSequences.a11.rayTracing.Hit;
import curseSequences.a11.rayTracing.Ray;
import curseSequences.a11.sceneObjects.Background;
import curseSequences.a11.sceneObjects.Plane;
import curseSequences.a11.sceneObjects.Rectangle;
import curseSequences.a11.sceneObjects.Shape;

public class World extends Group {
	
	public Scene scene;
	public SceneAndPlane sceneAndPlane;
	
	public World() {
	}
	
	public World(Shape shape) {
		super(shape);
	}

	@Override
	public void generateShapesList() {
//		geoObjectList.add(new Background(
//				new EmittingMaterial(new TransformedTexture(
//						new PicTexture("doc/sky_linear.jpg"), bgTransform())))); 
		geoObjectList.add(new Background(
				new EmittingMaterial(new ColoredTexture(new TransformedTexture(
						new PicTexture("doc/sky_linear.jpg"), bgTransform()), vec3(0.003))))); 
//		geoObjectList.add(new Rectangle(vec3(-30.0, 0.002, -40.0), 60, 80,
//				new EmittingMaterial(new PicTexture("doc/rasen_linear.jpg"))));
//		geoObjectList.add(new Plane(vec3(0.0, 0.001, 0.0),
//				new EmittingMaterial(new PicTexture("doc/granite_linear.jpg")), 20, 20));
//		 geoObjectList.add(new Plane(vec3(0.0, 0.0, 0.0), new
//		 PerfectDiffuseMaterial(new ChessTexture(light_mint, marine, 0.000001)),
//		 9E6, 9E6));
		
		sceneAndPlane = new SceneAndPlane(
				new Plane(vec3(0.0, 0.001, 0.0),
				new MetalMaterial(new ConstantTexture(light_mint))));
		geoObjectList.add(sceneAndPlane);
		scene = new Scene();
		geoObjectList.add(scene); 
	}
	
	private Mat4 bgTransform() {
		Mat4 translation = translate(vec3(0.25, 0, 0));
		Mat4 scale = scale(vec3(-1, 1, 0));
		return scale.multiply(translation);
	}
	
	@Override
	public Hit intersect(Ray ray) {
		List<Hit> hitList = new ArrayList<Hit>();
		
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
			Hit hit = hitList.get(0);
			return new Hit(hit.t, 
					hit.hitPoint,
					hit.normal,
					hit.uv,
					hit.material,
					hit.objName);
		}
	}
}
