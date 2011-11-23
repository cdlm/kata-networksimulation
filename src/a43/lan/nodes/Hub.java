package a43.lan.nodes;

import a43.lan.core.*;

public class Hub extends Node {

	public Hub(String name) {
		super(name);
	}

	public void receiveVia(Link in, Packet p) {
		if (p.isAddressedTo(this)) {
			throw new RuntimeException("Erreur: Paquet adressé à un hub!");
		} else {
			forwardFrom(in, p);
		}
	}

	protected void forwardFrom(Link in, Packet p) {
		System.out.printf("%s: diffusion de «%s»\n", name, p.getPayload());
		for (Link l : outgoing) {
			sendVia(l, p);
		}
	}

}
