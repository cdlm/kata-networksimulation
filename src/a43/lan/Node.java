package a43.lan;

import java.util.HashSet;
import java.util.Set;

public class Node {
	protected String name;
	protected Set<Link> connections;

	public Node(String name) {
		this.name = name;
		this.connections = new HashSet<Link>();
	}

	public String getName() {
		return name;
	}

	public void connect(Link l) {
		this.connections.add(l);
	}

	public void disconnect(Link l) {
		this.connections.remove(l);
	}

	public Packet originatePacket(Node destination, String payload) {
		Packet p = new Packet(this, destination, payload);
		for (Link l : connections) {
			this.sendVia(l, p);
			break; // envoi seulement par la 1re connexion trouvée
		}
		return p;
	}

	public void consume(Packet p) {
		// TODO
	}

	public void receiveVia(Link in, Packet p) {
		if (!connections.contains(in)) {
			throw new IllegalArgumentException(
					"Réception d'un paquet ne provenant pas d'un lien connecté");
		}
		if (p.isAddressedTo(this)) {
			p.beReceived();
			System.out.printf("%s: réception de «%s»\n", this.name,
					p.getPayload());
			this.consume(p);
		} else {
			// TODO
		}
	}

	public void sendVia(Link out, Packet p) {
		out.transmitFrom(this, p);
	}

}
