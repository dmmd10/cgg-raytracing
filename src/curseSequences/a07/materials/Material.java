package curseSequences.a07.materials;

import cgtools.Vec3;
import curseSequences.a07.rayTracing.Hit;
import curseSequences.a07.rayTracing.Ray;

public interface Material {
	Vec3 emittedRadiance(Ray r, Hit h);
	Ray scatteredRay(Ray r, Hit h);
	Vec3 albedo(Ray r, Hit h);
}
