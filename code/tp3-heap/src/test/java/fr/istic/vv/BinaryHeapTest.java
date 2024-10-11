package fr.istic.vv;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.NoSuchElementException; 

class BinaryHeapTest {
    private BinaryHeap<Integer> heap;

    @BeforeEach
    void setUp() {
        // Initialize the BinaryHeap with a simple integer comparator
        heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
    }

    // Test pop on empty heap
    @Test
    void testPopOnEmptyHeap() {
        assertThrows(NoSuchElementException.class, () -> {
            heap.pop(); //rouge
        }); //rouge 
    }

    // Test peek on empty heap
    @Test
    void testPeekOnEmptyHeap() {
        assertThrows(NoSuchElementException.class, () -> {
            heap.peek(); //rouge
        }); //rouge
    }

    // Test pop on single element heap
    @Test
    void testPopOnSingleElementHeap() {
        heap.push(10);
        assertEquals(10, heap.pop());
        assertTrue(heap.count() == 0); // Heap should be empty now //jaune
    }

    // Test pop on multiple elements heap
    @Test
    void testPopOnMultipleElementsHeap() {
        heap.push(20);
        heap.push(10);
        heap.push(30);
        assertEquals(10, heap.pop()); // 10 is the minimum
        assertEquals(2, heap.count()); // 2 elements should remain
    }

    // Test peek on single element heap
    @Test
    void testPeekOnSingleElementHeap() {
        heap.push(15);
        assertEquals(15, heap.peek());
        assertEquals(1, heap.count()); // Should still count the element
    }

    // Test peek on multiple elements heap
    @Test
    void testPeekOnMultipleElementsHeap() {
        heap.push(50);
        heap.push(30);
        heap.push(20);
        assertEquals(20, heap.peek()); // Should return minimum
        assertEquals(3, heap.count()); // Count should remain the same
    }

    // Test push with valid element
    @Test
    void testPushValidElement() {
        heap.push(25);
        assertEquals(1, heap.count());
        assertEquals(25, heap.peek());
    }

    // Test push with null element
    @Test
    void testPushNullElement() {
        assertThrows(NullPointerException.class, () -> {
            heap.push(null); //rouge
        }); //rouge
    }

    // Test push with duplicate element
    @Test
    void testPushDuplicateElement() {
        heap.push(10);
        heap.push(10); // Adding duplicate
        assertEquals(2, heap.count());
        assertEquals(10, heap.peek()); // Minimum should still be 10
    }

    // Test count on empty heap
    @Test
    void testCountOnEmptyHeap() {
        assertEquals(0, heap.count());
    }

    // Test count on single element heap
    @Test
    void testCountOnSingleElementHeap() {
        heap.push(5);
        assertEquals(1, heap.count());
    }

    // Test count on multiple elements heap
    @Test
    void testCountOnMultipleElementsHeap() {
        heap.push(1);
        heap.push(2);
        heap.push(3);
        assertEquals(3, heap.count());
    }
   
    // 2.
    @Test
    void testPushCausesHeapifyUp() {
        heap.push(50);
        heap.push(30);
        heap.push(20);
        heap.push(10); // Adding an element that requires heapify
        assertEquals(10, heap.peek()); // Minimum should now be 10
    }

    @Test
    void testPushMultipleEquivalentElements() {
        heap.push(10);
        heap.push(10); // Adding duplicate
        heap.push(10); // Adding another duplicate
        assertEquals(10, heap.peek()); // Minimum should still be 10
        assertEquals(3, heap.count()); // Count should be 3
    }

    
    // 3. Predicat 1:
    @Test
    void testHeapifyDown_LeftChildSmaller() {
        heap.push(50);
        heap.push(30);
        heap.push(40);  // left child is smaller than root
        assertEquals(30, heap.pop());  // the minimum element (30) should be removed
    }

    @Test
    void testHeapifyDown_LeftChildNotSmaller() {
        heap.push(50);
        heap.push(70);
        heap.push(60);  // left child is not smaller than root
        assertEquals(50, heap.pop());  // 50 should be the minimum element
    }

    @Test
    void testHeapifyDown_NoLeftChild() {
        heap.push(50);
        heap.push(60);  // only one right child
        assertEquals(50, heap.pop());  // 50 should be removed as the minimum element
    }

    // 3. Predicat 2:
    @Test
    void testHeapifyDown_RightChildSmaller() {
        heap.push(50);
        heap.push(60);
        heap.push(30);  // right child is smaller than root
        assertEquals(30, heap.pop());  // 30 should be the minimum element
    }

    @Test
    void testHeapifyDown_RightChildNotSmaller() {
        heap.push(50);
        heap.push(30);
        heap.push(40);  // right child is not smaller than root
        assertEquals(30, heap.pop());  // 30 should be the minimum element
    }

    @Test
    void testHeapifyDown_NoRightChild() {
        heap.push(50);
        heap.push(30);  // only one left child
        assertEquals(30, heap.pop());  // 30 should be the minimum element
    }
    
    // 4
 
    
    @Test
    void testHeapifyUpWithLargeHeap() {
        for (int i = 10000; i > 0; i--) {
            heap.push(i);
        }
        assertEquals(1, heap.peek()); // Vérifie que le plus petit élément reste à la racine
    }
 
    
    @Test
    void testHeapifyDownRightChildEdgeCase() {
        heap.push(10);
        heap.push(20);
        heap.push(30); // [10, 20, 30]
        heap.push(5);  // Doit déplacer 5 à la racine
        assertEquals(5, heap.pop());  // 5 retiré
        assertEquals(10, heap.peek());  // 10 doit être à la racine
    }

    @Test 
    void testHeapifyDownRightChildNotSmaller() {
        heap.push(10);
        heap.push(20);
        heap.push(15); // Le fils droit n'est pas plus petit que le fils gauche
        assertEquals(10, heap.pop());  // 10 retiré
        assertEquals(15, heap.peek());  // 15 doit être à la racine, car 20 est plus grand
    }

    @Test 
    void testHeapifyUpWithDeepStructure() {
        for (int i = 0; i < 100; i++) {
            heap.push(100 - i); // Ajoute des éléments de 100 à 1
        }
        assertEquals(1, heap.peek()); // Le plus petit élément doit être à la racine
    }

    @Test 
    void testHeapifyDownWithEqualSmallestChildIndex() {
        heap.push(10);
        heap.push(20);
        heap.push(15);
        heap.push(5);
        heap.pop(); 
        assertEquals(10, heap.peek()); 
    }
}
