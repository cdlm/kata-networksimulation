package a43.lan.nodes;

import a43.lan.core.Node;
import a43.lan.core.Packet;

public class Workstation extends Node {

	protected int nbConsumed;

	public Workstation(String name) {
		super(name);
	}

	public void consume(Packet p) {
		super.consume(p);
		nbConsumed++;
		System.out.printf("%s: working on task %d «%s»\n", name, nbConsumed, p.getPayload());
	}

	public int getNbConsumed() {
		return nbConsumed;
	}
}
