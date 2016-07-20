
package gen;

import java.util.Collections;
import java.util.Iterator;

public class GetThirdNearestGrassAngle extends Node {

	public GetThirdNearestGrassAngle(Animal animal) {
		super(animal);
	}

	public GetThirdNearestGrassAngle(Animal animal, Serengeti sgt) {
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

				setValue(Math.atan((sgt.grasses.get(y).GP_y-animal.AP_y)/(sgt.grasses.get(y).GP_y-animal.AP_y)));
			} else
				setValue(Math.random()*Math.PI*2);
		} else
			setValue(Math.random()*Math.PI*2);

		return value;

	}
}
