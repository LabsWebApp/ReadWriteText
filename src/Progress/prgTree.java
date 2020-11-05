package Progress;

import Progress.Tree.tree;

import java.util.Arrays;

public class prgTree extends tree<prgEntity> {
    public prgTree() {
        super(new prgEntity());
    }
    public prgTree(long maximum) {
        super(new prgEntity(maximum));
    }
    public prgTree(long ... maximums) {
        super(new prgEntity(Arrays.stream(maximums).sum()));
        for (long maximum: maximums) {
            this.addLeaf(new prgEntity(maximum));
        }
    }

    public void addValue(long add) throws Exception{
        getHead().addValue(add);
    }

    public void setValue() throws Exception {
        getHead().setValue();
    }

    public double getCurrentPercent() throws Exception {
        for (var tree: this.getSubTrees()){
            addValue(tree.getHead().getValue());
        }
        return getHead().getCurrentPercent();
    }
//public String toString() {
//        return printTree(0, head);
//    }
    public String getStopwatches(){
        return printTree(0, getHead().getStopwatch());
    }
}
