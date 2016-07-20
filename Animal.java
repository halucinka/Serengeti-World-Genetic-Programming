package gen;

import java.util.Comparator;
import java.util.Random;

public class Animal implements Cloneable
 {
	public double angleToPrey, vectorToPrey; // distance from gazelle
	public double AP_x, AP_y; // position of
	public double newAP_x, newAP_y;
	public double angle; // move vector of (vector size is 1)
	public int maxd; // maximalna hlbka stromu
	public int step;
	public int maxn;
	public Tree tree;
	public Serengeti sgt;
	public int generacia=0;
	int n = 0;
	int pocetn = 0;
	public int porovnavac=0;
	public double energy;

	Animal(Serengeti sgt) {
		super();
//		random = new Random(System.currentTimeMillis());
//		double fuj;
//		fuj = random.nextDouble();
		AP_x = Math.random() * sgt.getWidth();
//		System.out.println("AP_x" + AP_x);

		AP_y = Math.random() * sgt.getHeight();
//		System.out.println("rnd " + random.nextDouble() + " "
//				+ random.nextDouble());

		this.sgt = sgt;
		tree = new Tree();
		// tree.rootElement=null;
		// Langle=0;
		// LGangle=0;
		// LGvector=7;

	}

	public void nowMove() {
		AP_x = newAP_x;
		AP_y = newAP_y;
//		System.out.println("NowMove" + AP_x + " " + AP_y);
		if (energy>10){
		energy=energy-1;}
		else {energy=0;
		}
//		pohyb stoji 1 energie

	}

	public boolean eatGazelle(Animal gaz) {
		distanceFromGazelle(gaz);
		if (vectorToPrey < 1) {
			return true;
		} else
			return false;
	}


	public void distanceFromGazelle(Animal gaz) {
		vectorToPrey = Math.hypot(AP_x - gaz.AP_x, AP_y - gaz.AP_y);
	}

	public static class CompareAnimal implements Comparator<Animal> {

		@Override
		public int compare(Animal animal1, Animal animal2) {

			if (animal1.vectorToPrey > animal2.vectorToPrey)
				return 1;
			else if (animal1.vectorToPrey == animal2.vectorToPrey)
				return 0;
			else
				return -1;
		}
	}
		

	public double getStep() {
		return step;
	}

	public double getLP_x() {
		return AP_x;
	}

	public double getLP_y() {
		return AP_y;
	}

	public void setLGangle(Animal gaz) {
		angleToPrey = Math.tan(Math.abs(gaz.AP_x - AP_x)
				/ Math.abs(gaz.AP_y - AP_y));

	}

	public void setLGvector(Animal gaz) {
		vectorToPrey = Math.hypot(gaz.AP_x - AP_x, gaz.AP_y - AP_y);
	}

	

}
