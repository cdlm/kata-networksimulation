package a43.lan.test;

import a43.lan.core.*;

public class JustTwoNodes {

	public static void main(String[] args) {
		Node mac = new Node("Mac");
		Node pc = new Node("PC");
		
		// câble croisé = un lien dans chaque sens
		Link macToPc = new Link(mac, pc);
		Link pcToMac = new Link(pc, mac);
		
		mac.sendVia(macToPc, new Packet(mac, pc, "Hi, I'm a Mac!"));
		pc.sendVia(pcToMac, new Packet(pc, mac, "…and I'm a PC"));
	}

}
