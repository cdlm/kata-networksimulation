package a43.lan.core;

import java.util.HashSet;
import java.util.Set;

public class Node {
	protected String name;
	protected Set<Link> outgoing;

	public Node(String name) {
		this.name = name;
		this.outgoing = new HashSet<Link>();
	}

	public String getName() {
		return name;
	}

	public void attach(Link l) {
		this.outgoing.add(l);
	}

	public void detach(Link l) {
		this.outgoing.remove(l);
	}

	public Packet originatePacket(Node destination, String payload) {
		Packet p = new Packet(this, destination, payload);
		if (p.isAddressedTo(this)) {
			this.receiveVia(null, p);
		} else {
			for (Link l : outgoing) {
				this.sendVia(l, p);
				break; // envoi seulement par la 1re connexion trouvée
			}
		}
		return p;
	}

	public void sendVia(Link out, Packet p) {
		out.transmit(p);
	}

	public void receiveVia(Link in, Packet p) {
		System.out.printf("%s: réception de «%s»\n", this.name, p.getPayload());
		if (p.isAddressedTo(this)) {
			p.beReceived();
			this.consume(p);
		} else {
//			for (Link l : outgoing) {
//				this.sendVia(l,  p);
//			}
		}
	}

	public void consume(Packet p) {
	}

	public String toString() {
		return "[" + name + "]";
	}

}
