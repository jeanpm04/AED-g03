package lab9;

import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;
    protected int idNode;
    protected static int idCounter = 0;

    public BNode(int n) {
        this.keys = new ArrayList<E>(n);
        this.childs = new ArrayList<BNode<E>>(n);
        this.count = 0;
        for (int i = 0; i < n; i++) {
            this.keys.add(null);
            this.childs.add(null);
        }
        this.idNode = idCounter++;
    }

    public boolean nodeFull(int nEle) {
        return count == nEle;
    }

    public boolean nodeEmpty(int nEle) {
        return count < (nEle / 2);
    }

    public String toString() {
        String s = "nodo" + idNode + " : ";
        for (int i = 0; i < count; i++) {
            s += keys.get(i) + ", ";
        }
        s += "\n";
        return s;
    }

    public boolean searchNode(E cl, int[] pos) {
        pos[0] = 0;
        while (pos[0] < count && keys.get(pos[0]).compareTo(cl) < 0) {
            pos[0]++;
        }
        if (pos[0] == count) {
            return false;
        }
        return cl.equals(keys.get(pos[0]));
    }
}

