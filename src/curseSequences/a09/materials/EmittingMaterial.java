package curseSequences.a09.materials;

import cgtools.Vec3;
import static cgtools.Vec3.*;
import curseSequences.a09.rayTracing.Hit;
import curseSequences.a09.rayTracing.Ray;

public class EmittingMaterial implements Material {

	public final Vec3 emittedRadiance;
	
	public EmittingMaterial(Vec3 emittedRadiance) {
		this.emittedRadiance = emittedRadiance;
	}

	// Wenn das Objekt Licht emittiert
	// Abhängig wo das Objekt getroffen wird (zB blau-weißer Himmel)
	@Override
	public Vec3 emittedRadiance(Ray r, Hit h) {
		return emittedRadiance;
	}

	// Berechnet den zufälligen Strahl für die nächsttiefere Reflektion
	// Hit dient zur Bestimmung der Normalen für die Berechnung des zufälligen neuen Strahls
	// Ray momentan uninteressant
	@Override
	public Ray scatteredRay(Ray r, Hit h) {
		return null;
	}

	// Berechnet die neue Lichtintensität abhängig von jeweiligen Trefferpunkt
	// aber hier ist die Albedo auf der gesamten Oberfläche gleich
	@Override
	public Vec3 albedo(Ray r, Hit h) {
		return null;
	}

}
