package curseSequences.a04;

import static cgtools.Vec3.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import curseSequences.Image;

public class Main {
	final static int WIDTH = 1600;
	final static int HEIGHT = 900;

	public static void main(String[] args) {
		Image image = new Image(WIDTH, HEIGHT);

		Camera camera = new Camera(Math.PI / 2, 1600, 900);
		Group group = createGroup();
		image = raytrace(camera, group, 4, image);

		String filename = "doc/a04-scene.png";
		try {
			image.write(filename);
			System.out.println("Wrote image: " + filename);
		} catch (IOException error) {
			System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
		}

	}

	public static Image raytrace(Camera camera, Group group, int sampleMatrixSize, Image image) {
		for (int x = 0; x != WIDTH; x++) {
			for (int y = 0; y != HEIGHT; y++) {
				image.setPixel(x, y,
						new StratifiedSampling(new Scene(camera, group), sampleMatrixSize).pixelColor(x, y));
			}
		}
		return image;
	}

	protected static Group createGroup() {
		List<Shape> geoObjectList = new ArrayList<Shape>();

		// Testkonfiguration
		
		// geoObjectList.add(new Plane(vec3(0.0, -0.5, 0.0), vec3(0, 1, 0), gray));
		// geoObjectList.add(new Sphere(vec3(-1.0, -0.25, -2.5), 0.7, red));
		// geoObjectList.add(new Sphere(vec3(0.0, -0.25, -2.5), 0.5, green));
		// geoObjectList.add(new Sphere(vec3(1.0, -0.25, -2.5), 0.7, blue));

		// Scene
		
		geoObjectList.add(new Plane(vec3(0.0, -0.5, 0.0), vec3(0, 1, 0), gray));
		geoObjectList.add(new Plane(vec3(0.0, 1.5, 0.0), vec3(0, -1, -0.3), dark_gray));
		geoObjectList.add(new Plane(vec3(-2.5, 0, 0), vec3(-1, 0, 0), blue));

		for (int i = 0; i < 100; i++) {
			geoObjectList.add(new Sphere(vec3(-2.5, -0.5, (-2.5 - i * 2.0)), 0.6, red));
			geoObjectList.add(new Sphere(vec3(-2.5, -0.5, (-3.5 - i * 2.0)), 0.6, green));
		}
		for (int i = 0; i < 100; i++) {
			geoObjectList.add(new Sphere(vec3(3.5, -0.2, (-2.5 - i * 2.0)), 0.6, blue));
			geoObjectList.add(new Sphere(vec3(3.5, -0.2, (-3.5 - i * 2.0)), 0.6, marine));
		}
		for (int i = 0; i < 15; i++) {
			geoObjectList.add(new Sphere(vec3(Math.sin(Math.PI / 6 * i), -0.5, (0.0 - Math.exp(i * 0.5))),
					(0.1 + i * 0.05), red));
		}
		for (int i = 0; i < 100; i++) {
			geoObjectList.add(new Sphere(vec3(-2.5, (3.5 + i * 0.5), (-4.5 - i * 2.0)), 0.6, orange));

		}
		for (int i = 0; i < 8; i++) {
			geoObjectList.add(new Sphere(vec3(-2.6, (3.0 + i * -0.5), (-4.5 - i * 2.0)), 0.6, light_gray));
		}

		return new Group(geoObjectList);
	}
}
