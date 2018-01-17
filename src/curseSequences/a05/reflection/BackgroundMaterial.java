package curseSequences.a05.reflection;

import cgtools.Vec3;
import static cgtools.Vec3.*;
import curseSequences.a05.rayTracing.Hit;
import curseSequences.a05.rayTracing.Ray;

public class BackgroundMaterial implements Material {

	public final Vec3 albedo;
	
	public BackgroundMaterial(Vec3 albedo) {
		this.albedo = albedo;
	}

	// Wenn das Objekt Licht emittiert
	// Abhängig wo das Objekt getroffen wird (zB blau-weißer Himmel)
	@Override
	public Vec3 emittedRadiance(Ray r, Hit h) {
		return aqua;
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
