
package gen;

import java.util.Collections;
import java.util.Iterator;

public class GetSecondNearestGazelleDistance extends Node {

	public GetSecondNearestGazelleDistance(Animal animal) {
		super(animal);
	}

	public GetSecondNearestGazelleDistance(Animal animal, Serengeti sgt) {
		super(animal);
		
		data = "nearest gazelle";
		ch = 1;
		this.sgt = sgt;
	}

	public double preorder() {
		Iterator<Gazelle> itgr = sgt.gazelleSet.iterator();

		while (itgr.hasNext()) {
			Gazelle g1;
			g1 = itgr.next();
			g1.vectorToPrey = Math.hypot(g1.AP_x - animal.AP_x, g1.AP_y
					- animal.AP_y);
		}
		Collections.sort(sgt.gazelles, new Gazelle.CompareAnimal());
		int y;

		y =1;
		
		
		if ((y < sgt.gazelles.size())&&(y>0)) {
			if ((sgt.gazelles.get(y).vectorToPrey < sgt.vzdialenostViditelnosti)) {

				setValue(sgt.gazelles.get(y).vectorToPrey);
				System.out.println("....................................................................................."+sgt.gazelles.get(y));
			} else
				setValue(Math.random()*1000+sgt.vzdialenostViditelnosti);
		} else
			setValue(Math.random()*1000+sgt.vzdialenostViditelnosti);

		return value;

	}

}
