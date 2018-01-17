package curseSequences.a05.rayTracing;

import cgtools.Vec3;
import curseSequences.a05.reflection.Material;

public class Hit implements Comparable<Hit> {
	public final double t;
	public final Vec3 hitPoint;
	public final Vec3 normal;
	public final Material material;
	
	public Hit(double t, Vec3 hitPoint, Vec3 normal, Material material) {
		this.t = t;
		this.hitPoint = hitPoint;
		this.normal = normal;
		this.material = material;
	}

	@Override
	public int compareTo(Hit other) {
		if (this.t == other.t){
			return 0;
		}
		if (this.t > other.t){
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public String toString() {
		return "Hit [t=" + t + ", hitPoint=" + hitPoint + ", normal=" + normal + ", material=" + material + "]";
	}
}
