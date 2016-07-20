package gen;

import java.util.ArrayList;
import java.util.List;

public class Tree {

	public Node rootElement;
	public ArrayList<Node> AllNodes;

	public Tree() {
		super();
		AllNodes = new ArrayList<Node>();
	}

	public Node getRootElement() {
		return this.rootElement;
	}

	public void setRootElement(Node rootElement) {
		this.rootElement = rootElement;
	}

	public List<Node> toList() {
		List<Node> list = new ArrayList<Node>();
		walk(rootElement, list);
		return list;
	}

	public String toString() {
		return toList().toString();
	}

	private void walk(Node element, List<Node> list) {
		list.add(element);
		for (Node value : element.getChildren()) {
			walk(value, list);
			System.out.println(value);
		}
	}

	public void walkAndAddAll(Node node) {
		
//		System.out.println(node.value);
		AllNodes.add(node);
		
		for (Node n : node.getChildren()) {
			walkAndAddAll(n);
		}
	}

	public void AllNodesInTree() {
		AllNodes.clear();
		walkAndAddAll(rootElement);
	}

	public Tree clone(Animal animal){
		Tree t=new Tree();
		t.rootElement=this.rootElement.clone(animal);
		t.AllNodesInTree();
		
		return t;
	}
	
}
