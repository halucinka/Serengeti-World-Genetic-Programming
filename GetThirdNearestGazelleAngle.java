package gen;

	import java.util.Collections;
import java.util.Iterator;

	public class GetThirdNearestGazelleAngle extends Node {

		public GetThirdNearestGazelleAngle(Animal animal) {
			super(animal);
			// TODO Auto-generated constructor stub
		}

	

		public GetThirdNearestGazelleAngle(Animal animal, Serengeti sgt) {
			super(animal);
				data = "first nearest gazelle";
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

			y = 2;
			
			
			if ((y < sgt.gazelles.size())&&(y>0)) {
				if ((sgt.gazelles.get(0).vectorToPrey < sgt.vzdialenostViditelnosti)) {

					setValue(Math.atan((sgt.gazelles.get(y).AP_y-animal.AP_y)/(sgt.gazelles.get(y).AP_y-animal.AP_y)));
				} else
					setValue(Math.random()*Math.PI*2);
			} else
				setValue(Math.random()*Math.PI*2);

			return value;

		}
	}


