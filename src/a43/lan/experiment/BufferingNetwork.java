package a43.lan.experiment;

import a43.lan.core.Link;
import a43.lan.core.Network;
import a43.lan.core.Node;

public class BufferingNetwork extends Network {

	public void timePasses() {
		for (Link l : links) {
			((BufferingLink)l).timePasses();
		}
	}
	protected Link makeLink(Node from, Node to) {
		return new BufferingLink(from, to);
	}
}
