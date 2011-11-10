package a43.lan;

public class Node {
    String name;
    Link connection;

    public Node(String name) {
        this.name = name;
    }
    
	public String getName() {
		return name;
	}
	
    public void connect(Link l) {
		this.connection = l;
	}

	public void disconnect(Link l) {
		if (this.connection == l) { this.connection = null; }
	}

	public void receiveVia(Link l, Packet p) {
        if (l != connection) {
            throw new IllegalArgumentException("Réception d'un paquet ne provenant pas d'un lien connecté");
        }
        if (p.isAddressedTo(this)) {
            System.out.println(name + ": réception de " + p.getPayload());
            this.consume(p);
        }
    }

    public void consume(Packet p) {
        // TODO
    }

}
