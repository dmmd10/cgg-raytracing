package curseSequences.a04;

import cgtools.Vec3;

public class Hit implements Comparable<Hit> {
	public final double t;
	public final Vec3 hitPoint;
	public final Vec3 normal;
	public final Vec3 color;
	
	public Hit(double t, Vec3 hitPoint, Vec3 normal, Vec3 color) {
		this.t = t;
		this.hitPoint = hitPoint;
		this.normal = normal;
		this.color = color;
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
		return "Hit [t=" + t + ", hitPoint=" + hitPoint + ", normal=" + normal + ", color=" + color + "]";
	}
}
