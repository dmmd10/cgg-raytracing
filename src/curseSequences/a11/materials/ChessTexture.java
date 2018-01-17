package curseSequences.a11.materials;

import static cgtools.Vec3.vec3;

import cgtools.Vec3;

public class ChessTexture implements Texture {
	
	public final Vec3 colorOdd;
	public final Vec3 colorEven;
	public final double TILE_SIZE;
	
	// TILE_SIZE [0...1]
	public ChessTexture(Vec3 colorOdd, Vec3 colorEven, double TILE_SIZE) {
		this.colorOdd = colorOdd;
		this.colorEven = colorEven;
		this.TILE_SIZE = TILE_SIZE;
	}
	
	@Override
	public Vec3 color(Vec3 uv) {
		int x = (int)(uv.x * 1000000);
		int y = (int)(uv.y * 1000000);
		int tile = (int)(TILE_SIZE * 1000000);
		boolean evenY = (y / tile) % 2 == 0;
    	boolean evenX = (x / tile) % 2 == 0;
    	boolean oddX = (x / tile) % 2 == 1;    	
    	if (evenY) {
    		if (evenX) {
    			return colorEven;
    		} else {
                return colorOdd;
    		}
    	} else {
    		if (oddX) {
    			return colorEven;
    		} else {
                return colorOdd;
    		}
    	}
	}

}
