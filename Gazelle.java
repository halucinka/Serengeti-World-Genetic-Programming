package gen;

import java.util.Iterator;

public class Gazelle extends Animal {

	public Gazelle(Serengeti sgt) {
		super(sgt);
		step = 3;
		maxn = 1000;
		maxd = 15;
		energy = 3000;
	}

	public void newMove(Serengeti sgt) {
		angle = tree.rootElement.preorder();

//		System.out.println("............gazella................................uhol:" + angle+ "cos" +Math.cos(angle));
		newAP_x = (AP_x + (Math.cos(angle) * step) % sgt.getWidth() + sgt
				.getWidth()) % sgt.getWidth();
		newAP_y = (AP_y + (Math.sin(angle) * step) % sgt.getHeight() + sgt
				.getHeight()) % sgt.getHeight();
		// System.out.println(this);
		// System.out.println("Nove AP su:"+AP_x+" "+AP_y);

	}

	public Gazelle clone() {
		Gazelle a = new Gazelle(sgt);
		a.step = this.step;
		a.maxd = this.maxd;
		a.maxn = this.maxn;
		a.n = this.n;
		a.pocetn = this.pocetn;
		a.tree = this.tree.clone(a);
		return a;

	}

	public void IfGazelleEatGrass() {
		Iterator<Grass> it = sgt.grassSet.iterator();
		Grass g1;
		while (it.hasNext()) {
			g1 = it.next();
			if (Math.hypot(g1.GP_x - this.AP_x, g1.GP_y - this.AP_y) < 10) {
//			System.out.println("Gazela zozrala travuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
				this.energy = energy + g1.energy;
				sgt.grasses.remove(g1);
				it.remove();
			}
		}
	}

	public Node randomNode(int d) {

		pocetn++;
		Node a = null;
		if ((d == maxd - 1) || (n + 4 > maxn - pocetn)) {
			d++;
			a = new Constant(this, (Math.random() * 10));
			// System.out.print("const lebo hlbka");
			// System.out.println(" " + a.getValue());
		} else {
			d++;
			int random = (int) (Math.random() * 12);
			switch (random) {
			case 0: {
				n = n + 1;
				Node b = new Node(this);
				Node c = new Node(this);
				// System.out.println("add");

				b = randomNode(d);
				n = n - 1;
				c = randomNode(d);

				a = new Addition(this, b, c);
				a.VelkostPodstromu = 1 + b.VelkostPodstromu
						+ c.VelkostPodstromu;
				b.father = a;
				c.father = a;
				;
				break;
			}
			case 1: {
				n = n + 1;
				Node b = new Node(this);
				Node c = new Node(this);
				// System.out.println("sub");

				b = randomNode(d);
				n = n - 1;
				c = randomNode(d);
				sgt.gazelleSet.remove(this);
				sgt.gazelles.remove(this);

				a = new Subtraction(this, b, c);
				a.VelkostPodstromu = 1 + b.VelkostPodstromu
						+ c.VelkostPodstromu;
				b.father = a;
				c.father = a;
				break;

			}
			case 2: {
				n = n + 1;
				Node b = new Node(this);
				Node c = new Node(this);
				// System.out.println("multip");

				b = randomNode(d);
				n = n - 1;
				c = randomNode(d);
				a = new Multiplication(this, b, c);
				a.VelkostPodstromu = 1 + b.VelkostPodstromu
						+ c.VelkostPodstromu;
				b.father = a;
				c.father = a;

				break;

			}
			case 3: {
				n = n + 1;
				Node c = new Node(this);
				Node b = new Node(this);
				// System.out.println("div");

				b = randomNode(d);
				n = n - 1;
				c = randomNode(d);

				a = new Division(this, b, c);
				a.VelkostPodstromu = 1 + b.VelkostPodstromu
						+ c.VelkostPodstromu;
				b.father = a;
				c.father = a;
				break;

			}

			case 4: {
				n = n + 3;
				Node b = new Node(this);
				Node c = new Node(this);
				Node e = new Node(this);
				Node f = new Node(this);
				// System.out.println("if");

				b = randomNode(d);
				n = n - 1;
				c = randomNode(d);
				n = n - 1;
				e = randomNode(d);
				n = n - 1;
				f = randomNode(d);
				a = new If(this, b, c, e, f);
				a.VelkostPodstromu = 1 + b.VelkostPodstromu
						+ c.VelkostPodstromu + e.VelkostPodstromu
						+ f.VelkostPodstromu;
				b.father = a;
				c.father = a;
				e.father = a;
				f.father = a;

				break;

			}
			case 5: {
				// System.out.print("const");
				a = new Constant(this, Math.random() * 23);
				d--;
				// System.out.println(" " + a.getValue());
				break;

			}
			case 6: {
				a = new GetFirstNearestLionAngle(this, sgt);
				break;
			}
			case 7: {
				a = new GetFirstNearestLionDistance(this, sgt);
				break;
			}
			case 8: {
				a = new GetFirstNearestGazelleAngle(this, sgt);
				break;
			}
			case 9: {
				a = new GetFirstNearestGazelleDistance(this, sgt);
				break;
			}
			case 10: {
				a = new GetSecondNearestLionAngle(this, sgt);
				break;
			}
			case 11: {
				a = new GetSecondNearestLionDistance(this, sgt);
				break;
			}
			case 12: {
				a = new GetSecondNearestGazelleAngle(this, sgt);
				break;
			}
			case 13: {
				a = new GetSecondNearestGazelleDistance(this, sgt);
				break;
			}	case 14: {
				a = new GetThirdNearestLionAngle(this, sgt);
				break;
			}
			case 15: {
				a = new GetThirdNearestLionDistance(this, sgt);
				break;
			}
			case 16: {
				a = new GetThirdNearestGazelleAngle(this, sgt);
				break;
			}
			case 17: {
				a = new GetThirdNearestGazelleDistance(this);
				break;
			}
			case 18: {
				a = new GetFirstNearestGrassAngle(this, sgt);
				break;
			}
			case 19: {
				a = new GetFirstNearestGrassDistance(this, sgt);
				break;
			}
			case 20: {
				a = new GetSecondNearestGrassAngle(this, sgt);
				break;
			}
			case 21: {
				a = new GetSecondNearestGrassDistance(this, sgt);
				break;
			}

			case 22: {
				a = new GetSecondNearestGrassAngle(this, sgt);
				break;
			}
			case 23: {
				a = new GetSecondNearestGrassDistance(this, sgt);
				break;
			}

			}
		}
		return a;

	}
}
