package gen;

import java.util.Collections;
import java.util.Iterator;

public class GetThirdNearestLionDistance extends Node {

	public GetThirdNearestLionDistance(Animal animal) {
		super(animal);
	}

	public GetThirdNearestLionDistance(Animal animal, Serengeti sgt) {
		super(animal);
	
		data = "nearest lion";
		ch = 1;
		this.sgt = sgt;
	}

	public double preorder() {
		Iterator<Lion> itgr = sgt.lionSet.iterator();

		while (itgr.hasNext()) {
			Lion g1;
			g1 = itgr.next();
			g1.vectorToPrey = Math.hypot(g1.AP_x - animal.AP_x, g1.AP_y
					- animal.AP_y);
		}
		Collections.sort(sgt.lions, new Lion.CompareAnimal());
		int y;

		y = 2;

		if ((y < sgt.lions.size())) {
			if ((sgt.lions.get(y).vectorToPrey < sgt.vzdialenostViditelnosti)) {

				setValue(sgt.lions.get(y).vectorToPrey);
			} else
				setValue(Math.random() * 1000 + sgt.vzdialenostViditelnosti);
		} else
			setValue(Math.random() * 1000 + sgt.vzdialenostViditelnosti);

		return value;

	}
}
