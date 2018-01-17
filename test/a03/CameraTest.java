package a03;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cgtools.Vec3;
import curseSequences.a03.Camera;
import curseSequences.a03.Ray;

public class CameraTest {

	protected Camera camera;

	@Before
	public void setUp() {
		camera = new Camera(Math.PI/2, 10, 10);
	}

	@Test
	public void testOrigin() {
		Ray ray = camera.generateRay(45, 43);
		Assert.assertEquals(Vec3.vec3(0), ray.getX0());
	}
	
	@Test
	public void testDOfRayInX0Y0() {
		Ray ray = camera.generateRay(0, 0);
		Assert.assertEquals(Vec3.vec3(-1/Math.sqrt(3), 1/Math.sqrt(3), -1/Math.sqrt(3)), ray.getD());
	}
	
	@Test
	public void testDOfRayInX5Y5() {
		Ray ray = camera.generateRay(5, 5);
		Assert.assertEquals(Vec3.vec3(0, 0, -1), ray.getD());
	}
	
	@Test
	public void testDOfRayInX10Y10() {
		Ray ray = camera.generateRay(10, 10);
		Assert.assertEquals(Vec3.vec3(1/Math.sqrt(3), -1/Math.sqrt(3), -1/Math.sqrt(3)), ray.getD());
	}
}
