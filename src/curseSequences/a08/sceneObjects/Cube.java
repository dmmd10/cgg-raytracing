package curseSequences.a08.sceneObjects;

import cgtools.Vec3;
import static cgtools.Vec3.*;
import curseSequences.a08.materials.Material;
import curseSequences.a08.rayTracing.Hit;
import curseSequences.a08.rayTracing.Ray;

public class Cube implements Shape {

	public final Vec3 minPoint;
	public final Vec3 maxPoint;
	public final Material material;
	
	public Cube(Vec3 minPoint, Vec3 maxPoint, Material material) {
		this.minPoint = minPoint;
		this.maxPoint = maxPoint;
		this.material = material;
	}

	@Override
	public Hit intersect(Ray ray) {
		Vec3 dInverse = inverse(ray.d);
		double Tnear = Double.NEGATIVE_INFINITY;
		double Tfar = Double.POSITIVE_INFINITY;
		Vec3 TnearNormal = vec3(0, 0, 0);
		double tx1 = 0;
		double tx2 = 0;
		if (ray.d.x == 0) {
			if ( minPoint.x > ray.x0.x || ray.x0.x < maxPoint.x) {
				return null;
			}
		} else {
			tx1 = (minPoint.x - ray.x0.x) * dInverse.x;
			tx2 = (maxPoint.x - ray.x0.x) * dInverse.x;
			if (tx1 > tx2) {
				double tmp = tx1;
				tx1 = tx2;
				tx2 = tmp;
			}
			if (tx1 > Tnear) {
				Tnear = tx1;
				TnearNormal = vec3(1, 0, 0);
			}
			if (tx2 < Tfar) {Tfar = tx2;}
			if (Tnear > Tfar) {return null;}
			if (Tfar < ray.T_MIN) {return null;}
		}
		
		double ty1 = 0;
		double ty2 = 0;
		if (ray.d.y == 0) {
			if ( minPoint.y > ray.x0.y || ray.x0.y < maxPoint.y) {
				return null;
			}
		} else {
			ty1 = (minPoint.y - ray.x0.y) * dInverse.y;
			ty2 = (maxPoint.y - ray.x0.y) * dInverse.y;
			if (ty1 > ty2) {
				double tmp = ty1;
				ty1 = ty2;
				ty2 = tmp;
			}
			if (ty1 > Tnear) {
				Tnear = ty1;
				TnearNormal = vec3(0, 1, 0);
			}
			if (ty2 < Tfar) {Tfar = ty2;}
			if (Tnear > Tfar) {return null;}
			if (Tfar < ray.T_MIN) {return null;}
		}
		
		double tz1 = 0;
		double tz2 = 0;
		if (ray.d.z == 0) {
			if ( minPoint.z > ray.x0.z || ray.x0.z < maxPoint.z) {
				return null;
			}
			if (dotProduct(TnearNormal, ray.d) > 0){
				TnearNormal = multiply(-1, TnearNormal);
			}
			return new Hit(Tnear, add(ray.x0, multiply(Tnear,ray.d)), TnearNormal, material);
		} else {
			tz1 = (minPoint.z - ray.x0.z) * dInverse.z;
			tz2 = (maxPoint.z - ray.x0.z) * dInverse.z;
			if (tz1 > tz2) {
				double tmp = tz1;
				tz1 = tz2;
				tz2 = tmp;
			}
			if (tz1 > Tnear) {
				Tnear = tz1;
				TnearNormal = vec3(0, 0, 1);
			}
			if (tz2 < Tfar) {Tfar = tz2;}
			if (Tnear > Tfar) {return null;}
			if (Tfar < ray.T_MIN) {return null;}
			if (dotProduct(TnearNormal, ray.d) > 0){
				TnearNormal = multiply(-1, TnearNormal);
			}
			return new Hit(Tnear, add(ray.x0, multiply(Tnear,ray.d)), TnearNormal, material);
		}
	}
}
