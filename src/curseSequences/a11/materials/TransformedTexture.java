package curseSequences.a11.materials;

import cgtools.Mat4;
import cgtools.Vec3;

public class TransformedTexture implements Texture {
    public final Texture texture;
    public final Mat4 transform;

    public TransformedTexture(Texture texture, Mat4 transform) {
        this.texture = texture;
        this.transform = transform;
    }

    public Vec3 color(Vec3 uv) {
        return texture.color(transform.transformPoint(uv));
    }
}
