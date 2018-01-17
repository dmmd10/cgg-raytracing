package curseSequences.a01;

import cgtools.Vec3;
import curseSequences.Image;

import static cgtools.Vec3.*;
import java.io.IOException;

public class Main {
    static int width = 160;
    static int height = 90;
    static int TILE_SIZE = 10;

    public static void main(String[] args) {
        Image image = new Image(width, height);

        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                image.setPixel(x, y, pixelColor(x, y));
            }
        }

        String filename = "doc/a01_background.png";
        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }
    }

    static Vec3 pixelColor(int x, int y) {
    	boolean evenY = (y / TILE_SIZE) % 2 == 0;
    	boolean evenX = (x / TILE_SIZE) % 2 == 0;
    	boolean oddX = (x / TILE_SIZE) % 2 == 1;
    	float RED = (float) x / width;
    	Vec3 BLACK = vec3(0, 0, 0);
    	
    	if (evenY) {
    		if (evenX) {
    			return BLACK;
    		} else {
                return vec3(RED, 0.5, 1);
    		}
    	} else {
    		if (oddX) {
    			return BLACK;
    		} else {
                return vec3(RED, 0.5, 1);
    		}
    	}
    }
}
