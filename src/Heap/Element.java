
package Heap;

public class Element {

    private int key;
    private int data;

    public Element(int i, int o){
        this.key = i;
        this.data = o;
    }
    public int getKey(){
        return this.key;
    }
    public int getData(){
        return this.data;
    }
}