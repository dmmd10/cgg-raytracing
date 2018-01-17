package curseSequences.a12.materials;

import java.io.IOException;

import cgtools.ImageTexture;
import cgtools.Vec3;

public class PicTexture implements Texture {
	
	public final String filename;
	protected ImageTexture imgTex;

	public PicTexture(String filename) {
		this.filename = filename;
		imgTex = null;
		try {
			imgTex = new ImageTexture(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Vec3 color(Vec3 uv) {
		return imgTex.samplePoint(uv.x, uv.y);
	}
}
