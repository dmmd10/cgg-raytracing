package curseSequences.a10.sceneObjects;

import curseSequences.a10.rayTracing.BoundingBox;
import curseSequences.a10.rayTracing.Hit;
import curseSequences.a10.rayTracing.Ray;

public interface Shape {
	public Hit intersect(Ray ray);
	public BoundingBox bounding();
}
