package curseSequences;

import cgtools.ImageWriter;
import cgtools.Vec3;
import java.io.IOException;

public class Image {
	double[] imageData;
	int width;
	int height;
	final int RED = 0;
	final int GREEN = 1;
	final int BLUE = 2;
	
    public Image(int width, int height) {
        imageData = new double[ (width * height) * 3 ];
        this.width = width;
        this.height = height;
    }

    public Vec3 getPixelColor(int x, int y) {
    	int pixel = 3 * (width * y + x);
    	return Vec3.vec3(imageData[pixel + RED], imageData[pixel + GREEN], imageData[pixel + BLUE]);
    }
    
    public void setPixel(int x, int y, Vec3 color) {
    	int pixel = 3 * (width * y + x);
    	imageData[pixel + RED] =  Math.pow(color.x, 1/2.2);
    	imageData[pixel + GREEN] = Math.pow(color.y, 1/2.2);
    	imageData[pixel + BLUE] = Math.pow(color.z, 1/2.2);
    }
    
    // ohne Gammafilter 
    public void setPixel_Ohne_G(int x, int y, Vec3 color) {
    	int pixel = 3 * (width * y + x);
    	imageData[pixel + RED] =  color.x;
    	imageData[pixel + GREEN] = color.y;
    	imageData[pixel + BLUE] = color.z;
    }

    public void write(String filename) throws IOException {
        ImageWriter iWriter = new ImageWriter(imageData, width, height);
        iWriter.write(filename);
    }
}
