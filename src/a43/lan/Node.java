public class Node {
    String name;
    Link connection;

    public Node(String name) {
        this.name = name;
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
