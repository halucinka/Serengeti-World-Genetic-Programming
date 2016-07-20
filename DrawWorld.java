package gen;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class DrawWorld extends javax.swing.JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	{

		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	Set<Lion> LionSetPorovnavac = new HashSet<Lion>();
	int pocetRun = 0;
	int gene = 0;
	private JPanel jPanel1;
	private JButton RunButton;
	private MyCanvas canvas1;
	private JTextField TextSerengX;
	private JTextField TextSerengY;
	private JTextField TextViditelnost, TextPravMut, TextPravRep;
	private JTextField TextZacGazely, TextZacLevy, TextPocetTrav,
			TextEnergiaTrav, TextNovaGen;

	public Ellipse2D circle;
	public Graphics g;
	private Serengeti sgt;
	private Timer timer;
	private TimerTask task;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DrawWorld inst = new DrawWorld();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public DrawWorld() {
		super();
		initGUI();
	}

	public void vykresliAnimal() {
		// System.out.println("kreslim krajinku");
		canvas1.setObjects(sgt.grassSet, sgt.lionSet, sgt.gazelleSet);
		canvas1.repaint();
	}

	private void initGUI() {

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("DrawWorld");
		{

			jPanel1 = new JPanel();
			getContentPane().add(jPanel1, BorderLayout.EAST);
			jPanel1.setPreferredSize(new java.awt.Dimension(72, 302));
			{
				RunButton = new JButton();
				jPanel1.add(RunButton);
				RunButton.setText("Run");
				RunButton.setPreferredSize(new java.awt.Dimension(63, 21));
				RunButton.addActionListener(this);
			}

		}
		JLabel SerengX = new JLabel();
		JLabel SerengY = new JLabel();
		TextSerengX = new JTextField();
		TextSerengX.setText("500");
		TextSerengY = new JTextField();
		TextSerengY.setText("500");

		JLabel Viditelnost = new JLabel();
		TextViditelnost = new JTextField();
		TextViditelnost.setText("100");

		Viditelnost.setText("Viditelnost");

		SerengX.setText("Širka sveta");

		SerengY.setText("Výška sveta");
		jPanel1.add(SerengX);
		TextSerengX.setPreferredSize(new java.awt.Dimension(63, 21));
		jPanel1.add(TextSerengX);

		jPanel1.add(SerengY);
		TextSerengY.setPreferredSize(new java.awt.Dimension(63, 21));
		jPanel1.add(TextSerengY);
		jPanel1.add(Viditelnost);
		TextViditelnost.setPreferredSize(new java.awt.Dimension(63, 21));
		jPanel1.add(TextViditelnost);

		JLabel ZacGazely = new JLabel();
		JLabel ZacLevy = new JLabel();
		JLabel PocetTrav = new JLabel();
		JLabel EnergiaTrav = new JLabel();
		JLabel NovaGen = new JLabel();
		JLabel PravMut = new JLabel();
		JLabel PravRep = new JLabel();

		ZacGazely.setText("pocet zaè. gaziel");
		ZacLevy.setText("pocet zaè. levov");
		PocetTrav.setText("pocet trav");
		EnergiaTrav.setText("energia trav");
		NovaGen.setText("pocet jedincov novej generacie");
//		PravMut.setText("Pravdepodobnost mutacie");
//		PravRep.setText("Pravdepodobnost reprodukcie");
		TextPravMut = new JTextField();
		TextPravRep = new JTextField();
		TextZacGazely = new JTextField();
		TextZacLevy = new JTextField();
		TextPocetTrav = new JTextField();
		TextEnergiaTrav = new JTextField();
		TextNovaGen = new JTextField();
		TextZacGazely.setText("4");
		TextZacLevy.setText("4");
		TextPocetTrav.setText("10");
		TextEnergiaTrav.setText("500");
		TextNovaGen.setText("10");
		TextPravMut.setText("30");
		TextPravRep.setText("30");
		TextPravMut.setPreferredSize(new java.awt.Dimension(63, 21));
		TextPravRep.setPreferredSize(new java.awt.Dimension(63, 21));
		TextZacGazely.setPreferredSize(new java.awt.Dimension(63, 21));
		TextZacLevy.setPreferredSize(new java.awt.Dimension(63, 21));
		TextPocetTrav.setPreferredSize(new java.awt.Dimension(63, 21));
		TextEnergiaTrav.setPreferredSize(new java.awt.Dimension(63, 21));
		TextNovaGen.setPreferredSize(new java.awt.Dimension(63, 21));
		jPanel1.add(ZacGazely);
		jPanel1.add(TextZacGazely);
		jPanel1.add(ZacLevy);
		jPanel1.add(TextZacLevy);
		jPanel1.add(PocetTrav);
		jPanel1.add(TextPocetTrav);
		jPanel1.add(EnergiaTrav);
		jPanel1.add(TextEnergiaTrav);
		jPanel1.add(NovaGen);
		jPanel1.add(TextNovaGen);
//		jPanel1.add(PravMut);
//		jPanel1.add(TextPravMut);
//		jPanel1.add(PravRep);
//		jPanel1.add(TextPravRep);

		this.setSize(560, 500);

	}

	private int step = 0;

	private void doStep() {
		step++;

		Set<Lion> lionSet1;
		lionSet1 = sgt.lionSet;
		if (sgt.lionSet.size() == 0) {
			System.out.println("levy vymreli");
			step = 500;
		} else {

			Iterator<Lion> itL = lionSet1.iterator();
			while (itL.hasNext()) {
				Lion l1;
				l1 = itL.next();
				l1.newMove(sgt);
				if (l1.energy < 1) {
					sgt.gazelles.remove(l1);
					itL.remove();
				}

			}
		}
		Set<Gazelle> gazelleSet1;

		gazelleSet1 = sgt.gazelleSet;
		if (sgt.gazelleSet.size() == 0) {
			System.out.println("gazely vymreli");
			step = 500;
		} else {

			Iterator<Gazelle> itGa = gazelleSet1.iterator();
			while (itGa.hasNext()) {
				Gazelle ga;
				ga = itGa.next();
				ga.newMove(sgt);
				if (ga.energy < 1) {
					sgt.gazelles.remove(ga);
					itGa.remove();
				}
			}
		}

		// hybem levami a gazelami a pytam sa ci niekto nieco zozral z
		// coho
		// ma
		// energiu
		Iterator<Lion> itLo = lionSet1.iterator();
		while (itLo.hasNext()) {
			Lion l1;
			l1 = itLo.next();
			l1.nowMove();
			l1.IfLionEatGazelle();
		}

		Iterator<Gazelle> itGa = gazelleSet1.iterator();
		while (itGa.hasNext()) {
			Gazelle ga;
			ga = itGa.next();
			ga.nowMove();
			ga.IfGazelleEatGrass();
		}

		if (step % 10 == 0) {
			gene++;
			int nula=0;
			int jedna=0;
//			Iterator<Lion> itLion = lionSet1.iterator();
//			while (itLion.hasNext()) {
//				Lion l1;
//				l1 = itLion.next();
//				if (l1.porovnavac==0) nula++;
//				else jedna++;
//				
//			}
//			System.out.println (nula+ " "+ jedna);
			if (sgt.gazelleSet.size() > 0) {
				for (int k = 0; k < sgt.novagen; k++) {
					int ran;
					ran = (int) (Math.random() * 100);
					if (ran < 20) {
						sgt.mutationG(sgt.gazelleSet, sgt.gazelles, gene);
					} else if (ran < 80) {
						sgt.reproductionG(sgt.gazelleSet, sgt.gazelles, gene);
					} else {
						sgt.crossingOverG(sgt.gazelleSet, sgt.gazelles, gene);
					}
				}
			}
//			if (pocetRun!=3){
			if (sgt.lionSet.size() > 0) {
				for (int k = 0; k < sgt.novagen; k++) {
					int ran;
					ran = (int) (Math.random() * 100);
					if (ran < 20) {
						sgt.mutationL(sgt.lionSet, sgt.lions, gene);
					} else if (ran <80) {
						sgt.reproductionL(sgt.lionSet, sgt.lions, gene);
					} else {
						sgt.crossingOverL(sgt.lionSet, sgt.lions, gene);
					}
				}
			}
//			}
			sgt.newGrass(sgt, sgt.energiaTrav, sgt.pocTrav);

		}

		if (step == 500) {
			// tu skoncime
			int p=0;
//			if (pocetRun == 1) {
//				Iterator<Lion> itLi = sgt.lionSet.iterator();
//				p=0;
//				while (itLi.hasNext()) {
//					Lion l1;
//					l1 = itLi.next();
//					
//					if ((l1.generacia > gene-15)&&(p<30)) {
//						l1.porovnavac = 0;
//						LionSetPorovnavac.add(l1);
//						p++;
//					}
//				}
//			}
//			if (pocetRun == 2) {
//				Iterator<Lion> itLi = sgt.lionSet.iterator();
//				int q=0;
//				while (itLi.hasNext()) {
//					Lion l1;
//					l1 = itLi.next();
//					
//					if ((l1.generacia > gene-15)&&(q<30)) {
//						l1.porovnavac = 1;
//						q++;
//						LionSetPorovnavac.add(l1);
//					}
//				}
//			}
			timer.cancel();
			RunButton.enable();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (timer != null) {
			timer.cancel();
		}
		step = 0;

		// TODO Auto-generated method stub
		if (canvas1 == null) {
			canvas1 = new MyCanvas();
			getContentPane().add(canvas1, BorderLayout.CENTER);
		}
		RunButton.disable();
		// TODO Auto-generated method stub
		gene = 0;
		canvas1.setPreferredSize(new java.awt.Dimension(Integer
				.parseInt(TextSerengX.getText()), Integer.parseInt(TextSerengY
				.getText())));
		canvas1.setBackground(new java.awt.Color(255, 255, 255));
		this.setSize(new java.awt.Dimension(Integer.parseInt(TextSerengX
				.getText()) + 63, Integer.parseInt(TextSerengY.getText())));
		g = canvas1.getGraphics().create();
		sgt = new Serengeti(Integer.parseInt(TextSerengX.getText()),
				Integer.parseInt(TextSerengY.getText()));
		sgt.vzdialenostViditelnosti = Integer.parseInt(TextViditelnost
				.getText());
		sgt.pocZacGaziel = Integer.parseInt(TextZacGazely.getText());
		sgt.pocZacLevov = Integer.parseInt(TextZacLevy.getText());
		sgt.pocTrav = Integer.parseInt(TextPocetTrav.getText());
		sgt.energiaTrav = Integer.parseInt(TextEnergiaTrav.getText());
		sgt.novagen = Integer.parseInt(TextNovaGen.getText());
//		sgt.pravmut = Integer.parseInt(TextPravMut.getText());
//		sgt.pravrep = Integer.parseInt(TextPravRep.getText());
		pocetRun++;
//		System.out.println(pocetRun);
		if (pocetRun != 3) {
			sgt.startLions();
		} else {

			sgt.lionSet = LionSetPorovnavac;
			System.out.println(sgt.lionSet.size());
		}
		sgt.startGazelles();
		sgt.newGrass(sgt, sgt.energiaTrav, sgt.pocTrav);
		canvas1.setObjects(sgt.grassSet, sgt.lionSet, sgt.gazelleSet);
		step = 0;

		timer = new Timer();

		task = new TimerTask() {

			@Override
			public void run() {
				doStep();
				vykresliAnimal();
			}
		};

		timer.schedule(task, new Date(), 100);
	}
}
