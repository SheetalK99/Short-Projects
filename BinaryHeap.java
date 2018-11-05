// Starter code for bounded-size Binary Heap implementation

package sak170006;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class BinaryHeap<T extends Comparable<? super T>> {
    T[] pq;
    Comparator<T> comp;
    int capacity = 10;
    int size = 0;

    //Constructor for building an empty priority queue using natural ordering of T
    public BinaryHeap(T[] q) {
        //Use a lambda expression to create comparator from compareTo

        this(q, (T a, T b) -> a.compareTo(b));
        System.out.println("default comparator");
    }

    // Constructor for building an empty priority queue with custom comparator
    public BinaryHeap(T[] q, Comparator<T> c) {
        System.out.println("comparator comparator");
        pq = q;
        comp = c;
        capacity = q.length;
        size = q.length;
        for (int i = capacity / 2 - 1; i >= 0; i--)
            buildHeap(capacity, i);  // builds heap from array passed by user
    }

    /** Build a priority queue with a given array q, using q[0..n-1].
     *  It is not necessary that n == q.length.
     *  Extra space available can be used to add new elements.
     *  Implement this if solving optional problem.  To be called from heap sort.
     */
    private BinaryHeap(T[] q, Comparator<T> c, int n) {
        pq = q;
        comp = c;
        capacity = q.length;
        size = n;
        for (int i = capacity  - 1; i >= 0; i--)
            buildHeap(n, i); // builds heap from array passed by user
    }
    public void add(T x) { /* throw exception if pq is full */
        if (size == capacity) {
            throw new ArithmeticException("Priority Queue is Full");
        }

        pq[size++] = x;
        percolateUp(size-1); //move x to appropriate position
    }

    public boolean offer(T x) { /* return false if pq is full */
        if (size == capacity) {
            return false;
        }

        pq[size++] = x;
        percolateUp(size-1);//move x to appropriate position
        return true;
    }

    public T remove() { /* throw exception if pq is empty */
        if (size == 0) {
            throw new ArithmeticException("Priority Queue is empty");
        }

        T data = pq[0]; // added new node at root
        pq[0] = pq[size - 1];
        size--;
        percolateDown(0); //move x to appropriate position
        return data;
    }

    public T poll() { /* return false if pq is empty */
        if (size == 0) {
           return null;
        }
        T data = pq[0]; // added new node at root
        pq[0] = pq[size - 1];
        size--;
        percolateDown(0); //move x to appropriate position
        return data;
    }

    public T peek() { /* return null if pq is empty */
        if (size == 0) {
            return null;
        }
        return pq[0];
    }

    /** pq[i] may violate heap order with parent */
    void percolateUp(int i) {
        T data = pq[i];
        // move up till new node is less than parent
        while (i > 0 && comp.compare(data, pq[parent(i)]) == -1) {
            pq[i] = pq[parent(i)];
            i  = parent(i);
        }
        pq[i] = data;
    }

    /** pq[i] may violate heap order with children */
    void percolateDown(int i) { 
        T data = pq[i];
        int child = leftChild(i);
        while(child < size) {
        	// compare new node with children
            if (child + 1 < size  && comp.compare(pq[child], pq[child+1]) == 1) {
                child = child + 1;
            }
            if (comp.compare(pq[child], data) == 1) {
                break;
            }
            pq[i] = pq[child];
            i  = child;
            child = leftChild(child); //keep moving left till approriate position is found
        }
        pq[i] = data;
    }

    // Assign x to pq[i].  Indexed heap will override this method
    void move(int i, T x) {
        pq[i] = x;
    }

    int parent(int i) {
        return (i-1)/2;
    }

    int leftChild(int i) {
        return 2*i + 1;
    }

    void swap(int i, int j) {
        T temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    void printHeap() {
        int n = size;
        for (int i=0; i<n; ++i)
            System.out.print(pq[i]+" ");
        System.out.println();
    }
// end of functions for team project




// start of optional problem: heap sort (Q2)

    /** Create a heap.  Precondition: none.
     *  Implement this ifsolving optional problem
     */
    

    void buildHeap(int n, int i) {
        int smallest = i;
        int leftChild = leftChild(i);
        int rightChild = leftChild(i) + 1;

         if(leftChild < n && comp.compare(pq[leftChild], pq[smallest]) == -1) {
            smallest = leftChild;
            
        }
         if (rightChild < n &&  comp.compare(pq[rightChild], pq[smallest]) == -1) {
            smallest = rightChild;
        }

        if (smallest != i) {
            swap(smallest, i);
            buildHeap(n, smallest);
        }
        
        i=smallest;
    }

    
    
    
    /* sort array arr[].
       Sorted order depends on comparator used to buid heap.
       min heap ==> descending order
       max heap ==> ascending order
       Implement this for optional problem
     */
    public static<T> void heapSort(T[] arr, Comparator<T> c) { /* to be implemented */
    }

    // Sort array using natural ordering
    public static<T extends Comparable<? super T>> void heapSort(T[] arr) {
        heapSort(arr, (T a, T b) -> a.compareTo(b));
    }
// end of optional problem: heap sort (Q2)



// start of optional problem: kth largest element (Q3)
    public void replace(T x) {
	/* TO DO.  Replaces root of binary heap by x if x has higher priority
	     (smaller) than root, and restore heap order.  Otherwise do nothing.
	   This operation is used in finding largest k elements in a stream.
	   Implement this if solving optional problem.
	 */
    }

    /** Return the kth largest element of stream using custom comparator.
     *  Assume that k is small enough to fit in memory, but the stream is arbitrarily large.
     *  If stream has less than k elements return null.
     */
    public static<T extends Comparable<? super T>> T kthLargest(Iterator<T> stream, int k, Comparator<T> c) {
        return null;
    }

    /** Return the kth largest element of stream using natural ordering.
     *  Assume that k is small enough to fit in memory, but the stream is arbitrarily large.
     *  If stream has less than k elements return null.
     */
    public static<T extends Comparable<? super T>> T kthLargest(Iterator<T> stream, int k) {
        return kthLargest(stream, k, (T a, T b) -> a.compareTo(b));
    }
// end of optional problem: kth largest element (Q3)

    public static void main(String args[]) {
        Integer[] arr = new Integer[10];
        arr[0] =  8;
        arr[1] = 7;
        arr[2] = 4;
        arr[3] = 2;
        arr[4] = 9;
        
        Integer[] arr2 = new Integer[10];
        // object for filled array with size parameter
        BinaryHeap<Integer> q = new BinaryHeap<>(arr, ((Integer a, Integer b) -> b.compareTo(a)), 5);
        q.printHeap();
     // object for empty array
        
        BinaryHeap<Integer> r = new BinaryHeap<>(arr2, ((Integer a, Integer b) -> a.compareTo(b)), 0);
        r.printHeap(); 
        Scanner in= new Scanner(System.in);
        whileloop:
        while(in.hasNext()) {
            int com = in.nextInt();
            switch(com) {
                case 1: //add
                    q.add(in.nextInt());
                    q.printHeap();
                    break;
                case 2: //remove
                    System.out.println(q.remove());
                    q.printHeap();
                    break;
                case 3: //add
                    q.offer(in.nextInt());
                    q.printHeap();
                    break;
                case 4: //remove
                    System.out.println("poll : " + q.poll());
                    q.printHeap();
                    break;
                case 5:
                    q.printHeap();
                    break;
                default:  // Exit loop
                    break whileloop;
            }
        }

    }

}
