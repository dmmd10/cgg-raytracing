package curseSequences.a04;

import java.util.Comparator;

public class HitComparator implements Comparator<Hit> {

	@Override
	public int compare(Hit hit1, Hit hit2) {
		return hit1.compareTo(hit2);
	}

}
