package curseSequences.a04;

import static cgtools.Vec3.*;
import cgtools.Vec3;

public class Background implements Shape {

	protected final Vec3 color = white;
	
	@Override
	public Hit intersect(Ray ray) {
		double t = Double.POSITIVE_INFINITY;
		Vec3 hitPoint = add(ray.x0, multiply(t, ray.d));
		Vec3 normal = normalize(multiply(-1, ray.d));
		return new Hit(t, hitPoint, normal, color);
	}
}
