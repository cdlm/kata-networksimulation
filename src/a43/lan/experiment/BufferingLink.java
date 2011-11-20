package a43.lan.experiment;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import a43.lan.core.*;

public class BufferingLink extends Link {

	protected Queue<Packet> buffer;

	public BufferingLink(Node n1, Node n2) {
		super(n1, n2);
		buffer = new LinkedList<Packet>();
	}
	
	public void transmit(Packet p) {
		buffer.add(p);
	}
	
	public void timePasses() {
		try {
			Packet p = buffer.remove();
			to.receiveVia(this, p);
		} catch (NoSuchElementException e) {
			// it's ok, there is nothing to transmit
		}
		
	}
}
