package curseSequences.a07.sceneObjects;

import curseSequences.a07.rayTracing.Hit;
import curseSequences.a07.rayTracing.Ray;

public interface Shape {
	public Hit intersect(Ray ray);
}
