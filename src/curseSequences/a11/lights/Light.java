package curseSequences.a11.lights;

import cgtools.Vec3;
import curseSequences.a11.rayTracing.Hit;
import curseSequences.a11.sceneObjects.Shape;

public interface Light {
	public Vec3 sample(Hit hit, Shape scene);
}
