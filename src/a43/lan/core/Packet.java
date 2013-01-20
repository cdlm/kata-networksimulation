package a43.lan.core;

public class Packet {

	protected String sourceName, destinationName;
	protected Object payload;
	protected boolean received;

	public Packet(Node source, Node destination, Object payload) {
		this(source.getName(), destination.getName(), payload);
	}

	public Packet(String srcName, String dstName, Object payload) {
		this.sourceName = srcName;
		this.destinationName = dstName;
		this.payload = payload;
		this.received = false;
	}

	public Object getPayload() {
		return payload;
	}

	public boolean originatesFrom(Node n) {
		return sourceName.equals(n.getName());
	}

	public boolean isAddressedTo(Node n) {
		return destinationName.equals(n.getName());
	}

	public void beReceived() {
		this.received = true;
	}

	public boolean wasReceived() {
		return received;
	}
	
	// {{{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	// }}}
}
