package a43.lan.experiment;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import a43.lan.core.*;

public class BufferingLink extends Link {

	protected Queue<Packet> buffer;
	protected Packet beingTransmitted;

	public BufferingLink(Node n1, Node n2) {
		super(n1, n2);
		buffer = new LinkedList<Packet>();
	}

	public void transmit(Packet p) {
		buffer.add(p);
	}

	public void tick() {
		beingTransmitted = buffer.poll();
	}

	public void tock() {
		if (beingTransmitted != null) {
			to.receiveVia(this, beingTransmitted);
		}
	}

	public String toString() {
		return "BufLink(" + from.getName() + "->" + to.getName() + ":"
				+ buffer.size() + ")";
	}

}
