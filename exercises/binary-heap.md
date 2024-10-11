# Implementing and testing a binary heap

A [*binary heap*](https://en.wikipedia.org/wiki/Binary_heap) is a data structure that contains comparable objects and it is able to efficiently return the lowest element.
This data structure relies on a binary tree to keep the insertion and deletion operations efficient. It is the base of the [*Heapsort* algorithm](https://en.wikipedia.org/wiki/Heapsort).

Implement a `BinaryHeap` class with the following interface:

```java
class BinaryHeap<T> {

    public BinaryHeap(Comparator<T> comparator) { ... }

    public T pop() { ... }

    public T peek() { ... }

    public void push(T element) { ... }

    public int count() { ... }

}
```

A `BinaryHeap` instance is created using a `Comparator` object that represents the ordering criterion between the objects in the heap.
`pop` returns and removes the minimum object in the heap. If the heap is empty it throws a `NotSuchElementException`.
`peek` similar to `pop`, returns the minimum object but it does not remove it from the `BinaryHeap`.
`push` adds an element to the `BinaryHeap`.
`count` returns the number of elements in the `BinaryHeap`.

Design and implement a test suite for this `BinaryHeap` class.
Feel free to add any extra method you may need.

Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-heap](../code/tp3-heap) to complete this exercise.

# Answer
## 1.
**public T pop() { ... }**
  
Characteristics:   

	The state of the heap   
 
Blocks:   

    - Heap is empty
    - Heap contains one element
    - Heap contains multiple elements

**public T peek() { ... }**   

Characteristics:   

	The state of the heap   
 
Blocks:   

    - Heap is empty
    - Heap contains one element
    - Heap contains multiple elements

**public void push(T element) { ... }**   

Characteristics:   

	The element being added   
 
Blocks:   

    - Valid element
    - Null element
    - Duplicate elements

**public int count() { ... }**   

Characteristics:   

	The state of the heap   
 
Blocks:   

    - Heap is empty
    - Heap contains one element
    - Heap contains multiple elements

The characteristic state of the heap is used in the methods pop(), peek(), and count().   

## 2.Evaluate the statement coverage using the plugin eclmma in eclipse   
<img width="565" alt="Capture d’écran 2024-10-09 154043" src="https://github.com/user-attachments/assets/a03f9b76-f3fe-45a0-953c-c467d91be493">


for the binaryheap the lines in red means that they wasn’t tested ,so we will add more test to improve the coverage.
After adding 2 tests, we had this result:

<img width="555" alt="Coverage2" src="https://github.com/user-attachments/assets/2765ae40-8dd7-4ae0-993b-533d3ae50b22">

The addition of the 2 tests allowed us to increase the coverage from 92.4% to 93.3% which does not show a big difference because we already had 14 first tests with a coverage of 92.4%.

## 3. Base Choice Coverage :
These are the cases in the method heapifyDown(int index)
### predicat 1:

    if (leftChildIndex < heap.size() && comparator.compare(heap.get(leftChildIndex), heap.get(smallestChildIndex)) < 0)  

**Case 1:**   

    leftChildIndex < heap.size() == true et comparator.compare(heap.get(leftChildIndex), heap.get(smallestChildIndex)) < 0 == true   

**Case 2:**   

    leftChildIndex < heap.size() == true et comparator.compare(heap.get(leftChildIndex), heap.get(smallestChildIndex)) < 0 == false   

**Case 3:**   

	leftChildIndex < heap.size() == false   
 
### predicat 2 :   

    if (rightChildIndex < heap.size() && comparator.compare(heap.get(rightChildIndex), heap.get(smallestChildIndex)) < 0)   

**Case 1:**   

    rightChildIndex < heap.size() == true et comparator.compare(heap.get(rightChildInd), heap.get(smallestChildIndex)) < 0 == true   

**Case 2:**   

    rightChildIndex < heap.size() == true et comparator.compare(heap.get(rightChildInd), heap.get(smallestChildIndex)) < 0 == false   

**Case 3:**   

	right ChildIndex < heap.size() == false

<img width="548" alt="Coverage3" src="https://github.com/user-attachments/assets/c84212ce-78a7-496d-bd26-4045aac41d5b">

the 6 tests added satisfy the bcc and have improved the couverage of the code from 93,3% to 94,6%.

## 4.Mutation Test using Pit:
<img width="620" alt="mutation4-1" src="https://github.com/user-attachments/assets/0373a70f-ec2f-4600-a2bf-c6c06750c15e">   

Mutation coverage is 74%, we killed 26 mutants and we have 9 survived, so we will should add new test cases or refactor the existing ones to achieve a high mutation score.  

<img width="410" alt="mutation4-2" src="https://github.com/user-attachments/assets/ab827d02-1f5b-4077-881c-b1688bc75ca6">   

The 5 new tests were able to kill 6 mutants, which increased Mutation coverage from 74% to 89%.
