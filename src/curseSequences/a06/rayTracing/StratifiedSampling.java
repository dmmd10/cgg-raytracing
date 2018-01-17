package curseSequences.a06.rayTracing;

import cgtools.Random;
import static cgtools.Vec3.*;
import cgtools.Vec3;

public class StratifiedSampling implements Sampler {

	protected Sampler sampling;
	protected final int SAMPLE_MATRIX_SIZE;
	
	public StratifiedSampling(Sampler sampling, int sampleMatrixSize) {
		this.sampling = sampling;
		this.SAMPLE_MATRIX_SIZE = sampleMatrixSize;
	}

	@Override
	public Vec3 pixelColor(double x, double y) {
		Vec3 sampleColor = vec3(0);
    	int samplePoints = SAMPLE_MATRIX_SIZE * SAMPLE_MATRIX_SIZE;

    	for(int xi = 0; xi < SAMPLE_MATRIX_SIZE; xi++){
    		for(int yi = 0; yi < SAMPLE_MATRIX_SIZE; yi++){
    			double rx = Random.random();
    			double ry = Random.random();
    			double xs = x + (xi+rx) / SAMPLE_MATRIX_SIZE;
    			double ys = y + (yi+ry) / SAMPLE_MATRIX_SIZE;
    			sampleColor = add(sampleColor, sampling.pixelColor(xs, ys));
    		}
    	}
    	return divide(sampleColor, samplePoints);
	}
}
