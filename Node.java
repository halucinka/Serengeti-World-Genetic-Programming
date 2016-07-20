package gen;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public String data;
	public double value;
	public List<Node> children;
	public Integer ch = 0;
	public Node father;
	public int VelkostPodstromu = 1;
	public int fathersIndex;
	public boolean crossOverMe;
	public Animal animal;
	public Serengeti sgt;

	public Node(Animal animal) {
		super();
		children = new ArrayList<Node>();
		crossOverMe = false;
		this.animal=animal;
	}
	public Node(Animal animal, Integer value) {
		this(animal);
		setValue(value);
	}

	public Node clone(Animal animal) {

		Node no = new Node(animal);
		no.data = new String(this.data);
		no.ch = new Integer(this.ch);
		no.crossOverMe = this.crossOverMe;
		no.VelkostPodstromu = this.VelkostPodstromu;
		no.fathersIndex = this.fathersIndex;
		no.value = this.value;
		no.father = this.father;
		no.sgt = this.sgt;
		if (this.getNumberOfChildren() > 0) {
			for (int k = 0; k < this.getNumberOfChildren(); k++) {
				no.children.add(this.children.get(k).clone(animal));
				no.children.get(k).father = no;
			}
		}
		return no;
	}

	public List<Node> getChildren() {
		return this.children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
		for (int i = 0; i < children.size(); i++) {
			children.get(i).father = this;
			children.get(i).fathersIndex = i;
		}
	}

	public int getNumberOfChildren() {
		return children.size();
	}

	public void addChild(Node child) {
		child.father = this;
		child.fathersIndex = getNumberOfChildren();
		children.add(child);

	}

	public void setChild(int index, Node child)
			throws IndexOutOfBoundsException {
		if (index == getNumberOfChildren()) {
			// this is really an append
			addChild(child);
			return;
		} else {
			children.get(index); // just to throw the exception, and stop here
			children.remove(index);
			children.add(index, child);
		}
	}

	public void removeChildAt(int index) throws IndexOutOfBoundsException {
		children.remove(index);
	}

	public double getValue() {
		return this.value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getCh() {
		return this.ch;
	}

	public void setCh(int ch) {
		this.ch = ch;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	// public String toString() {
	// StringBuilder sb = new StringBuilder();
	// sb.append("{").append(getValue().toString()).append(",[");
	// int i = 0;
	// for (Node e : getChildren()) {
	// if (i > 0) {
	// sb.append(",");
	// }
	// sb.append(e.getValue().toString());
	// i++;
	// }
	// sb.append("]").append("}");
	// return sb.toString();
	// }

	public double preorder() {
		return value;
	};

}