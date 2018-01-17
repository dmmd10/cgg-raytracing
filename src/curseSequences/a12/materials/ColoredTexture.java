package curseSequences.a12.materials;

import cgtools.Vec3;
import static cgtools.Vec3.*;

public class ColoredTexture implements Texture {

	public final Texture texture;
	public final Vec3 color;
	
	public ColoredTexture(Texture texture, Vec3 color) {
		this.texture = texture;
		this.color = color;
	}

	@Override
	public Vec3 color(Vec3 uv) {
		Vec3 imgTexture = texture.color(uv);
		double lum = 0.299 * imgTexture.x + 0.587 * imgTexture.y + 0.114 * imgTexture.z;
		return multiply(lum, color);
	}

}
