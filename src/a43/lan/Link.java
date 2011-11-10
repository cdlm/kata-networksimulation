package a43.lan;

public class Link {
    Node n1, n2;

    public Link(Node n1, Node n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    public boolean isConnecting(Node n1, Node n2) {
		return (this.n1 == n1 && this.n2 == n2)
			|| (this.n1 == n2 && this.n2 == n1);
	}

	public void transmitFrom(Node emitter, Packet p) {
        if (emitter == n1) {
            n2.receiveVia(this, p);
        } else if (emitter == n2) {
            n1.receiveVia(this, p);
        } else {
            throw new IllegalArgumentException(
                    "Tentative de transmission depuis " +
                    emitter.getName() +
                    "non rattaché au lien " +
                    n1.getName() + "/" + n2.getName());
        }
    }
}
