package curseSequences.a10.rayTracing;

import curseSequences.Image;
import curseSequences.a10.sceneGraph.Scene;

import static cgtools.Vec3.*;
import static cgtools.Mat4.*;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import cgtools.ImageTexture;
import cgtools.Vec3;

public class Main {
	final static int WIDTH = 1600;
	final static int HEIGHT = 900;

	public static void main(String[] args) throws InterruptedException, IOException {
		Image image = new Image(WIDTH, HEIGHT);
		Camera camera = new Camera_Standard(Math.PI / 2, 1600, 900, CameraViews.sidePerspective());
//		Camera camera = new Camera_Standard(Math.PI / 2, 1600, 900, CameraViews.lookDownCenterPerspective());
//		Camera camera = new Camera_StreetViewPhotoSphere(Math.PI / 2, 1, 1600, 900, translate(vec3(0, 6, 0)));
		
		Instant start, end;
		Long startLong, endLong;
		
//		ImageTexture imgTex = new ImageTexture("doc/rasen.jpg");
//		imgTex.reverseGammaCorrection(2.2);
		
		start = Instant.now();
		startLong = start.getEpochSecond();
		
		calcImageWithConcurrency(image, camera);

		String filename = "doc/a10_test.png";
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
		return raytrace(scene, camera, sampleMatrixSize, sampleMatrixSize, image);
	}
	
	public static Image raytrace(Scene scene, Camera camera, int sampleMatrixSizeX, int sampleMatrixSizeY, Image image) {
		for (int x = 0; x != WIDTH; x++) {
			for (int y = 0; y != HEIGHT; y++) {
				image.setPixel(x, y, new StratifiedSampling(new Sampling(camera, scene), sampleMatrixSizeX, sampleMatrixSizeY).pixelColor(x, y));
			}
		}
		return image;
	}
	
	private static void calcImageWithConcurrency(Image image, Camera camera) {
		int cores = Runtime.getRuntime().availableProcessors()-1;
		int superSamplingSize = 1;
//		int superSamplingSizeY = 8;

		List<CompletableFuture<Image>> cFutures = new ArrayList<CompletableFuture<Image>>();
		for (int i = 0; i < cores; i++) {
			cFutures.add(CompletableFuture.supplyAsync(() -> 
					raytrace(new Scene(), camera, superSamplingSize, new Image(WIDTH, HEIGHT))));
		}
		
		CompletableFuture<Void> combinedFuture 
		  = CompletableFuture.allOf(cFutures.toArray(new CompletableFuture[cFutures.size()]));
				
		List<Image> combined = cFutures.stream()
				  .map(CompletableFuture::join)
				  .collect(Collectors.<Image>toList());
	
		for (int w = 0; w < WIDTH; w++) {
			for (int h = 0; h < HEIGHT; h++) {
				Vec3 sampleColor = vec3(0);
				for (int i = 0; i < combined.size(); i++) {
					sampleColor = add(sampleColor, combined.get(i).getPixelColor(w, h));
				}
				Vec3 mean = divide(sampleColor, combined.size());
				image.setPixel_Ohne_G(w, h, mean);
			}
		}
	}
	
	
}
