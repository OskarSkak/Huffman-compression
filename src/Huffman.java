import Heap.Element;
import Heap.PQ;
import Heap.PQHeap;

import java.util.ArrayList;

public class Huffman {
    PQHeap minHeap;
    Node[] elements;
    Node root;
    ArrayList<EncodedNode> encodedNodes;


    public Huffman(int[] frequeyncyArray){
        elements = new Node[frequeyncyArray.length];
        generateNodeArray(frequeyncyArray);
        this.encodedNodes = new ArrayList<>();
    }

    private void generateNodeArray(int[] frequencyArray){
        for(int i = 0; i < frequencyArray.length; i++){
            this.elements[i] = new Node(frequencyArray[i], i);
        }
    }

    public Node buildTree(){
        int n = elements.length;
        NodeHeap heap = new NodeHeap(n);

        for(int i = 0; i < n; i++){
            heap.insert(elements[i]);
        }

        for(int i = 0; i < n - 1; i++){
            Node z = new Node();
            Node x = heap.extractMin();
            z.setLeft(x);

            Node y = heap.extractMin();
            z.setRight(y);

            z.setFrequency(x.getFrequency() + y.getFrequency());

            heap.insert(z);
        }
        root = heap.extractMin();
        return root;
    }

    public String[] orderedTraversal(){
        ArrayList<EncodedNode> arrList = inorderTreeWalk(this.root);
        String[] stringArr = new String[256];

        for(EncodedNode eNode : arrList){
            stringArr[eNode.getNode().getSpot()] = eNode.getPath();
        }

        return stringArr;
    }

    private ArrayList<EncodedNode> inorderTreeWalk(Node x){
        if(x != null){
            inorderTreeWalk(x.getLeft());
            if(x.getLeft() == null && x.getRight() == null) {
                encodedNodes.add(this.getEncodedNode(x));
            }
            inorderTreeWalk(x.getRight());
        }
        return this.encodedNodes;
    }

    private EncodedNode getEncodedNode(Node x){
        EncodedNode e = new EncodedNode(x);

        while(x.getParent() != null) {
            Node parent = x.getParent();
            if (parent.getLeft() == x) e.addLeft();
            else e.addRight();

            x = parent;
        }
        return e;
    }


}

class EncodedNode{
    private String walk;
    private Node node;
    private String path;

    public Node getNode(){
        return this.node;
    }

    public  EncodedNode(Node node){
        walk = "";
        path = "";
        this.node = node;
    }

    public String getPath(){
        char[] arr = walk.toCharArray();
        for(int i = arr.length - 1; i > -1; i--){
            path += arr[i];
        }
        return path;
    }

    public void addRight(){
        walk += "1";
    }
    public void addLeft(){
        walk += "0";
    }
}