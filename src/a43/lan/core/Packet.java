package a43.lan.core;

public class Packet {

	protected Node source, destination;
	protected String payload;
	protected boolean received;

	public Packet(Node source, Node destination, String payload) {
		this.source = source;
		this.destination = destination;
		this.payload = payload;
		this.received = false;
	}

	public String getPayload() {
		return payload;
	}

	public boolean originatesFrom(Node n) {
		return source == n;
	}

	public boolean isAddressedTo(Node n) {
		return destination == n;
	}

	public void beReceived() {
		this.received = true;
	}

	public boolean wasReceived() {
		return received;
	}
}
