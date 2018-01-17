package curseSequences.a12.materials;

import cgtools.Vec3;
import curseSequences.a12.rayTracing.Hit;
import curseSequences.a12.rayTracing.Ray;

public interface Material {
	Vec3 emittedRadiance(Ray r, Hit h);
	Ray scatteredRay(Ray r, Hit h);
	Vec3 albedo(Ray r, Hit h);
}
