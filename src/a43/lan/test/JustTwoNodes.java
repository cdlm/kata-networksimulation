package a43.lan.test;

import a43.lan.*;

public class JustTwoNodes {

	public static void main(String[] args) {
		Node mac = new Node("Mac");
		Node pc = new Node("PC");
		
		Link cableCroise = new Link(mac, pc);
		mac.connect(cableCroise);
		pc.connect(cableCroise);
		
		mac.sendVia(cableCroise, new Packet(mac, pc, "Hi, I'm a Mac!"));
		pc.sendVia(cableCroise, new Packet(pc, mac, "…and I'm a PC"));
	}

}
