package gen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Serengeti {
	public int[][] world;

	int width;
	int height;
	double vzdialenostViditelnosti;
	public ArrayList<Lion> lions;
	public ArrayList<Gazelle> gazelles;
	public ArrayList<Grass> grasses;
	Set<Grass> grassSet = new HashSet<Grass>();
	Set<Lion> lionSet = new HashSet<Lion>();
	Set<Gazelle> gazelleSet = new HashSet<Gazelle>();
	
	
	public int pocZacGaziel;
	public int pocZacLevov;
	public int pocTrav;
	public int energiaTrav;
	public int novagen;
	public int pravmut, pravrep;
	public Serengeti(int w, int h) {
		super();
		lions = new ArrayList<Lion>();
		gazelles = new ArrayList<Gazelle>();
		grasses = new ArrayList<Grass>();
		width = w;
		height = h;
	}

	public void startLions() {
		for (int j = 0; j < pocZacLevov; j++) {
			Lion lion = new Lion(this);
			Node root = new Node(lion);
			root = lion.randomNode(0);
			lion.tree.setRootElement(root);
			lionSet.add(lion);
			lions.add(lion);
		}
	}

	public void startGazelles() {
		for (int j = 0; j < pocZacGaziel; j++) {
			Gazelle gaz = new Gazelle(this);
			Node root = new Node(gaz);
			root = gaz.randomNode(0);
			gaz.tree.setRootElement(root);
			gazelleSet.add(gaz);
			gazelles.add(gaz);
		}
	}

	public void newGrass(Serengeti sgt, double spolocnaEnergy, int pocet) {
		Grass grass;
		for (int j = 0; j < pocet - 1; j++) {
			grass = new Grass(this);
			grass.energy = Math.random() * spolocnaEnergy;
			spolocnaEnergy = spolocnaEnergy - grass.energy;
			grass.GP_x = Math.random() * sgt.getWidth();
			grass.GP_y = Math.random() * sgt.getHeight();
			grasses.add(grass);
			grassSet.add(grass);
		}
		grass = new Grass(this);

		grass.energy = spolocnaEnergy;
		grass.GP_x = Math.random() * sgt.getWidth();
		grass.GP_y = Math.random() * sgt.getHeight();
		grasses.add(grass);
		grassSet.add(grass);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public List<Lion> getLions() {
		return this.lions;
	}

	public int getNumberOfLions() {
		return lions.size();
	}

	public void addLion(Lion lion) {
		lions.add(lion);
	}

	public void insertLionAt(int index, Lion lion)
			throws IndexOutOfBoundsException {
		if (index == getNumberOfLions()) {
			// this is really an append
			addLion(lion);
			return;
		} else {
			lions.get(index); // just to throw the exception, and stop here
			lions.add(index, lion);
		}
	}

	public void removeLionAt(int index) throws IndexOutOfBoundsException {
		lions.remove(index);
	}

	// pariace sa veci pre leva
	public Lion vyberParicaL(Set<Lion> setZverov, List<Lion> poleZverov) {
		// vybera najviac energicke zvery:)
		Iterator<Lion> iter = setZverov.iterator();
		double celkovaEnergy = 0;
		while (iter.hasNext()) {
			Lion lion;
			lion = iter.next();
			celkovaEnergy = celkovaEnergy + lion.energy;
//			System.out.println("energia leva je:" + lion.energy);
		}
		double sucetEnergy = 0;
		double rand;

		rand = Math.random() * celkovaEnergy;
//		System.out.println("celkovaEnergy.................................."
//				+ celkovaEnergy);
		iter = setZverov.iterator();
		Lion lion = null;

		while (iter.hasNext() && (sucetEnergy <= rand)) {

			lion = iter.next();
			sucetEnergy = sucetEnergy + lion.energy;
		}
		Lion paric = lion;
//		System.out.println("cha som pariiic" + paric);
		return paric;

	}

	public Lion vytvorDietaL(Lion paric1, Lion paric2, int n1, int n2) {
		// n1, n2 su indexy prislusnych node v All nodes parica 1 a parica 2
		Lion paric1temp = paric1.clone();
		Lion dieta = new Lion(this);
		if (paric1.tree.AllNodes.get(n1).father == null) {
			dieta.tree.rootElement = paric2.tree.rootElement.clone(dieta);
		} else {
			// parica1temp upravujem na dieta
			paric1temp.tree.AllNodes.get(n1).crossOverMe = true;

			int k = 0;
			while
			// (k<paric1.tree.AllNodes.get(n1).father.getNumberOfChildren();k++)&&
			(paric1temp.tree.AllNodes.get(n1).father.children.get(k).crossOverMe == false) {
				k++;
			}
			Node n;
			n = paric2.tree.AllNodes.get(n2).clone(dieta);
			n.father = paric1temp.tree.AllNodes.get(n1).father;
			paric1temp.tree.AllNodes.get(n1).father.setChild(k, n);
			dieta = paric1temp;
		}
//		System.out.println("vytvaram decko" + dieta);
		return dieta;
	}

	public Lion vygenerujPrijatelnePodstromyAVytvorDietaL(Lion paric1,
			Lion paric2) {
		int r1;
		int r2;
		Lion dieta = null;
		int prijate = 0;
		while (prijate == 0) {
			r1 = (int) (Math.random() * paric1.tree.AllNodes.size());
			r2 = (int) (Math.random() * paric2.tree.AllNodes.size());

			// paric 1 prijme (hociaky=nerozlisujem) MENSI podstom ako je ten
			// jeho,
			// ktory sa ide vymienat, s pravdepodobnostou pocet
			// vrcholov/maxvrcholov
			double prijatelnost;
			if (paric1.tree.AllNodes.get(r1).VelkostPodstromu < paric2.tree.AllNodes
					.get(r2).VelkostPodstromu) {
				prijatelnost = 1.2 - paric1.tree.rootElement.VelkostPodstromu
						/ paric1.maxn;
				double r = -1;
				r = Math.random();
				if (r < prijatelnost) {

					dieta = vytvorDietaL(paric1, paric2, r1, r2);
					prijate = 1;
				}
			} else {
				prijatelnost = 0.2+paric1.tree.rootElement.VelkostPodstromu
						/ paric1.maxn;
				double r;
				r = Math.random();

				if (r < prijatelnost) {

					dieta = vytvorDietaL(paric1, paric2, r1, r2);
					prijate = 1;
				}
			}
		}
		return dieta;
	}

	public void crossingOverL(Set<Lion> setZverov, List<Lion> poleZverov, int gene) {
		Lion paric1, paric2;
		Lion dieta = new Lion(this);

		paric1 = vyberParicaL(setZverov, poleZverov);
		paric2 = vyberParicaL(setZverov, poleZverov);
		paric1.tree.AllNodesInTree();
		paric2.tree.AllNodesInTree();
		dieta = vygenerujPrijatelnePodstromyAVytvorDietaL(paric1, paric2);
		dieta.energy = paric1.energy / 2 + paric2.energy / 2;
		paric1.energy = paric1.energy / 2;
		paric2.energy = paric2.energy / 2;
//		System.out.println("set levov ma velkost:" + setZverov.size());
		dieta.generacia=gene;
		setZverov.add(dieta);
//		System.out.println("set levov ma velkost:" + setZverov.size());
		poleZverov.add(dieta);
//		System.out.println("pole levov ma velkost:" + poleZverov.size());
	}

	// pariace sa veci pre gazelu
	public Gazelle vyberParicaG(Set<Gazelle> setZverov, List<Gazelle> poleZverov) {
		// vybera najviac energicke zvery:)
		Iterator<Gazelle> it = setZverov.iterator();
		double celkovaEnergy = 0;
		while (it.hasNext()) {
			Gazelle gaz;
			gaz = it.next();
			celkovaEnergy = celkovaEnergy + gaz.energy;
		}
		double sucetEnergy = 0;
		double rand;

		rand = Math.random() * celkovaEnergy;
		it = setZverov.iterator();
		Gazelle gaz = null;
		while (it.hasNext() && (sucetEnergy <= rand)) {

			gaz = it.next();
			sucetEnergy = sucetEnergy + gaz.energy;
		}
		Gazelle paric;
		paric = gaz;

		return (paric);

	}

	public Gazelle vytvorDietaG(Gazelle paric1, Gazelle paric2, int n1, int n2) {
		// n1, n2 su indexy prislusnych node v All nodes parica 1 a parica 2
		Gazelle paric1temp = paric1.clone();
		Gazelle dieta = new Gazelle(this);
		if (paric1.tree.AllNodes.get(n1).father == null) {
			dieta.tree.rootElement = paric2.tree.rootElement.clone(dieta);
		} else {
			// parica1temp upravujem na dieta
			paric1temp.tree.AllNodes.get(n1).crossOverMe = true;

			int k = 0;
			while
			// (k<paric1.tree.AllNodes.get(n1).father.getNumberOfChildren();k++)&&
			(paric1temp.tree.AllNodes.get(n1).father.children.get(k).crossOverMe == false) {
				k++;
			}
			Node n;
			n = paric2.tree.AllNodes.get(n2).clone(dieta);
			n.father = paric1temp.tree.AllNodes.get(n1).father;
			paric1temp.tree.AllNodes.get(n1).father.setChild(k, n);
			dieta = paric1temp;
		}
//		System.out.println("vytvaram decko" + dieta);
		return dieta;
	}

	public Gazelle vygenerujPrijatelnePodstromyAVytvorDietaG(Gazelle paric1,
			Gazelle paric2) {
		int r1;
		int r2;
		Gazelle dieta = null;
		int prijate = 0;
		while (prijate == 0) {
			r1 = (int) (Math.random() * paric1.tree.AllNodes.size());
			r2 = (int) (Math.random() * paric2.tree.AllNodes.size());
			
			// paric 1 prijme (hociaky=nerozlisujem) MENSI podstom ako je ten
			// jeho,
			// ktory sa ide vymienat, s pravdepodobnostou pocet
			// vrcholov/maxvrcholov
			double prijatelnost;
			if (paric1.tree.AllNodes.get(r1).VelkostPodstromu < paric2.tree.AllNodes
					.get(r2).VelkostPodstromu) {
				prijatelnost = 1.2-paric1.tree.rootElement.VelkostPodstromu
						/ paric1.maxn;
				double r = -1;
				r=Math.random();
				if (r < prijatelnost) {

					dieta = vytvorDietaG(paric1, paric2, r1, r2);
					prijate=1;
				}
			} else {
				prijatelnost = 0.2+paric1.tree.rootElement.VelkostPodstromu
						/ paric1.maxn;
				double r = -1;
				r=Math.random();
				if (r < prijatelnost) {

					dieta = vytvorDietaG(paric1, paric2, r1, r2);
					prijate=1;
				}
			}
		}
		return dieta;
	}

	public void crossingOverG(Set<Gazelle> setZverov, List<Gazelle> poleZverov, int gene) {
		Gazelle paric1, paric2;
		Gazelle dieta = new Gazelle(this);
		paric1 = vyberParicaG(setZverov, poleZverov);
		paric2 = vyberParicaG(setZverov, poleZverov);
		paric1.tree.AllNodes.clear();
		paric1.tree.AllNodesInTree();

		paric2.tree.AllNodes.clear();
		paric2.tree.AllNodesInTree();
		dieta = vygenerujPrijatelnePodstromyAVytvorDietaG(paric1, paric2);
		dieta.energy = paric1.energy / 2 + paric2.energy / 2;
		paric1.energy = paric1.energy / 2;
		paric2.energy = paric2.energy / 2;
		dieta.generacia=gene;
		setZverov.add(dieta);
		poleZverov.add(dieta);
	}

	
	
	
	public void reproductionL(Set<Lion> setZverov, List<Lion> poleZverov, int gene){
	Lion paric=null;
	paric=vyberParicaL(setZverov, poleZverov);
	Lion dieta=new Lion(this);
	dieta=paric.clone();
	dieta.energy=paric.energy/2;
	paric.energy=paric.energy/2;
	dieta.generacia=gene;
	setZverov.add(dieta);
	poleZverov.add(dieta);
		
		
		
	}
	
	public void reproductionG(Set<Gazelle> setZverov, List<Gazelle> poleZverov, int gene){
		Gazelle paric=null;
		paric=vyberParicaG(setZverov, poleZverov);
		Gazelle dieta=new Gazelle(this);
		dieta=paric.clone();
		dieta.energy=paric.energy/2;
		paric.energy=paric.energy/2;
		dieta.generacia=gene;
		setZverov.add(dieta);
		poleZverov.add(dieta);	
			
		}
	
	
	
	
	
	
	public void mutationG(Set<Gazelle> setZverov, List<Gazelle> poleZverov, int gene){
		Gazelle paric=null;
		paric=vyberParicaG(setZverov, poleZverov);
		Gazelle dieta=new Gazelle(this);
		dieta=paric.clone();
		dieta.tree.AllNodesInTree();
		int r;
		r=(int)(Math.random()*dieta.tree.AllNodes.size());
		Node node=new Node(dieta);
		Gazelle pomocdieta=new Gazelle(this);
		pomocdieta.maxn=dieta.tree.AllNodes.get(r).VelkostPodstromu;
		pomocdieta.tree.rootElement=dieta.randomNode(0);
		pomocdieta.tree.rootElement.father=dieta.tree.AllNodes.get(r).father;
		dieta.tree.AllNodes.set(r, pomocdieta.tree.rootElement);
		dieta.energy=paric.energy/2;
		paric.energy=paric.energy/2;
		dieta.generacia=gene;
		setZverov.add(dieta);
		poleZverov.add(dieta);	
			
		}
	
	
	public void mutationL(Set<Lion> setZverov, List<Lion> poleZverov, int gene){
		Lion paric=null;
		paric=vyberParicaL(setZverov, poleZverov);
		Lion dieta=new Lion(this);
		dieta=paric.clone();
		dieta.tree.AllNodesInTree();
		int r;
		r=(int)(Math.random()*dieta.tree.AllNodes.size());
		Node node=new Node(dieta);
		Lion pomocdieta=new Lion(this);
		pomocdieta.maxn=dieta.tree.AllNodes.get(r).VelkostPodstromu;
		pomocdieta.tree.rootElement=dieta.randomNode(0);
		pomocdieta.tree.rootElement.father=dieta.tree.AllNodes.get(r).father;
		dieta.tree.AllNodes.set(r, pomocdieta.tree.rootElement);
		dieta.energy=paric.energy/2;
		paric.energy=paric.energy/2;
		dieta.generacia=gene;
		setZverov.add(dieta);
		poleZverov.add(dieta);	
			
		}	
	
}
