package a43.lan.core;

public class Link {
	protected Node from, to;

	public Link(Node from, Node to) {
		this.from = from;
		this.to = to;
		this.from.attach(this);
	}

	public boolean isBetween(Node from, Node to) {
		return this.from == from && this.to == to;
	}

	public void transmit(Packet p) {
		to.receiveVia(this, p);
	}

	public void detach() {
		from.detach(this);
	}
	
	public String toString() {
		return "Link(" + from.getName() + "->" + to.getName() + ")";
	}

	public Node source() { return from; }
	public Node destination() { return to; }
}