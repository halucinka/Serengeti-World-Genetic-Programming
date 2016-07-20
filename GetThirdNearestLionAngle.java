package gen;

import java.util.Collections;
import java.util.Iterator;

public class GetThirdNearestLionAngle extends Node {

	public GetThirdNearestLionAngle(Animal animal) {
		super(animal);
	}

	public GetThirdNearestLionAngle(Animal animal, Serengeti sgt) {
		super(animal);
		
		data =  "nearest lion";
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

				setValue(Math.atan((sgt.lions.get(y).AP_y - animal.AP_y)
						/ (sgt.lions.get(y).AP_y - animal.AP_y)));
			} else
				setValue(Math.random() * Math.PI * 2);
		} else
			setValue(Math.random() * Math.PI * 2);

		return value;

	}
}
