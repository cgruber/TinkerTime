package aohara.tinkertime.models;

import java.util.Comparator;
import javax.inject.Inject;

public class ModComparator implements Comparator<Mod>{
  @Inject ModComparator() {}

	@Override
	public int compare(Mod o1, Mod o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
