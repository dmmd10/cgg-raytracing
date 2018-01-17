package curseSequences.a03;

import static cgtools.Vec3.*;
import cgtools.Vec3;

public class Hit {
	public final double t;
	public final Vec3 hitPoint;
	public final Vec3 normal;
	
	public Hit(double t, Vec3 hitPoint, Vec3 normal) {
		this.t = t;
		this.hitPoint = hitPoint;
		this.normal = normal;
	}

	public double getT() {
		return t;
	}

	public Vec3 getHitPoint() {
		return hitPoint;
	}

	public Vec3 getNormal() {
		return normal;
	}

	@Override
	public String toString() {
		return "Hit [t=" + t + ", hitPoint=" + hitPoint + ", normal=" + normal + "]";
	}
}
