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

## Answer
## 1.
   ### Characteristics and Partition Blocks

#### Empty String
- **Input:** `""`
- **Expected Output:** `true`

#### Single Character (Invalid)
- **Inputs:** `"("`, `"["`, `"}"`
- **Expected Output:** `false`

#### Correctly Nested Balanced Strings
- **Inputs:** `"{}"`, `"[]"`, `"()"`, `"{[]}"`, `"{[()]}"`, `"{[][]}({})"`
- **Expected Output:** `true`

#### Incorrectly Nested or Unmatched Symbols
- **Inputs:** `"}{"`, `"[(])"`, `"{{"`, `"{(}]"`, `"([)]"`
- **Expected Output:** `false`

#### Random Strings with Symbols and Letters
- **Inputs:** `"a(b)c"`, `"foo{bar}"`, `"["`
- **Expected Output:** Based on balancing only symbols, e.g., `"a(b)c"` should return `true` if parentheses match.

## 2.

#### The Stack is Used (Push and Pop Operations)
- The stack is utilized to push opening symbols (e.g., `{`, `[`, `(`) and pop them when corresponding closing symbols (e.g., `}`, `]`, `)`) are encountered.

#### The Stack is Empty at the Beginning and End
- At the start, the stack is empty.
- If the string is correctly nested, the stack should be empty when all characters are processed, indicating balanced symbols.

#### Invalid Symbols Cause Early Returns (False)
- If an unmatched closing symbol or an incorrect nesting is found, the function should return `false` immediately without continuing further.

#### Correct Nesting Causes the Final Result to be True
- If all symbols are correctly nested and the stack is empty at the end, the function returns `true`.
- 
  after evaluating the statements , we found that tests coveres all the code, so we didn't add other tests 
![image](https://github.com/user-attachments/assets/228ffc08-37e2-42da-8610-f43347938284)


## 3. 
### Testing Requirements

#### Opening Symbols (Predicate 1):
- **True Case:** Input contains at least one opening symbol.
- **False Case:** Input contains no opening symbols.

#### Closing Symbols (Predicate 2):
- **True Case:** Input contains at least one closing symbol.
- **False Case:** Input contains no closing symbols.

#### Stack Empty Check (Predicate 3):
- **True Case:** The stack is empty when a closing symbol is encountered.
- **False Case:** The stack is not empty when a closing symbol is encountered.

#### Matching Pairs Check (Predicate 4):
- **True Case:** The opening and closing symbols match.
- **False Case:** The opening and closing symbols do not match.
  
 based on the test cases we have already provided ,they satisfid the BCC.   
 
 ## 4.    
 
 After using PIT (Pitest) to evaluate the test suite, we achieved a 100% mutation coverage. This means that all mutations introduced by PIT were either killed by the existing test suite or no live mutants remained.   
 
![image](https://github.com/user-attachments/assets/da051301-1adb-464c-a553-855b91df084e)


