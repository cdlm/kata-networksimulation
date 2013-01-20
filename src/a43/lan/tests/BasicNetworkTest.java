package a43.lan.tests;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import a43.lan.core.*;
import a43.lan.nodes.Hub;
import a43.lan.nodes.Printer;

public class BasicNetworkTest {

	protected Network net;
	protected Node mac, pc1, pc2, impr, hub, alone;

	/**
	 * Accesseur pour réutiliser le réseau construit dans d'autres classes de
	 * test
	 */
	public Network network() {
		if (net == null) {
			buildNetwork();
		}
		return net;
	}

	/** Construit le réseau en étoile de l'énoncé */
	public void buildNetwork() {
		// {{{ task
		// un nœud seul, hors du réseau
		alone = new Node("isolé");

		net = new Network();

		hub = new Hub("hub");
		net.addNode(hub);

		mac = new Node("Mac");
		net.addNode(mac);
		net.connect(mac, hub);

		pc1 = new Node("PC1");
		net.addNode(pc1);
		net.connect(pc1, hub);

		pc2 = new Node("PC2");
		net.addNode(pc2);
		net.connect(pc2, hub);

		impr = new Printer("impr", 3);
		net.addNode(impr);
		net.connect(impr, hub);
		// }}}
	}

	/*
	 * Rien n'empêche de mettre une méthode main() dans une classe de tests
	 * unitaires :
	 * 
	 * - pour lancer le main(): Run as… > Java Application
	 * 
	 * - pour lancer les tests : Run as… > JUnit Tests
	 */
	public static void main(String[] args) {
		// {{{ task }}}
	}

	@Before
	public void setUp() throws Exception {
		buildNetwork();
	}

	@Test
	public void testNetworkFindsNodesByName() {
		Node n1 = net.getNodeNamed("Mac");
		Node n2 = net.getNodeNamed("PC1");

		assertNotNull(n1);
		assertNotNull(n2);
	}

	@Test(expected = NoSuchElementException.class)
	public void testAloneNotFoundInNetwork() {
		net.getNodeNamed("alone");
	}

	@Test
	public void testScenarioSelfSend() {
		// un nœud peut s'envoyer des paquets,
		// même sans être dans un réseau (local loopback)
		Packet p = alone.originatePacket(alone, "Hello myself!");

		assertTrue(p.isAddressedTo(alone));
		assertTrue(p.originatesFrom(alone));
		assertTrue(p.wasReceived());
	}

	@Test
	public void testScenarioHelloMacPc() {
		Packet p1 = mac.originatePacket(pc1, "Hi, I'm a Mac!");
		Packet p2 = pc1.originatePacket(mac, "And I'm a PC.");

		assertTrue(p1.isAddressedTo(pc1));
		assertTrue(p1.originatesFrom(mac));
		assertTrue(p1.wasReceived());

		assertTrue(p2.isAddressedTo(mac));
		assertTrue(p2.originatesFrom(pc1));
		assertTrue(p2.wasReceived());
	}

	@Test
	public void testScenarioLprDisconnected() {
		net.disconnect(hub, impr);
		Packet p = mac.originatePacket(impr, "Document à imprimer");

		assertTrue(p.isAddressedTo(impr));
		assertTrue(p.originatesFrom(mac));
		assertFalse(p.wasReceived());
	}

	@Test
	public void testScenarioLprConnected() {
		Packet p = mac.originatePacket(impr, "Document à imprimer");

		assertTrue(p.isAddressedTo(impr));
		assertTrue(p.originatesFrom(mac));
		assertTrue(p.wasReceived());
		assertEquals(((Printer) impr).paperStock(), 2);
	}

}
