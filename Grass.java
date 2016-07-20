package gen;

import java.util.Comparator;

public class Grass {

	public static class CompareGrass implements Comparator<Grass> {

		@Override
		public int compare(Grass grass1, Grass grass2) {
			if (grass1.vectorToPrey > grass2.vectorToPrey)
				return 1;
			else if (grass1.vectorToPrey == grass2.vectorToPrey)
				return 0;
			else
				return -1;
		}
	}

	public double vectorToPrey;
	public double GP_x, GP_y;
	private Serengeti sgt;
	public double energy;

	public Grass(Serengeti sgt) {
		super();

		this.sgt = sgt;
	}
}