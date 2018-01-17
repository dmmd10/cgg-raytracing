package curseSequences.a12.sceneObjects;

import curseSequences.a12.rayTracing.BoundingBox;
import curseSequences.a12.rayTracing.Hit;
import curseSequences.a12.rayTracing.Ray;

public interface Shape {
	public Hit intersect(Ray ray);
	public BoundingBox bounding();
}
