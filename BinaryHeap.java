import java.util.Arrays;

public class BinaryHeap{
    private static final int DEFAULT_CAPACITY = 10;
    protected int[] array;
    protected int size;
    
	public BinaryHeap () {
        array = new int[DEFAULT_CAPACITY];  
        size = 0;
    }
    
    
    public void add(int value) {
        // grow array if needed
        if (size >= array.length - 1) {
            array = this.resize();
        }        
        
        // place element into heap at bottom
        size++;
        int index = size;
        array[index] = value;
        
        bubbleUp();
    }
    
    
    public boolean isEmpty() {
        return size == 0;
    }

    
    /**
     * Returns (but does not remove) the minimum element in the heap.
     */
    public int peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        
        return array[1];
    }

    
    public int remove() {
    	int result = peek();
    	
    	array[1] = array[size];
    	array[size] = 0;
    	size--;
    	
    	bubbleDown();
    	
    	return result;
    }
    
    protected void bubbleDown() {
        int index = 1;
        
        // bubble down
        while (hasLeftChild(index)) {
            int smallerChild = leftIndex(index);
           
            if (hasRightChild(index)
                && array[leftIndex(index)] > (array[rightIndex(index)])) {
                smallerChild = rightIndex(index);
            } 
            
            if (array[index] > (array[smallerChild])) {
                swap(index, smallerChild);
            } else {
                break;
            }
            
            index = smallerChild;
        }        
    }
    
    protected void bubbleUp() {
        int index = this.size;
        
        while (hasParent(index)
                && (parent(index) > (array[index]))) {
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }        
    }
    
    
    protected boolean hasParent(int i) {
        return i > 1;
    }
    
    
    protected int leftIndex(int i) {
        return i * 2;
    }
    
    
    protected int rightIndex(int i) {
        return i * 2 + 1;
    }
    
    
    protected boolean hasLeftChild(int i) {
        return leftIndex(i) <= size;
    }
    
    
    protected boolean hasRightChild(int i) {
        return rightIndex(i) <= size;
    }
    
    
    protected int parent(int i) {
        return array[parentIndex(i)];
    }
    
    
    protected int parentIndex(int i) {
        return i / 2;
    }
    
    
    protected int[] resize() {
        return Arrays.copyOf(array, array.length * 2);
    }
    
    
    protected void swap(int index1, int index2) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;        
    }
}