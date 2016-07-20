package gen;

class Constant extends Node{

	public Constant(Animal animal) {
		super(animal);
	}

	public Constant(Animal animal, double value) {
		super(animal);
		setValue(value);
		setData("c");
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public double getValue() {
		return this.value;
	}
	
	


}
