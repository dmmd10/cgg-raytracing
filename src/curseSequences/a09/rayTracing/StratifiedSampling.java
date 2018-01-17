package curseSequences.a09.rayTracing;

import cgtools.Random;
import static cgtools.Vec3.*;
import cgtools.Vec3;

public class StratifiedSampling implements Sampler {

	protected Sampler sampling;
	protected final int SAMPLE_MATRIX_SIZE_X;
	protected final int SAMPLE_MATRIX_SIZE_Y;
	
	public StratifiedSampling(Sampler sampling, int sampleMatrixSize) {
		this.sampling = sampling;
		this.SAMPLE_MATRIX_SIZE_X = sampleMatrixSize;
		this.SAMPLE_MATRIX_SIZE_Y = sampleMatrixSize;
	}
	
	public StratifiedSampling(Sampler sampling, int sampleMatrixSizeX, int sampleMatrixSizeY) {
		this.sampling = sampling;
		this.SAMPLE_MATRIX_SIZE_X = sampleMatrixSizeX;
		this.SAMPLE_MATRIX_SIZE_Y = sampleMatrixSizeY;
		
	}

	@Override
	public Vec3 pixelColor(double x, double y) {
		Vec3 sampleColor = vec3(0);
    	int samplePoints = SAMPLE_MATRIX_SIZE_X * SAMPLE_MATRIX_SIZE_Y;

    	for(int xi = 0; xi < SAMPLE_MATRIX_SIZE_X; xi++){
    		for(int yi = 0; yi < SAMPLE_MATRIX_SIZE_Y; yi++){
    			double rx = Random.random();
    			double ry = Random.random();
    			double xs = x + (xi+rx) / SAMPLE_MATRIX_SIZE_X;
    			double ys = y + (yi+ry) / SAMPLE_MATRIX_SIZE_Y;
    			sampleColor = add(sampleColor, sampling.pixelColor(xs, ys));
    		}
    	}
    	return divide(sampleColor, samplePoints);
	}
}
