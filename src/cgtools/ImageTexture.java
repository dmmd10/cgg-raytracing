package cgtools;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static cgtools.Vec3.*;

public class ImageTexture {
    private BufferedImage image;
    public final int width;
    public final int height;
    private final double[] pixelBuffer;
    private final double componentScale;
    private String filename;
    int count = 0;

    public ImageTexture(String filename) throws IOException {
        this.filename = filename;
    	image = ImageIO.read(new File(filename));
        width = image.getWidth();
        height = image.getHeight();
        pixelBuffer = new double[image.getRaster().getNumBands()];

        switch (image.getSampleModel().getDataType()) {
        case DataBuffer.TYPE_BYTE:
            componentScale = 255;
            break;
        case DataBuffer.TYPE_USHORT:
            componentScale = 65535;
            break;
        default:
            componentScale = 1;
            break;
        }
    }
    
    public void reverseGammaCorrection(double gammaFactor) throws IOException {
    	for (int h = 0; h < height; h++) {
    		for (int w = 0; w < width; w++) {
    			int pixel = image.getRGB(w, h);
    			  int red   = (pixel >> 16) & 0xFF,
    			      green = (pixel >> 8) & 0xFF,
    			      blue  = (pixel) & 0xFF;
    			  double red_r01 = red / 255.0;
    			  double green_r01 = green / 255.0;
    			  double blue_r01 = blue / 255.0;
    			  red_r01 = Math.pow(red_r01, gammaFactor);
    			  green_r01 = Math.pow(green_r01, gammaFactor);
    			  blue_r01 = Math.pow(blue_r01, gammaFactor);
    			  red = (int)(red_r01 * 255);
    			  green = (int)(green_r01 * 255);
    			  blue = (int)(blue_r01 * 255);
    			  image.setRGB(w, h, new Color(red, green, blue).getRGB());
    		}
		}
    	ImageIO.write(image, "jpg", new File(filename.substring(0, filename.length()-4) + "_linear.jpg"));
    }

    public Vec3 samplePoint(double u, double v) {
        int x = (int) ((u - Math.floor(u)) * width);
        int y = (int) ((v - Math.floor(v)) * height);
        image.getRaster().getPixel(x, y, pixelBuffer);
        Vec3 color = vec3(pixelBuffer[0], pixelBuffer[1], pixelBuffer[2]);
        return divide(color, componentScale);
    }
}
