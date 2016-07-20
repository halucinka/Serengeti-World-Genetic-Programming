package gen;

class Subtraction extends Node {
	
	public Subtraction(Animal animal){
		super(animal);
	}
	public Subtraction(Animal animal,Node left, Node right) {
		super(animal);
		addChild(left);
		addChild(right);
		data = "-";
		ch = 2;
	}

	public double preorder() {
		if (getNumberOfChildren() > 0) {
			setValue((children.get(0).preorder() - children.get(1).preorder()));

		}
		return value;
	}

}