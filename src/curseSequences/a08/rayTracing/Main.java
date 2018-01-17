package curseSequences.a08.rayTracing;

import curseSequences.Image;
import curseSequences.a08.sceneGraph.Scene;

import java.io.IOException;
import java.time.Instant;

public class Main {
	final static int WIDTH = 1600;
	final static int HEIGHT = 900;

	public static void main(String[] args) {
		Image image = new Image(WIDTH, HEIGHT);
		Camera camera = new Camera(Math.PI / 2, 1600, 900, CameraViews.lookDownCenterPerspective());
		Scene scene = new Scene();
		Instant start, end;
		Long startLong, endLong;
		start = Instant.now();
		startLong = start.getEpochSecond();
		image = raytrace(scene, camera, 2, image);
		String filename = "doc/a08-test.png";
		try {
			image.write(filename);
			end = Instant.now();
			endLong = end.getEpochSecond();
			System.out.println("Wrote image: " + filename + " (" + (endLong - startLong) + " s)");
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
