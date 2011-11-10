package a43.lan;

public class Packet {

    Node source, destination;
    String payload;

    public Packet(Node source, Node destination) {
        this.source = source;
        this.destination = destination;
    }

    public String getPayload() {
		return payload;
	}

	public boolean isSentBy(Node n) {
        return source.equals(n.getName());
    }

    public boolean isAddressedTo(Node n) {
        return destination.equals(n.getName());
    }
}
