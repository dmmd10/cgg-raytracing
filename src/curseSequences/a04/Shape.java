package curseSequences.a04;

public interface Shape {
	public final double T_MIN = 0;
	public final double T_MAX = Double.POSITIVE_INFINITY;
	public Hit intersect(Ray ray);
}
