package curseSequences.a12.rayTracing;

import cgtools.Vec3;
import curseSequences.a12.materials.Material;

public class Hit implements Comparable<Hit> {
	public final double t;
	public final Vec3 hitPoint;
	public final Vec3 normal;
	public final Vec3 uv;
	public final Material material;
	public final String objName;
	
	public Hit(double t, Vec3 hitPoint, Vec3 normal, Vec3 uv, Material material) {
		this(t, hitPoint, normal, uv, material, "");
	}
	
	public Hit(double t, Vec3 hitPoint, Vec3 normal, Vec3 uv, Material material, String objName) {
		this.t = t;
		this.hitPoint = hitPoint;
		this.normal = normal;
		this.uv = uv;
		this.material = material;
		this.objName = objName;
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
		return "Hit [t=" + t + ", hitPoint=" + hitPoint + ", normal=" + normal + ", uv=" + uv + ", material=" + material
				+ "]";
	}
}
