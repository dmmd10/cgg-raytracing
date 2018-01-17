package a03;

import org.junit.Assert;
import org.junit.Test;

import cgtools.Vec3;
import curseSequences.a03.Hit;
import curseSequences.a03.Ray;
import curseSequences.a03.Sphere;

public class SphereTest {

	@Test
	public void testConfiguration1() {
		Sphere sphere = new Sphere(Vec3.vec3(0, 0, -2), 1, 0, Integer.MAX_VALUE);
		Ray ray = new Ray(Vec3.vec3(0), Vec3.vec3(0, 0, -1));
		Hit hit = sphere.intersect(ray);
		Assert.assertEquals(Vec3.vec3(0, 0, -1), hit.getHitPoint());
		Assert.assertEquals(Vec3.vec3(0, 0, 1), hit.getNormal());
	}
	
	@Test
	public void testConfiguration2() {
		Sphere sphere = new Sphere(Vec3.vec3(0, 0, -2), 1, 0, Integer.MAX_VALUE);
		Ray ray = new Ray(Vec3.vec3(0), Vec3.vec3(0, 1, -1));
		Hit hit = sphere.intersect(ray);
		Assert.assertEquals(null, hit);
	}
	
	@Test
	public void testConfiguration3() {
		Sphere sphere = new Sphere(Vec3.vec3(0, -1, -2), 1, 0, Integer.MAX_VALUE);
		Ray ray = new Ray(Vec3.vec3(0), Vec3.vec3(0, 0, -1));
		Hit hit = sphere.intersect(ray);
		Assert.assertEquals(Vec3.vec3(0, 0, -2), hit.getHitPoint());
		Assert.assertEquals(Vec3.vec3(0, 1, 0), hit.getNormal());
	}
	
	@Test
	public void testConfiguration4() {
		Sphere sphere = new Sphere(Vec3.vec3(0, 0, -2), 1, 0, Integer.MAX_VALUE);
		Ray ray = new Ray(Vec3.vec3(0, 0, -4), Vec3.vec3(0, 0, -1));
		Hit hit = sphere.intersect(ray);
		Assert.assertEquals(null, hit);
	}
}
