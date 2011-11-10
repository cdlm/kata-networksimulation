public class Packet {

    Node source, destination;
    String payload;

    public Packet(Node source, Node destination) {
        this.source = source;
        this.destination = destination;
    }

    public boolean isSentBy(Node n) {
        return source.equal(n.getName());
    }

    public boolean isAddressedTo(Node n) {
        return destination.equal(n.getName());
    }
}
