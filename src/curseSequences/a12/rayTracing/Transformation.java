package curseSequences.a12.rayTracing;

import cgtools.Mat4;
import cgtools.Vec3;

public class Transformation {
	public Mat4 mObjWorld;
	public final Mat4 mWorldObj;
	public final Mat4 mObjWorldNormal;

	public Transformation(Mat4 mObjWorld) {
		this.mObjWorld = mObjWorld;
		mWorldObj = mObjWorld.invertFull();
		mObjWorldNormal = mWorldObj.transpose();
	}
	
	public Vec3 toWorld(Vec3 hitpoint) {
		return mObjWorld.transformPoint(hitpoint);
	}
	
	public Vec3 toWorldN(Vec3 normal) {
		return mObjWorldNormal.transformDirection(normal);
	}
	
	public Ray toObject(Ray ray) {
		Vec3 x0$ = mWorldObj.transformPoint(ray.x0);
		Vec3 d$ = mWorldObj.transformDirection(ray.d);
		return new Ray(x0$, d$, ray.T_MIN, ray.T_MAX);
	}
}
