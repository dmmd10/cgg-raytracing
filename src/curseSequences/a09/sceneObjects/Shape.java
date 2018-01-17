package curseSequences.a09.sceneObjects;

import curseSequences.a09.rayTracing.Hit;
import curseSequences.a09.rayTracing.Ray;

public interface Shape {
	public Hit intersect(Ray ray);
}
