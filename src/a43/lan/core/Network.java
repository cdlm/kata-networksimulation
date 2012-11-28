package a43.lan.core;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Network {
	protected Set<Node> nodes;
	protected Set<Link> links;

	public Network() {
		nodes = new HashSet<Node>();
		links = new HashSet<Link>();
	}

	public void addNode(Node n) {
		nodes.add(n);
	}

	public void connect(Node n1, Node n2) {
		if (n1 == n2) {
			throw new IllegalArgumentException("Connexion d'un nœud à lui-même");
		}
		links.add(this.makeLink(n1, n2));
		links.add(this.makeLink(n2, n1));
	}

	public void disconnect(Node n1, Node n2) {
		List<Link> toRemove = new LinkedList<Link>();
		for (Link l : links) {
			if (l.isBetween(n1, n2)) {
				toRemove.add(l);
			}
		}
		for (Link l : toRemove) {
			links.remove(l);
			l.detach();
		}
	}

	protected Link makeLink(Node from, Node to) {
		return new Link(from, to);
	}

	public Collection<Node> nodes() { return nodes; }
	public Collection<Link> links() { return links; }
}
