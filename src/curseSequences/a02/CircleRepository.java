package curseSequences.a02;

import java.util.ArrayList;
import java.util.List;

import cgtools.Random;
import cgtools.Vec3;

public class CircleRepository {
	
	private List<Circle> circleList;

	public CircleRepository(int numbersOfCircles, int width, int height, double minRadius, double maxRadius) {
		createCircles(numbersOfCircles, width, height, minRadius, maxRadius);
		sortCircleList();
	}
	
	protected void createCircles(int numbersOfCircles, int width, int height, double minRadius, double maxRadius){
		circleList = new ArrayList<Circle>();
		for (int i = 0; i < numbersOfCircles; i++) {
			int x = (int) (Random.random() * width);
			int y = (int) (Random.random() * height);
			double radius = minRadius + Random.random() * (maxRadius - minRadius);
			Vec3 color = new Vec3(Random.random(), Random.random(), Random.random());
			circleList.add(new Circle(x, y, color, radius));
		}
	}
	
	protected void sortCircleList() {
		circleList.sort(new CircleComparator());
	}

	public List<Circle> getCircleList() {
		return circleList;
	}
}
