package curseSequences.a08.rayTracing;

import cgtools.Vec3;

public interface Sampler {
	public Vec3 pixelColor(double x, double y);
}
