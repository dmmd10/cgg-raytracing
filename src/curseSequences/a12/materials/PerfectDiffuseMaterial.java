package curseSequences.a12.materials;

import cgtools.Vec3;
import static cgtools.Vec3.*;

import cgtools.Random;
import curseSequences.a12.rayTracing.Hit;
import curseSequences.a12.rayTracing.Ray;

public class PerfectDiffuseMaterial implements Material {

	public final Texture albedo;
	protected Ray scatteredRay;

	public PerfectDiffuseMaterial(Texture albedo) {
		this.albedo = albedo;
	}

	// Wenn das Objekt Licht emittiert
	// Abhängig wo das Objekt getroffen wird (zB blau-weißer Himmel)
	@Override
	public Vec3 emittedRadiance(Ray r, Hit h) {
		return zero;
	}

	// Berechnet den zufälligen Strahl für die nächsttiefere Reflexion
	// Hit dient zur Bestimmung der Normalen für die Berechnung des zufälligen
	// neuen Strahls
	// Ray momentan uninteressant
	@Override
	public Ray scatteredRay(Ray r, Hit h) {
		while (true) {
			double x = Random.random() * 2 - 1;
			double y = Random.random() * 2 - 1;
			double z = Random.random() * 2 - 1;
			Vec3 sphereRadiusVec = vec3(x, y, z);
			if (sphereRadiusVec.length() <= 1) {
				scatteredRay = new Ray(h.hitPoint, normalize(add(h.normal, sphereRadiusVec)));
				return scatteredRay;
			}
		}
	}

	// Berechnet die neue Lichtintensität abhängig von jeweiligen Trefferpunkt
	// aber hier ist die Albedo auf der gesamten Oberfläche gleich
	@Override
	public Vec3 albedo(Ray r, Hit h) {
		return albedo.color(h.uv);
	}

	@Override
	public String toString() {
		return "PerfectDiffuseMaterial [albedo=" + albedo + ", emitted=zero, lastScat" + scatteredRay + "]";
	}
}
