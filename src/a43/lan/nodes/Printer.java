package a43.lan.nodes;

import a43.lan.core.*;

public class Printer extends Node {

	protected int paperStock;
	
	public Printer(String name, int initialPaperStock) {
		super(name);
		paperStock = initialPaperStock;
	}
	
	public int paperStock() {
		return paperStock;
	}

	public void consume(Packet p) {
		super.consume(p);
		print(p);
	}
	
	protected void print(Packet p) {
		if (paperStock > 0) {
			System.out.printf("%s: impression de «%s»\n", name, p.getPayload());
			paperStock--;
		} else {
			System.out.printf("%s: bac vide!\n", name);
		}
	}

}
