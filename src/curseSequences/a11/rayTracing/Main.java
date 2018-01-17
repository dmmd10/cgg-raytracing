package curseSequences.a11.rayTracing;

import curseSequences.Image;
import curseSequences.a11.lights.WorldLighting;
import curseSequences.a11.sceneGraph.Scene;
import curseSequences.a11.sceneGraph.World;

import static cgtools.Vec3.*;
import static cgtools.Mat4.*;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
//		Camera camera = new Camera_Standard(Math.PI / 2, 1600, 900, CameraViews.sidePerspective1());
//		Camera camera = new Camera_Standard(Math.PI / 2, 1600, 900, CameraViews.sidePerspective2());
		Camera camera = new Camera_Standard(Math.PI / 2, 1600, 900, CameraViews.sidePerspective3());
//		Camera camera = new Camera_Standard(Math.PI / 2, 1600, 900, CameraViews.sidePerspective4());
//		Camera camera = new Camera_Standard(Math.PI / 2, 1600, 900, CameraViews.lookUpCenterPerspective());
//		Camera camera = new Camera_Standard(Math.PI / 2, 1600, 900, CameraViews.lookDownCenterPerspective());
//		Camera camera = new Camera_StreetViewPhotoSphere(0.5*Math.PI, 1, 1600, 900, translate(vec3(0, 6, 0)));
		
		WorldLighting world = new WorldLighting();
		
		Instant start, end;
				
//		ImageTexture imgTex = new ImageTexture("doc/rasen.jpg");
//		imgTex.reverseGammaCorrection(2.2);
		
		start = Instant.now();		
		calcImageWithConcurrency(image, camera);

		String filename = "doc/a11_test.png";
		try {
			image.write(filename);
			end = Instant.now();
			System.out.println("Wrote image: " + filename + " (" + ChronoUnit.SECONDS.between(start, end) + " s)");
		} catch (IOException error) {
			System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
		}
	}

	public static Image raytrace(World world, Camera camera, WorldLighting worldLight, int sampleMatrixSize, Image image) {
		return raytrace(world, camera, worldLight, sampleMatrixSize, sampleMatrixSize, image);
	}
	
	public static Image raytrace(World world, Camera camera, WorldLighting worldLight, int sampleMatrixSizeX, int sampleMatrixSizeY, Image image) {
		Sampling sampling = new Sampling(camera, world, worldLight);
		StratifiedSampling stratifiedSampling = new StratifiedSampling(sampling, sampleMatrixSizeX, sampleMatrixSizeY);
		for (int x = 0; x != WIDTH; x++) {
			for (int y = 0; y != HEIGHT; y++) {
				image.setPixel(x, y, stratifiedSampling.pixelColor(x, y));
			}
		}
		return image;
	}
	
	private static void calcImageWithConcurrency(Image image, Camera camera) {
		int cores = Runtime.getRuntime().availableProcessors()-1;
//		int cores = 1;
		int superSamplingSize = 1;
//		int superSamplingSizeY = 8;

		List<CompletableFuture<Image>> cFutures = new ArrayList<CompletableFuture<Image>>();
		for (int i = 0; i < cores; i++) {
			cFutures.add(CompletableFuture.supplyAsync(() -> 
					raytrace(new World(), camera, new WorldLighting(), superSamplingSize, new Image(WIDTH, HEIGHT))));
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
