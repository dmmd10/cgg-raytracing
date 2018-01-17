package curseSequences.a11.sceneObjects;

import curseSequences.a11.rayTracing.BoundingBox;
import curseSequences.a11.rayTracing.Hit;
import curseSequences.a11.rayTracing.Ray;

public interface Shape {
	public Hit intersect(Ray ray);
	public BoundingBox bounding();
}
