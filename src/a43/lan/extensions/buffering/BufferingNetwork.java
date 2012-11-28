package a43.lan.extensions.buffering;

import a43.lan.core.Link;
import a43.lan.core.Network;
import a43.lan.core.Node;

public class BufferingNetwork extends Network {

	public void timePasses() {
		System.out.println("...tick...tock...");
		for (Link l : links) {
			((BufferingLink)l).tick();
		}
		for (Link l : links) {
			((BufferingLink)l).tock();
		}
	}

	protected Link makeLink(Node from, Node to) {
		return new BufferingLink(from, to);
	}
}
