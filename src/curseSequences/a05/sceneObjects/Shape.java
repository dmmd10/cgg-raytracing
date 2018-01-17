package curseSequences.a05.sceneObjects;

import curseSequences.a05.rayTracing.Hit;
import curseSequences.a05.rayTracing.Ray;

public interface Shape {
	public final double T_MIN = 0.0001;
	public final double T_MAX = Double.POSITIVE_INFINITY;
	public Hit intersect(Ray ray);
}
