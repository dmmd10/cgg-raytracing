package curseSequences.a10.materials;

import cgtools.Vec3;

public class ConstantTexture implements Texture {

	public final Vec3 color;
	
	public ConstantTexture(Vec3 color) {
		this.color = color;
	}

	@Override
	public Vec3 color(Vec3 uv) {
		return color;
	}

}
