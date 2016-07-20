package gen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.Set;

public class MyCanvas extends Canvas {

	public MyCanvas() {
		setBackground(Color.yellow);

	}

	Set<Grass> grassSet;
	Set<Lion> lionSet;
	Set<Gazelle> gazelleSet;

	public void paint(Graphics g) {

		// System.out.println("kresliiiim");
		Iterator<Grass> itGr = grassSet.iterator();
		// System.out.println(grassSet.size());
		Grass grass;
		while (itGr.hasNext()) {
			grass = itGr.next();
			int f = (int) (grass.energy);
			if (f > 255)
				f = 0;
			else
				f = 255 - f;

			g.setColor(new Color(f, 255, f));

			g.fillRect((int) grass.GP_x, (int) grass.GP_y, 5, 5);
		}

		Iterator<Gazelle> itGa = gazelleSet.iterator();
		Gazelle gazelle;

		while (itGa.hasNext()) {
			gazelle = itGa.next();
			int f = (int) (gazelle.energy);
			if (f > 255)
				f = 0;
			else
				f = 255 - f;
			g.setColor(new Color(255, f, f));
			g.fillRect((int) gazelle.AP_x, (int) gazelle.AP_y, 5, 5);

		}

		Iterator<Lion> itL = lionSet.iterator();
		Lion lion;
		while (itL.hasNext()) {
			lion = itL.next();
			int f = (int) (lion.energy);
			if (f > 255)
				f = 0;
			else
				f = 255 - f;
			g.setColor(new Color(f, f, 255));

			g.fillRect((int) lion.AP_x, (int) lion.AP_y, 5, 5);
		}

	}

	public void setObjects(Set<Grass> grass, Set<Lion> lion,
			Set<Gazelle> gazelle) {
		this.grassSet = grass;
		this.lionSet = lion;
		this.gazelleSet = gazelle;
	}

}