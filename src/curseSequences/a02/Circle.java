package curseSequences.a02;

import cgtools.Vec3;

public class Circle implements Comparable<Circle>{
	private int xCenter;
	private int yCenter;
	private Vec3 color;
	private double radius;
	
	public Circle(int xCenter, int yCenter, Vec3 color, double radius) {
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		this.color = color;
		this.radius = radius;
	}

	public boolean isPixelInsideCircle(double xSamplePoint, double ySamplePoint) {
		double dx = xCenter - xSamplePoint;
		double dy = yCenter - ySamplePoint;
		double distance = Math.sqrt(Math.pow(dx, 2)+ Math.pow(dy, 2));
		if (distance <= radius) {
			return true;
		} else {
			return false;
		}	
	}

	public double getRadius() {
		return radius;
	}
	
	public Vec3 getColor() {
		return color;
	}
	
	@Override
	public int compareTo(Circle other) {
		if (this.radius == other.radius){
			return 0;
		}
		if (this.radius > other.radius){
			return -1;
		} else {
			return 1;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circle other = (Circle) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		if (xCenter != other.xCenter)
			return false;
		if (yCenter != other.yCenter)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		long temp;
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + xCenter;
		result = prime * result + yCenter;
		return result;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Circle newCircle = new Circle(xCenter, yCenter, color, radius);
		return newCircle;
	}

	@Override
	public String toString() {
		return "Circle [xCenter=" + xCenter + ", yCenter=" + yCenter + ", color=" + color + ", radius=" + radius + "]";
	}
}
