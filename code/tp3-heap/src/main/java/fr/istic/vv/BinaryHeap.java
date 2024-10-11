package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {

    private final ArrayList<T> heap; 
    private final Comparator<T> comparator; 

    public BinaryHeap(Comparator<T> comparator) {
        if (comparator == null) {
            throw new NullPointerException("Comparator cannot be null");
        }
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    public T pop() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T minElement = heap.get(0); 
        T lastElement = heap.remove(heap.size() - 1); 

        if (!heap.isEmpty()) {
            heap.set(0, lastElement); 
            heapifyDown(0); 
        }

        return minElement; 
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    public void push(T element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        heap.add(element); 
        heapifyUp(heap.size() - 1); 
    }

    public int count() {
        return heap.size(); 
    }

    // Helper method to maintain the heap property by pushing element upwards.
    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        T element = heap.get(index);

        while (index > 0 && comparator.compare(element, heap.get(parentIndex)) < 0) {
            heap.set(index, heap.get(parentIndex)); 
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
        heap.set(index, element); 
    }

    // Helper method to maintain the heap property by pushing element downwards.
    private void heapifyDown(int index) {
        int leftChildIndex;
        int rightChildIndex;
        int smallestChildIndex;

        while (index < heap.size()) { 
            leftChildIndex = 2 * index + 1;
            rightChildIndex = 2 * index + 2;
            smallestChildIndex = index;

            if (leftChildIndex < heap.size() && comparator.compare(heap.get(leftChildIndex), heap.get(smallestChildIndex)) < 0) { 
                smallestChildIndex = leftChildIndex;
            }

            if (rightChildIndex < heap.size() && comparator.compare(heap.get(rightChildIndex), heap.get(smallestChildIndex)) < 0) { 
                smallestChildIndex = rightChildIndex; 
            }

            if (smallestChildIndex == index) {
                break;
            }

            // Swap element at index with the smallest child.
            T temp = heap.get(index);
            heap.set(index, heap.get(smallestChildIndex));
            heap.set(smallestChildIndex, temp);

            index = smallestChildIndex; 
        }
    }
}
