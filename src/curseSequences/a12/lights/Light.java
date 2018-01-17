package curseSequences.a12.lights;

import cgtools.Vec3;
import curseSequences.a12.rayTracing.Hit;
import curseSequences.a12.sceneObjects.Shape;

public interface Light {
	public Vec3 sample(Hit hit, Shape scene);
}
