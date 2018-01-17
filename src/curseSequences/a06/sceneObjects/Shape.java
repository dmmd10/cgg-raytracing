package curseSequences.a06.sceneObjects;

import curseSequences.a06.rayTracing.Hit;
import curseSequences.a06.rayTracing.Ray;

public interface Shape {
	public Hit intersect(Ray ray);
}
