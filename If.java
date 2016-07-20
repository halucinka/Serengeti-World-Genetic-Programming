package gen;

public class If extends Node {

	public If(Animal animal) {
		super(animal);
	}

	public If(Animal animal, Node first, Node second, Node third, Node fourth) {
		super(animal);
		addChild(first);
		addChild(second);
		addChild(third);
		addChild(fourth);
		setData("If");
		ch = 4;
	}

	public double preorder() {
		if (getNumberOfChildren() > 0) {

			if ((children.get(0).preorder()) < (children.get(1).preorder())) {
				if (children.get(2) != null) {
					setValue(children.get(2).preorder());
				}
				// if (children.get(3) != null)
				// children.get(3).preorder();
			} else {
				if (children.get(3) != null)
					setValue(children.get(3).preorder());
				// if (children.get(2) != null)
				// children.get(2).preorder();
			}

		}
		return value;
	}
}
