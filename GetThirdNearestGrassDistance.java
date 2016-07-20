package gen;

import java.util.Collections;
import java.util.Iterator;

public class GetThirdNearestGrassDistance extends Node {

	public GetThirdNearestGrassDistance(Animal animal) {
		super(animal);
	}

	public GetThirdNearestGrassDistance(Animal animal, Serengeti sgt) {
		super(animal);
		data =  "nearest grass";
		ch = 1;
		this.sgt = sgt;
	}

	public double preorder() {
		Iterator<Grass> itgr = sgt.grassSet.iterator();

		while (itgr.hasNext()) {
			Grass g1;
			g1 = itgr.next();
			g1.vectorToPrey = Math.hypot(g1.GP_x - animal.AP_x, g1.GP_y
					- animal.AP_y);
		}
//		System.out.println("pocet trav"+sgt.grasses.size());
		Collections.sort(sgt.grasses, new Grass.CompareGrass());
		int y;

		y = 2;
		
		
		if ((y < sgt.grasses.size())) {
			if ((sgt.grasses.get(y).vectorToPrey < sgt.vzdialenostViditelnosti)) {

				setValue(sgt.grasses.get(y).vectorToPrey);
			} else
				setValue(Math.random()*1000+sgt.vzdialenostViditelnosti);
		} else
			setValue(Math.random()*1000+sgt.vzdialenostViditelnosti);

		return value;

	}
}
