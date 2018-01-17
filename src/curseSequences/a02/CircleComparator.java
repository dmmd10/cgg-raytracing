package curseSequences.a02;

import java.util.Comparator;

public class CircleComparator implements Comparator<Circle> {

	@Override
	public int compare(Circle c1, Circle c2) {
		return c1.compareTo(c2);
	}

}
