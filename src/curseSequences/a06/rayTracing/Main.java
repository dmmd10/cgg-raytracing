package curseSequences.a06.rayTracing;

import curseSequences.Image;
import curseSequences.a06.sceneObjects.Scene;

import java.io.IOException;

public class Main {
	final static int WIDTH = 1600;
	final static int HEIGHT = 900;

	public static void main(String[] args) {
		Image image = new Image(WIDTH, HEIGHT);
		Camera camera = new Camera(Math.PI / 2, 1600, 900);
		Scene scene = new Scene();
		image = raytrace(scene, camera, 10, image);
		String filename = "doc/a06-mirrors-glass-2.png";
		try {
			image.write(filename);
			System.out.println("Wrote image: " + filename);
		} catch (IOException error) {
			System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
		}
	}

	public static Image raytrace(Scene scene, Camera camera, int sampleMatrixSize, Image image) {
		for (int x = 0; x != WIDTH; x++) {
			for (int y = 0; y != HEIGHT; y++) {
				image.setPixel(x, y, new StratifiedSampling(new Sampling(camera, scene), sampleMatrixSize).pixelColor(x, y));
			}
		}
		return image;
	}
}
