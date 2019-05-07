
package Heap;

public class PQHeap implements PQ {



    //********************************************** PROPERTIES *********************************************

    // The array containing the PriorityQueHeap
    public Element[] minHeap;

    //The size of the heap
    public int heapSize;


    //********************************************** CONSTRUCTOR *********************************************
    public PQHeap( int size) {
        this.minHeap = new Element[size];
        this.heapSize = 0;
    }

    /**
     *
     * @return The first element in the array which is the lowest in the priority que
     */
    @Override
    public Element extractMin() {
        // Getting the lowest value
        Element min = minHeap[0];
        // Setting the new root to the lowest leaf of the tree
        minHeap[0] = minHeap[heapSize-1];
        // Removing the lowest leaf
        minHeap[heapSize-1] = null;

        this.heapSize--;

        // Calling Heapify to maintain the min heap structure
        MinHeapify(minHeap , 0);
        return min;
    }



    //********************************************* METHODS *********************************************


    /**
     *
     * @param e the new element to insert
     */
    @Override
    public void insert(Element e) {
        // Increasing the size of the minHeap because a new element is inserted
        heapSize++;

        // setting the value i to heapsize - 1 because the array is 0 index
        int i = heapSize-1;
        // Inserting the new element into the last position
        this.minHeap[i] = e;
        // Checking if the parent is higher than the new element. Swapping the lowest element to the root
        while (i > 0  && minHeap[Parent(i)].getData() > minHeap[i].getData()) {
            swap(i, Parent(i));
            i = Parent(i);
        }


    }


    /**
     *
     * Perfoming the Heapify algorithm, which ensuring the MinHeap structure
     * @param heap the minHeap PriorityQue
     * @param i the index of the root
     *
     *
     */
    private void MinHeapify(Element[] heap, int i) {

        // The lowest value in the tree
        int lowest;
        int left = Left(i);
        int right = Right(i);

        // Checks if the nodes left child is lower
        if (left <= heapSize-1 && heap[left].getData() < heap[i].getData()) {
            lowest = left;
        } else {
            lowest = i;
        }

        // checks if the nodes right child is lower
        if ( right <= heapSize-1 && heap[right].getData() < heap[lowest].getData()) {
            lowest = right;
        }

        if (lowest != i) {
            swap(i,lowest);



            // Calling the minHeapify again with new values. Recursively ensuring the minHeap structure
            MinHeapify(heap,lowest);
        }
    }


    /*
    Return the nodes left, right and parent index

     */
    private int Left(int nodeIndex) {

        return 2 * nodeIndex +1;
    }

    private int Right (int nodeIndex) {
        return  2 * nodeIndex + 2;
    }

    private int Parent(int nodeIndex){
        if (nodeIndex % 2 ==1) {
            return nodeIndex / 2;
        }
        return (nodeIndex -1) / 2;
    }


    /**
     *
     * Helper method to swap two elements in the array
     * @param firstIndex the index of the first value
     * @param secondIndex the index of the second value
     */
    private void swap(int firstIndex , int secondIndex) {
        Element temp = this.minHeap[firstIndex];
        this.minHeap[firstIndex] = this.minHeap[secondIndex];
        this.minHeap[secondIndex] = temp;
    }
}
