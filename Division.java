package gen;

class Division extends Node {

	public Division(Animal animal) {
		super(animal);
	}

	public Division(Animal animal, Node left, Node right) {
		super(animal);
		addChild(left);
		addChild(right);
		
		data = "/";
		ch=1;
	}

	@Override
	public double preorder() {
		if (getNumberOfChildren() > 0) {
			if (children.get(0).preorder()==0){
			setValue(0);
		} else
			setValue(children.get(0).preorder() / children.get(1).preorder());
		}
		return value;
	}

}