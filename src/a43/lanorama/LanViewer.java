package a43.lanorama;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import a43.lan.core.Link;
import a43.lan.core.Network;
import a43.lan.core.Node;

public class LanViewer {

	protected Network network;

	public LanViewer(Network network) {
		this.network = network;
	}

	protected void buildGraph(Graph g) {
		Map<Node,GraphNode> netToGraph = new HashMap<Node,GraphNode>();
		for (Node n : network.nodes()) {
			GraphNode gn = new GraphNode(g, SWT.NONE, n.toString());
			netToGraph.put(n, gn);
		}
		for (Link l : network.links()) {
			GraphNode n1 = netToGraph.get(l.source());
			GraphNode n2 = netToGraph.get(l.destination());
			new GraphConnection(g, SWT.NONE, n1, n2);
		}
	}

	public void open() {
		Display d = new Display();
		Shell shell = new Shell(d);

		shell.setText("Local Area Netweork simulation");
		shell.setLayout(new FillLayout());
		shell.setSize(600, 400);

		Graph g = new Graph(shell, SWT.NONE);
		buildGraph(g);
		g.setLayoutAlgorithm(new SpringLayoutAlgorithm(
				LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);

		shell.open();
		while (!shell.isDisposed()) {
			while (!d.readAndDispatch()) {
				d.sleep();
			}
		}
	}

	public static void show(Network network) {
		new LanViewer(network).open();
	}
}