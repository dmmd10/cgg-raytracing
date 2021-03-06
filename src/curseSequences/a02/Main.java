package curseSequences.a02;

import cgtools.Random;
import cgtools.Vec3;
import curseSequences.Image;

import static cgtools.Vec3.*;
import java.io.IOException;
import java.util.List;

public class Main {
    final static int WIDTH = 1600;
    final static int HEIGHT = 900;
    final static int NUMBERS_OF_CIRCLES = 50;
    final static double MIN_RADIUS = 50;
    final static double MAX_RADIUS = 300;
    static List<Circle> circleRepo;
    final static Vec3 BG_COLOR = new Vec3(0.1, 0.1, 0.1);
    final static int SAMPLE_MATRIX_SIZE = 10;
   

    public static void main(String[] args) {
        Image image = new Image(WIDTH, HEIGHT);
        circleRepo = new CircleRepository(NUMBERS_OF_CIRCLES, WIDTH, HEIGHT, MIN_RADIUS, MAX_RADIUS).getCircleList();
        
        for (int x = 0; x != WIDTH; x++) {
            for (int y = 0; y != HEIGHT; y++) {
                image.setPixel(x, y, pixelColor(x, y, SAMPLE_MATRIX_SIZE));
            }
        }

        String filename = "doc/a02-super-sampling.png";
        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }
        
    }
    
    static Vec3 pixelColor(int x, int y, int sampleMatrixSize) {
    	double red = 0;
    	double green = 0;
    	double blue = 0;
    	int samplePoints = sampleMatrixSize * sampleMatrixSize;

    	for(int xi = 0; xi < sampleMatrixSize; xi++){
    		for(int yi = 0; yi < sampleMatrixSize; yi++){
    			double rx = Random.random();
    			double ry = Random.random();
    			double xs = x + (xi+rx) / sampleMatrixSize;
    			double ys = y + (yi+ry) / sampleMatrixSize;
    			Vec3 sampleColor = getColorOfSamplePoint(xs, ys);
    			red += sampleColor.x;
    			green += sampleColor.y;
    			blue += sampleColor.z;
    		}
    	}
    	return new Vec3( red/samplePoints , green/samplePoints, blue/samplePoints );
    }
    
    static Vec3 getColorOfSamplePoint(double x, double y){
    	Vec3 temp = BG_COLOR;
    	for (Circle circle: circleRepo) {
    		if (circle.isPixelInsideCircle(x, y)) {
    			temp = circle.getColor();
    		}
    	}
    	return temp;
    }
}
