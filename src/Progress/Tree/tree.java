package Progress.Tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class tree<T> {

    private T head;

    private List<tree<T>> leafs = new ArrayList<tree<T>>();

    private tree<T> parent;

    private HashMap<T, tree<T>> locate = new HashMap<T, tree<T>>();

    public tree(T head) {
        this.head = head;
        locate.put(head, this);
    }

    public void addLeaf(T root, T leaf) {
        if (locate.containsKey(root)) {
            locate.get(root).addLeaf(leaf);
        } else {
            addLeaf(root).addLeaf(leaf);
        }
    }

    public tree<T> addLeaf(T leaf) {
        tree<T> t = new tree<T>(leaf);
        leafs.add(t);
        t.parent = this;
        t.locate = this.locate;
        locate.put(leaf, t);
        return t;
    }

    public tree<T> setAsParent(T parentRoot) {
        tree<T> t = new tree<T>(parentRoot);
        t.leafs.add(this);
        this.parent = t;
        t.locate = this.locate;
        t.locate.put(head, this);
        t.locate.put(parentRoot, t);
        return t;
    }

    public T getHead() {
        return head;
    }

    public tree<T> getTree(T element) {
        return locate.get(element);
    }

    public tree<T> getParent() {
        return parent;
    }

    public Collection<T> getSuccessors(T root) {
        Collection<T> successors = new ArrayList<T>();
        tree<T> t = getTree(root);
        if (null != t) {
            for (tree<T> leaf : t.leafs) {
                successors.add(leaf.head);
            }
        }
        return successors;
    }

    public Collection<tree<T>> getSubTrees() {
        return leafs;
    }

    public static <T> Collection<T> getSuccessors(T of, Collection<tree<T>> in) {
        for (tree<T> t : in) {
            if (t.locate.containsKey(of)) {
                return t.getSuccessors(of);
            }
        }
        return new ArrayList<T>();
    }

    @Override
    public String toString() {
        return printTree(0);
    }

    private static final int indent = 1;

    private String printTree(int increment) {
        String s = "";
        String inc = "";
        for (int i = 0; i < increment; ++i) {
            inc = inc + " ";
        }
        s = inc + head;
        for (tree<T> child : leafs) {
            s += "\n" + child.printTree(increment + indent);
        }
        return s;
    }
}
