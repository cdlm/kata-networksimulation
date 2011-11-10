package a43.lan;

public class Packet {

    Node source, destination;
    String payload;

    public Packet(Node source, Node destination, String payload) {
        this.source = source;
        this.destination = destination;
        this.payload = payload;
    }

    public String getPayload() {
		return payload;
	}

	public boolean isSentBy(Node n) {
        return source == n;
    }

    public boolean isAddressedTo(Node n) {
        return destination == n;
    }
}
