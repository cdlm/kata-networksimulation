package a43.lan;

import java.util.ArrayList;
import java.util.Collection;

public class Network {
    Collection<Node> nodes;
    Collection<Link> links;

    public Network() {
        this.nodes = new ArrayList<Node>();
        this.links = new ArrayList<Link>();
    }

    public void addNode(Node n) {
        this.nodes.add(n);
    }

    public void connect(Node n1, Node n2) {
        if (n1 == n2) {
            throw new IllegalArgumentException("Connexion d'un nœud à lui-même");
        }
        Link l = new Link(n1, n2);
        n1.connect(l);
        n2.connect(l);
        this.links.add(l);
    }

    public void disconnect(Node n1, Node n2) {
        for (Link l : this.links) {
            if (l.isConnecting(n1, n2)) {
                this.links.remove(l);
                n1.disconnect(l);
                n2.disconnect(l);
                break;
            }
        }
    }

}
