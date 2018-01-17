package curseSequences.a08.sceneObjects;

import curseSequences.a08.rayTracing.Hit;
import curseSequences.a08.rayTracing.Ray;

public interface Shape {
	public Hit intersect(Ray ray);
}
