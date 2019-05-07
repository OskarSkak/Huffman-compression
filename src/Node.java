import Heap.Element;

public class Node {
    private Node left, right, parent;
    private int freq, spot;

    public Node(){}

    public Node(int freq){
        this.freq = freq;
    }

    public Node(int freq, int spot){
        this.freq = freq;
        this.spot = spot;
    }

    public void setFrequency(int freq){
        this.freq = freq;
    }
    public int getFrequency(){
        return this.freq;
    }

    public void setSpot(int spot){
        this.spot = spot;
    }
    public int getSpot(){
        return this.spot;
    }


    public Node getLeft(){
        return left;
    }
    public Node getRight(){
        return right;
    }

    public Node getParent(){
        return  this.parent;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public void setLeft(Node left){
        this.left = left;
        this.left.setParent(this);
    }

    public void setRight(Node right){
        this.right = right;
        this.right.setParent(this);
    }


}
